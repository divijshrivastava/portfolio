package tech.divij.aspect;

import com.blueconic.browscap.BrowsCapField;
import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.UserAgentParser;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.Executor;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tech.divij.dao.UserActivity;
import tech.divij.repository.UserActivityRepository;

@Aspect
@Component
@Slf4j
public class ActivityLogger {
  private final UserActivityRepository userActivityRepository;

  @Autowired private UserAgentParser parser;

  @Autowired
  @Qualifier("asyncExecutor")
  private Executor executor;

  @Autowired
  public ActivityLogger(UserActivityRepository userActivityRepository) {
    this.userActivityRepository = userActivityRepository;
  }

  @Pointcut("execution(public * tech.divij.controller.*.*(..))")
  public void controllerMethods() {}

  @Pointcut("@annotation(LogActivity)")
  public void logActivityPointcut() {}

  @AfterReturning(pointcut = "logActivityPointcut()")
  public void logActivity(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    LogActivity logActivity = signature.getMethod().getAnnotation(LogActivity.class);
    String activityType = logActivity.activityType();
    String activityDetails = getActivityDetails(joinPoint);
    String ipAddress = getIpAddress(joinPoint);
    String userAgentString =
        ((HttpServletRequest) (joinPoint.getArgs()[0])).getHeader("User-Agent");
    Capabilities capabilities = parser.parse(userAgentString);
    log.info("Time is:" + LocalDateTime.now());

    UserActivity userActivity =
        new UserActivity(
            ipAddress,
            activityType,
            activityDetails,
            LocalDateTime.now(ZoneId.of("Asia/Kolkata")),
            userAgentString,
            capabilities.getBrowser(),
            capabilities.getPlatform(),
            capabilities.getDeviceType(),
            capabilities.getValue(BrowsCapField.PLATFORM_MAKER));
    executor.execute(() -> userActivityRepository.save(userActivity));
  }

  private String getActivityDetails(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    if (args == null || args.length == 0) {
      return null;
    }
    Object firstArg = args[0];
    if (firstArg instanceof HttpServletRequest) {
      HttpServletRequest request = (HttpServletRequest) firstArg;
      String uri = request.getRequestURI();
      return "URI=" + uri;
    } else {
      return firstArg.toString();
    }
  }

  private String getIpAddress(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    if (args == null || args.length == 0) {
      return null;
    }
    Object firstArg = args[0];
    if (!(firstArg instanceof HttpServletRequest)) {
      return null;
    }
    HttpServletRequest request = (HttpServletRequest) firstArg;
    String ipAddress = request.getHeader("X-Forwarded-For");
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("Proxy-Client-IP");
    }
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getRemoteAddr();
    }
    return ipAddress;
  }
}

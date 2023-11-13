package tech.divij.mapper;

import org.springframework.stereotype.Component;
import tech.divij.dao.UserActivity;
import tech.divij.dto.UserActivityDto;

@Component
public class UserActivityMapper {

  public UserActivityDto toDto(UserActivity entity) {
    UserActivityDto dto = new UserActivityDto();
    dto.setId((long) entity.getId());
    dto.setIpAddress(entity.getIpAddress());
    dto.setActivityType(entity.getActivityType());
    dto.setActivityDetails(entity.getActivityDetails());
    dto.setActivityTime(entity.getCreatedAt());
    dto.setUserAgentString(entity.getUserAgent());
    dto.setBrowser(entity.getBrowser());
    dto.setPlatform(entity.getOperatingSystem());
    dto.setDeviceType(entity.getDeviceType());
    dto.setPlatformMaker(entity.getPlatformMaker());
    return dto;
  }
}

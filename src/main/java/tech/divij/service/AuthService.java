package tech.divij.service;

import static tech.divij.constants.Constants.RESTART;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

  private final UserAuthenticationService userAuthenticationService;

  private final ScheduledExecutorService executorService =
      Executors.newSingleThreadScheduledExecutor();

  @Value("${execution_script_location}")
  private String executionScriptLocation;

  @Value("${task_delay}")
  private int taskDelay;

  public AuthService(UserAuthenticationService userAuthenticationService) {
    this.userAuthenticationService = userAuthenticationService;
  }

  @Async
  public void scheduleDelayedTask(Runnable task) {
    executorService.schedule(task, taskDelay, TimeUnit.SECONDS);
  }

  public ResponseEntity runScript(String command) {
    String output = "There was a failure while running the command: " + command;
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.TEXT_PLAIN);
    if (command.equals(RESTART)) {
      scheduleDelayedTask(
          () -> {
            try {
              Runtime.getRuntime().exec(executionScriptLocation);
            } catch (IOException e) {
              log.error(
                  "There was an error while executing the script at {}. Please look at the error message {}",
                  executionScriptLocation,
                  e.getMessage());
            }
          });
      output = "The application will restart in a while!";
    } else {
      output = "Command not found!";
    }
    return ResponseEntity.ok().headers(httpHeaders).body(output);
  }

  public List<String> fetchAuthorities() {

    return userAuthenticationService.getAuthorities();

  }
}

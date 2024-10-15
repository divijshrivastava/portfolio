package tech.divij.controller;

import static tech.divij.constants.Constants.RESTART;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.divij.constants.Constants;
import tech.divij.response.Response;
import tech.divij.service.AuthService;
import tech.divij.service.UserAuthenticationService;

@Slf4j
@RestController
@RequestMapping("user-auth")
//@CrossOrigin(
//    origins = "*",
//    maxAge = 3600,
//    methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
public class AuthController {

  private final UserAuthenticationService userAuthenticationService;

  private final AuthService authService;

  @Autowired
  public AuthController(
      UserAuthenticationService userAuthenticationService, AuthService authService) {
    this.userAuthenticationService = userAuthenticationService;
    this.authService = authService;
  }

  @PostMapping("logged-user")
  public Response homePage() {
    log.info("Auth Controller: logged-user api");

    String username = userAuthenticationService.getLoggedInUserDetails();

    return Response.builder()
        .message(username)
        .responseCode(username == null ? "User not found" : "User Found")
        .build();
  }

  @PostMapping("is-admin")
  public Response isAdmin() {

    if (userAuthenticationService.isAdmin()) {

      return Response.builder()
          .message(Constants.TRUE)
          .responseCode(Constants.SUCCESS)
          .responseMessage(Constants.USER_ADMIN_MESSAGE)
          .build();
    }

    return Response.builder()
        .message(Constants.FALSE)
        .responseCode(Constants.FAILURE)
        .responseMessage(Constants.USER_NOT_ADMIN_MESSAGE)
        .build();
  }

  @GetMapping("entitlements")
  public ResponseEntity fetchAuthorities() {

    return ResponseEntity.ok().body(authService.fetchAuthorities());
  }

  @GetMapping("restart")
  public ResponseEntity restartServer() {
    // Execute the script
    log.info("Restart requested");

    if (userAuthenticationService.isAdmin()) {
      return authService.runScript(RESTART);
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }
}

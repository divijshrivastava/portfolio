package tech.divij.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.divij.dto.UserDto;
import tech.divij.service.UserAuthenticationService;

@RestController
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private UserAuthenticationService userAuthenticationService;

  @PostMapping
  public ResponseEntity login(@RequestBody UserDto loginRequest) {
    try {
      if (userAuthenticationService.checkUserCredentials(loginRequest)) {
        Authentication authentication =
          new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
            loginRequest.getPassword());
        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.status(200).build();
      } else {
        return ResponseEntity.status(403).body("Login failed: ");
      }
    } catch (AuthenticationException e) {
      return ResponseEntity.status(403).body("Login failed: ");
    }
  }

}

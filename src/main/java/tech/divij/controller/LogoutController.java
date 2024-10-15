package tech.divij.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class LogoutController {

  @PostMapping
  public ResponseEntity logout() {

    try {
      SecurityContextHolder.clearContext();
      return ResponseEntity.status(200).build();
    } catch (AuthenticationException e) {
      return ResponseEntity.status(403).body("Login failed: ");
    }
  }

}

package tech.divij.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.divij.dao.UserContact;
import tech.divij.service.RecaptchaService;
import tech.divij.service.UserContactService;

@RestController
@RequestMapping("user-contact")
public class UserContactController {
  private final UserContactService userContactService;
  private final RecaptchaService recaptchaService;

  public UserContactController(
      UserContactService userContactService, RecaptchaService recaptchaService) {
    this.userContactService = userContactService;
    this.recaptchaService = recaptchaService;
  }

  @PostMapping
  public ResponseEntity<Void> saveUserContact(@RequestBody UserContact userContact) {
    userContactService.saveUserContact(userContact);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/validateCaptcha")
  public ResponseEntity<Boolean> validateCaptcha(
      @RequestParam("captchaResponse") String captchaResponse) {
    Boolean success = recaptchaService.isCaptchaValid(captchaResponse);
    return ResponseEntity.ok(success);
  }
}

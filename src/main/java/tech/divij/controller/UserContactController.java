package tech.divij.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.divij.dto.UserContactDto;
import tech.divij.service.RecaptchaService;
import tech.divij.service.UserContactService;

@RestController
@RequestMapping("/user-contact")
public class UserContactController {

  private final UserContactService userContactService;
  private final RecaptchaService recaptchaService;

  public UserContactController(UserContactService userContactService,
    RecaptchaService recaptchaService) {
    this.userContactService = userContactService;
    this.recaptchaService = recaptchaService;
  }

  @PostMapping
  public ResponseEntity<Void> saveUserContact(@RequestBody UserContactDto userContactEntity) {
    userContactService.saveUserContact(userContactEntity);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/validateCaptcha")
  public ResponseEntity<Boolean>
  validateCaptcha(@RequestParam("captchaResponse") String captchaResponse) {
    Boolean success = recaptchaService.isCaptchaValid(captchaResponse);
    return ResponseEntity.ok(success);
  }

  @GetMapping("/all")
  public ResponseEntity<Page<UserContactDto>>
  fetchUserContactList(@RequestParam(value = "page", defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = "10") int size,
    @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
    Page<UserContactDto> userContactDtos = userContactService.fetchContacts(pageable);
    return ResponseEntity.ok(userContactDtos);
  }

  @GetMapping("total-contacts")
  public ResponseEntity getTotalContacts() {
    Long totalContacts = userContactService.getTotalContacts();
    return ResponseEntity.ok(totalContacts);
  }
}

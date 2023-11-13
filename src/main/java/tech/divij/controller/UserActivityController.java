package tech.divij.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.divij.dto.UserActivityDto;
import tech.divij.service.UserActivityService;

@RestController
@RequestMapping("/user-activity")
public class UserActivityController {

  private final UserActivityService userActivityService;

  @Autowired
  public UserActivityController(UserActivityService userActivityService) {
    this.userActivityService = userActivityService;
  }

  @GetMapping("/all")
  public ResponseEntity<Page<UserActivityDto>> getUserActivity(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size,
      @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
    Page<UserActivityDto> userActivityDtoPage =
        userActivityService.getAccessibleUserActivity(pageable);
    return ResponseEntity.ok(userActivityDtoPage);
  }

  @GetMapping("/total-activities")
  public Long getTotalActivities() {
    return userActivityService.getTotalActivities();
  }
}

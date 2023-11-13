package tech.divij.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.divij.dao.UserActivity;
import tech.divij.dto.UserActivityDto;
import tech.divij.mapper.UserActivityMapper;
import tech.divij.repository.UserActivityRepository;

@Service
public class UserActivityService {

  private final UserActivityRepository userActivityRepository;

  private final UserAuthenticationService userAuthenticationService;

  private final UserActivityMapper userActivityMapper;

  public UserActivityService(
      UserActivityRepository userActivityRepository,
      UserAuthenticationService userAuthenticationService,
      UserActivityMapper userActivityMapper) {
    this.userActivityRepository = userActivityRepository;
    this.userAuthenticationService = userAuthenticationService;
    this.userActivityMapper = userActivityMapper;
  }

  static Page<UserActivityDto> getUserActivityDtos(
      Pageable pageable,
      UserAuthenticationService userAuthenticationService,
      UserActivityRepository userActivityRepository,
      UserActivityMapper userActivityMapper) {
    if (!userAuthenticationService.isAdmin()) {
      return new PageImpl<>(Collections.emptyList());
    }
    Page<UserActivity> userActivityPage = userActivityRepository.findAll(pageable);
    List<UserActivityDto> userActivityDtoList =
        userActivityPage.getContent().stream()
            .map(userActivityMapper::toDto)
            .collect(Collectors.toList());
    return new PageImpl<>(userActivityDtoList, pageable, userActivityPage.getTotalElements());
  }

  public Page<UserActivityDto> getAccessibleUserActivity(Pageable pageable) {
    return getUserActivityDtos(
        pageable, userAuthenticationService, userActivityRepository, userActivityMapper);
  }

  public long getTotalActivities() {
    return userAuthenticationService.isAdmin() ? userActivityRepository.count() : 0;
  }
}

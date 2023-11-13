package tech.divij.service;

import org.springframework.stereotype.Service;
import tech.divij.mapper.UserActivityMapper;
import tech.divij.repository.UserActivityRepository;

@Service
public class ContactService {

  private final UserActivityRepository userActivityRepository;

  private final UserAuthenticationService userAuthenticationService;

  private final UserActivityMapper userActivityMapper;

  public ContactService(
      UserActivityRepository userActivityRepository,
      UserAuthenticationService userAuthenticationService,
      UserActivityMapper userActivityMapper) {
    this.userActivityRepository = userActivityRepository;
    this.userAuthenticationService = userAuthenticationService;
    this.userActivityMapper = userActivityMapper;
  }
}

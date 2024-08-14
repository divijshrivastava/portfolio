package tech.divij.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import tech.divij.entity.Authority;
import tech.divij.repository.UserRepo;

@Service
public class UserService {

  private final UserRepo userRepo;

  public UserService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public List<String> fetchAuthoritiesForUserName(String username) {
    return userRepo.findByUserName(username).getAuthorities().stream()
        .map(Authority::getAuthorityName)
        .collect(Collectors.toList());
  }
}

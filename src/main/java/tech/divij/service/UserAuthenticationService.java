package tech.divij.service;

import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.divij.constants.Constants;
import tech.divij.constants.Role;
import tech.divij.dto.UserDto;
import tech.divij.entity.UserCredEntity;
import tech.divij.repository.UserCredRepo;

@Slf4j
@Service
public class UserAuthenticationService {

  private final UserCredRepo userCredRepo;
  private final UserService userService;

  public UserAuthenticationService(UserCredRepo userCredRepo, UserService userService) {
    this.userCredRepo = userCredRepo;
    this.userService = userService;
  }

  private static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }

  public boolean checkUserCredentials(UserDto userDto) {
    String encryptedPassword;
    try {
      // TODO: Change passwords from String to byte arrays.
      encryptedPassword =
          bytesToHex(MessageDigest.getInstance("MD5").digest(userDto.getPassword().getBytes()));
    } catch (Exception e) {
      return false;
    }
    return (userCredRepo.isUserValid(userDto.getUserName(), encryptedPassword).size() != 0);
  }

  public UserDto getUserDetailsByUserName(String username) {

    List<UserCredEntity> userList = userCredRepo.findByUserName(username);

    if (userList.isEmpty()) {
      return null;
    }
    UserCredEntity userCredEntity = userList.get(0);

    UserDto userDto = new UserDto();
    userDto.setUserName(userCredEntity.getUserName());
    userDto.setPassword(userCredEntity.getPassword());
    //    userDto.setAuthorities(userService.fetchAuthoritiesForUserName(username));

    return userDto;
  }

  public String getLoggedInUserDetails() {
    String userName;

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      userName = ((UserDetails) principal).getUsername();
    } else {
      userName = principal.toString();
    }
    log.info("logged in user: {}", userName);
    if (Constants.ANONYMOUS_USER.equals(userName)) {
      return null;
    }

    return userName;
  }

  public boolean isAdmin() {
    return getAuthorities().contains(Role.ADMIN.toString());
  }

  public List<String> getAuthorities() {
    UserDetails userDetails;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      userDetails = ((UserDetails) principal);
      List<String> authorities =
        userDetails.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.toList());
      return authorities;
    }
    return List.of();
  }
}

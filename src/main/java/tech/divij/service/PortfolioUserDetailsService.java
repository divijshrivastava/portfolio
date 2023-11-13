package tech.divij.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.divij.constants.Constants;
import tech.divij.dto.UserDto;

@Service
public class PortfolioUserDetailsService implements UserDetailsService {

  private final UserAuthenticationService userAuthenticationService;

  private final UserService userService;

  public PortfolioUserDetailsService(
      UserAuthenticationService userAuthenticationService, UserService userService) {
    this.userAuthenticationService = userAuthenticationService;
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserDto portfolioUser = userAuthenticationService.getUserDetailsByUserName(username);
    List<String> authorities = userService.fetchAuthoritiesForUserName(username);
    if (portfolioUser == null) {
      throw new UsernameNotFoundException(Constants.USERNAME_NOT_FOUND);
    }
    List<GrantedAuthority> auths = new ArrayList<>();

    for (String authority : authorities) {
      auths.add(() -> authority);
    }

    return new User(portfolioUser.getUserName(), portfolioUser.getPassword(), auths);
  }
}

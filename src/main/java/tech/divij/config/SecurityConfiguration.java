package tech.divij.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.divij.constants.Constants;
import tech.divij.constants.Role;
import tech.divij.service.PortfolioUserDetailsService;

@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final PortfolioUserDetailsService userDetailsService;

  public SecurityConfiguration(PortfolioUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  public static String getMd5(String input) {
    try {
      // Static getInstance method is called with hashing SHA
      MessageDigest md = MessageDigest.getInstance(Constants.MD5);

      // digest() method called
      // to calculate message digest of an input
      // and return array of byte
      byte[] messageDigest = md.digest(input.getBytes());

      // Convert byte array into signum representation
      BigInteger no = new BigInteger(Constants.ONE, messageDigest);

      // Convert message digest into hex value
      StringBuilder hashtext = new StringBuilder(no.toString(Constants.SIXTEEN));

      while (hashtext.length() < Constants.THIRTY_TWO) {
        hashtext
            .append(Constants.ZERO_STRING)
            .append(hashtext); // = Constants.ZERO_STRING + hashtext;
      }

      return hashtext.toString();
    }

    // For specifying wrong message digest algorithms
    catch (NoSuchAlgorithmException e) {
      log.error("Exception thrown for incorrect algorithm: {}", e);
      return null;
    }
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
      .antMatchers("/admin")
      .hasRole(Role.ADMIN.toString())
      .antMatchers("/user-contact/all?**")
      .hasRole(Role.ADMIN.toString())
      .antMatchers("/total-contacts")
      .hasRole(Role.ADMIN.toString())
      .antMatchers("/user")
      .hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
      .antMatchers("/file")
      .hasAuthority(Role.ADMIN.toString())
      .antMatchers("/resume/upload")
      .hasAuthority(Role.ADMIN.toString())
      .antMatchers("/file/create")
      .hasAuthority(Role.ADMIN.toString())
      .antMatchers("/file/blog-image-upload")
      .hasAnyAuthority(Role.ADMIN.toString(), Role.AUTHOR.toString())
      .antMatchers("/blog/**")
      .permitAll()
      .antMatchers("/user-auth/**")
      .permitAll()
      .antMatchers("/app/*")
      .permitAll()
      .antMatchers("/assets/*")
      .permitAll()
      .antMatchers("/ping").permitAll()
      .antMatchers("/**")
      .permitAll()
      .and()
      .csrf()
      .disable()
      .formLogin();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new PasswordEncoder() {
      @Override
      public String encode(CharSequence charSequence) {
        return getMd5(charSequence.toString());
      }

      @Override
      public boolean matches(CharSequence charSequence, String s) {
        return getMd5(charSequence.toString()).equals(s);
      }
    };
  }
}

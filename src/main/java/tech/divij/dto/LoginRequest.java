package tech.divij.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@Data
public class LoginRequest {

  private String userName;
  private String password;
}

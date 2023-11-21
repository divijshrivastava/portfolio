package tech.divij.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class UserContactDto {

  public String fullName;
  public String email;
  public String message;
  public String status;
  public String ipAddress;
  public String isResponded;
  public String time;

}

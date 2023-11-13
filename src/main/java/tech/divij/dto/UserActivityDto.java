package tech.divij.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityDto {
  private Long id;
  private String ipAddress;
  private String activityType;
  private String activityDetails;
  private LocalDateTime activityTime;
  private String userAgentString;
  private String browser;
  private String platform;
  private String deviceType;
  private String platformMaker;
}

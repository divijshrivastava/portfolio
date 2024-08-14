package tech.divij.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "USER_ACTIVITY")
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "ip_address")
  private String ipAddress;

  @Column(name = "activity_type")
  private String activityType;

  @Column(name = "activity_details")
  private String activityDetails;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "USER_AGENT")
  private String userAgent;

  @Column(name = "BROWSER")
  private String browser;

  @Column(name = "OPERATING_SYSTEM")
  private String operatingSystem;

  @Column(name = "DEVICE_TYPE")
  private String deviceType;

  @Column(name = "PLATFORM_MAKER")
  private String platformMaker;

  public UserActivity(
      String ipAddress,
      String activityType,
      String activityDetails,
      LocalDateTime createdAt,
      String userAgent,
      String browser,
      String operatingSystem,
      String deviceType,
      String platformMaker) {
    this.ipAddress = ipAddress;
    this.activityType = activityType;
    this.activityDetails = activityDetails;
    this.createdAt = createdAt;
    this.userAgent = userAgent;
    this.browser = browser;
    this.operatingSystem = operatingSystem;
    this.deviceType = deviceType;
    this.platformMaker = platformMaker;
  }
}

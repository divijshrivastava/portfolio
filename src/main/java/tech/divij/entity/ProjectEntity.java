package tech.divij.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@Entity(name = "PROJECT")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PUBLISH_TIMESTAMP")
  private LocalDateTime publishedOn;

  @Column(name = "INSERT_TIMESTAMP")
  private LocalDateTime insertTimestamp;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "PROJECT_TYPE")
  private String projectType;

  @Column(name = "INSERTED_BY")
  private String insertedBy;

  @Column(name = "IS_ACTIVE")
  private boolean isActive;

  @Column(name = "HEADING")
  private String heading;

  @Column(name = "DESCRIPTION")
  private String description;

}

package tech.divij.entity;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name = "VIDEO_PROJECT")
@NoArgsConstructor
@AllArgsConstructor
public class YtVideoEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "PROJECT_ID")
  private ProjectEntity projectId;

  @Column(name = "VIDEO_LINK")
  private String videoLink;

  @Column(name = "IS_IMAGE_PRESENT")
  private boolean isImagePresent;

  @Column(name = "IMAGE_ID")
  private long imageId;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "INSERTED_BY")
  private String insertedBy;

  @Column(name = "IS_ACTIVE")
  private boolean isActive;

  @Column(name = "INSERTED_ON")
  private LocalDateTime insertTime;

}

package tech.divij.entity.project;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity(name = "CODE_PROJECT")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class CodeProjectEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn
  @OneToOne(cascade = CascadeType.ALL)
  private ProjectEntity projectId;

  @Column(name = "CODE_LINK")
  private String codeLink;

  @Column(name = "IS_IMAGE_PRESENT")
  private Boolean isImagePresent;

  @Column(name = "IMAGE_ID")
  private Long imageId;

  @Column(name = "STATUS")
  private String status;

}

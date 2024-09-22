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
@Entity(name = "WEBSITE_PROJECT")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class WebsiteProjectEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "PROJECT_ID")
  @OneToOne(cascade = CascadeType.ALL)
  private ProjectEntity projectId;

  @Column(name = "WEBSITE_LINK")
  private String websiteLink;

  @Column(name = "IS_WEBSITE_LINK_PRESENT")
  private Boolean isWebsiteLinkPresent;

  @Column(name = "CODE_LINK")
  private String codeLink;

  @Column(name = "IS_CODE_LINK_PRESENT")
  private Boolean isCodeLinkPresent;

  @Column(name = "IMAGE_ID")
  private Long imageId;

}

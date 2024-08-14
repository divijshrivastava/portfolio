package tech.divij.dto.project;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import tech.divij.dto.ProjectWrapper;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class WebsiteProjectDto extends ProjectWrapper {

  private Long id;
  private String websiteLink;
  private Boolean isWebsiteLinkPresent;
  private String codeLink;
  private Boolean isCodeLinkPresent;
  private Long projectId;
  private Long imageId;
  private String insertedBy;
  private Boolean isActive;
  private String status;
  private String insertTimestamp;

}

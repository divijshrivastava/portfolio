package tech.divij.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tech.divij.dto.ProjectWrapper;

@SuperBuilder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
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

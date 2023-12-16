package tech.divij.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
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

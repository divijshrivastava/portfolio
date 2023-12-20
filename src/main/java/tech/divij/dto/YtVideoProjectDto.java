package tech.divij.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class YtVideoProjectDto extends ProjectWrapper {

  private Long id;
  private String videoLink;
  private Long projectId;
  private Boolean isImagePresent;
  private Long imageId;
  private String insertedBy;
  private Boolean isActive;

}

package tech.divij.dto.project;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import tech.divij.dto.ProjectWrapper;

@SuperBuilder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class CodeProjectDto extends ProjectWrapper {

  private Long id;
  private String codeLink;
  private Long projectId;
  private Boolean isImagePresent;
  private Long imageId;
  private String insertedBy;
  private Boolean isActive;
  private String status;

}

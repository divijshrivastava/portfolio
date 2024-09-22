package tech.divij.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tech.divij.constants.ProjectType;


@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ProjectWrapper {

  private String heading;
  private String description;
  private ProjectType projectType;
  private String status;
}

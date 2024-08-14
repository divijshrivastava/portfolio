package tech.divij.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tech.divij.dto.ProjectWrapper;
import tech.divij.dto.project.CodeProjectDto;
import tech.divij.dto.project.WebsiteProjectDto;
import tech.divij.dto.project.YtVideoProjectDto;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {

  @JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "projectType"
  )
  @JsonSubTypes({
    @JsonSubTypes.Type(value = YtVideoProjectDto.class, name = "YTVIDEO"),
    @JsonSubTypes.Type(value = WebsiteProjectDto.class, name = "WEBSITE"),
    @JsonSubTypes.Type(value = CodeProjectDto.class, name = "CODE")
  })
  private ProjectWrapper projectWrapper;

}

package tech.divij.request;

import lombok.Builder;
import lombok.Data;
import tech.divij.constants.ProjectType;
import tech.divij.dto.CodeProjectDto;
import tech.divij.dto.WebsiteProjectDto;
import tech.divij.dto.YtVideoProjectDto;

@Data
@Builder
public class ProjectRequest {

  private ProjectType projectType;

  private YtVideoProjectDto ytVideoProjectDto;

  private WebsiteProjectDto websiteProjectDto;

  private CodeProjectDto codeProjectDto;

}

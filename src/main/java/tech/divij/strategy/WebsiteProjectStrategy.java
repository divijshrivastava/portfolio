package tech.divij.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.divij.dto.project.WebsiteProjectDto;
import tech.divij.entity.project.WebsiteProjectEntity;
import tech.divij.mapper.ProjectMapper;
import tech.divij.repository.WebsiteProjectRepository;
import tech.divij.request.ProjectRequest;
import tech.divij.service.UserAuthenticationService;

@AllArgsConstructor
@Component
public class WebsiteProjectStrategy implements ProjectStrategy {

  private WebsiteProjectRepository websiteProjectRepository;
  private ProjectMapper projectMapper;
  private UserAuthenticationService userAuthenticationService;

  public WebsiteProjectDto save(ProjectRequest projectRequest) {
    String userName = this.userAuthenticationService.getLoggedInUserDetails();
    WebsiteProjectEntity websiteProjectEntity = websiteProjectRepository.save(
      projectMapper.mapDtoToEntity((WebsiteProjectDto) projectRequest.getProjectWrapper(),
        userName));
    return projectMapper.mapEntityToDto(websiteProjectEntity);
  }

}

package tech.divij.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.divij.dto.project.YtVideoProjectDto;
import tech.divij.entity.project.YtVideoEntity;
import tech.divij.mapper.ProjectMapper;
import tech.divij.repository.YtVideoProjectRepository;
import tech.divij.request.ProjectRequest;
import tech.divij.service.UserAuthenticationService;


@AllArgsConstructor
@Component
public class VideoProjectStrategy implements ProjectStrategy {

  private YtVideoProjectRepository ytVideoProjectRepository;

  private ProjectMapper projectMapper;

  private UserAuthenticationService userAuthenticationService;

  public YtVideoProjectDto save(ProjectRequest projectRequest) {
    String userName = this.userAuthenticationService.getLoggedInUserDetails();
    YtVideoEntity ytVideoEntity =
      ytVideoProjectRepository.save(
        projectMapper.mapDtoToEntity((YtVideoProjectDto) projectRequest.getProjectWrapper(),
          userName));

    return projectMapper.mapEntityToDto(ytVideoEntity);
  }
}

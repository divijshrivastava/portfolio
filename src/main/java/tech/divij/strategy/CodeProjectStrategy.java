package tech.divij.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.divij.dto.ProjectWrapper;
import tech.divij.dto.project.CodeProjectDto;
import tech.divij.entity.project.CodeProjectEntity;
import tech.divij.mapper.ProjectMapper;
import tech.divij.repository.CodeProjectRepository;
import tech.divij.request.ProjectRequest;
import tech.divij.service.UserAuthenticationService;

@Component
@AllArgsConstructor
public class CodeProjectStrategy implements ProjectStrategy {

  private CodeProjectRepository codeProjectRepository;
  private ProjectMapper projectMapper;
  private UserAuthenticationService userAuthenticationService;

  @Override
  public ProjectWrapper save(ProjectRequest projectWrapper) {

    String userName = this.userAuthenticationService.getLoggedInUserDetails();
    CodeProjectEntity codeProjectEntity = codeProjectRepository.save(
      projectMapper.mapDtoToEntity((CodeProjectDto) projectWrapper.getProjectWrapper(), userName));

    return projectMapper.mapEntityToDto(codeProjectEntity);
  }


}

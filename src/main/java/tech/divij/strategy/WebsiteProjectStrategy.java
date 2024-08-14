package tech.divij.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.divij.dto.ProjectWrapper;
import tech.divij.dto.project.WebsiteProjectDto;
import tech.divij.entity.project.WebsiteProjectEntity;
import tech.divij.mapper.ProjectMapper;
import tech.divij.repository.WebsiteProjectRepository;
import tech.divij.request.ProjectRequest;

@AllArgsConstructor
@Component
public class WebsiteProjectStrategy implements ProjectStrategy {

  private WebsiteProjectRepository websiteProjectRepository;
  private ProjectMapper projectMapper;

  public WebsiteProjectDto save(ProjectWrapper projectWrapper) {
    WebsiteProjectEntity websiteProjectEntity = websiteProjectRepository.save(
      projectMapper.mapDtoToEntity((WebsiteProjectDto) projectWrapper));
    return projectMapper.mapEntityToDto(websiteProjectEntity);
  }

  @Override
  public ProjectWrapper save(ProjectRequest projectWrapper) {
    return null;
  }
}

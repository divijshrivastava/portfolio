package tech.divij.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.divij.dto.ProjectWrapper;
import tech.divij.entity.project.ProjectEntity;
import tech.divij.mapper.ProjectMapper;
import tech.divij.repository.CodeProjectRepository;
import tech.divij.repository.ProjectRepository;
import tech.divij.repository.WebsiteProjectRepository;
import tech.divij.repository.YtVideoProjectRepository;
import tech.divij.request.ProjectRequest;
import tech.divij.strategy.ProjectStrategy;
import tech.divij.strategy.factory.ProjectStrategyFactory;

@Service
@Slf4j
@AllArgsConstructor
public class ProjectService {

  private final ProjectMapper projectMapper;

  private final UserAuthenticationService userAuthenticationService;

  private final YtVideoProjectRepository ytVideoProjectRepository;

  private final WebsiteProjectRepository websiteProjectRepository;

  private final CodeProjectRepository codeProjectRepository;

  private final ProjectRepository projectRepository;

  private final ProjectStrategyFactory projectStrategyFactory;

  public ResponseEntity createProject(ProjectRequest projectRequest) {
    if (userAuthenticationService.isAdmin()) {
      ProjectWrapper projectWrapper = projectRequest.getProjectWrapper();
      ProjectStrategy projectStrategy = this.projectStrategyFactory.getProjectStrategy(
        projectWrapper
      );

      return ResponseEntity.ok(projectStrategy.save(projectRequest));

    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }


  public ResponseEntity fetchProjects(int page, int count) {

    List<ProjectEntity> projectEntityList = projectRepository.findAll();
    List<ProjectWrapper> responses = new ArrayList<>();

    projectEntityList.forEach(projectEntity -> {
      switch (projectEntity.getProjectType()) {
        case "YTVIDEO":

          responses.add(projectMapper.mapEntityToDto(
            ytVideoProjectRepository.findByProjectId(projectEntity)));
          break;

        case "WEBSITE":
          responses.add(projectMapper.mapEntityToDto(
            websiteProjectRepository.findByProjectId(projectEntity)));
          break;

        case "CODE":
          responses.add(projectMapper.mapEntityToDto(
            codeProjectRepository.findByProjectId(projectEntity)));
          break;
      }
    });

    return ResponseEntity.ok(responses);
  }
}

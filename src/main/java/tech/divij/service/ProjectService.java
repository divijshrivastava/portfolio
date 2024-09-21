package tech.divij.service;

import static tech.divij.constants.Constants.FAILURE;
import static tech.divij.constants.Constants.PROJECT_NOT_APPROVED_MESSAGE;
import static tech.divij.constants.Constants.PROJECT_NOT_FOUND;
import static tech.divij.constants.Constants.USER_NOT_ADMIN_MESSAGE;
import static tech.divij.constants.Constants.USER_NOT_AUTHORISED;

import com.mysql.cj.util.StringUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.divij.constants.BlogStatus;
import tech.divij.constants.Constants;
import tech.divij.constants.ProjectStatus;
import tech.divij.dto.ProjectWrapper;
import tech.divij.entity.project.ProjectEntity;
import tech.divij.entity.project.YtVideoEntity;
import tech.divij.mapper.ProjectMapper;
import tech.divij.repository.CodeProjectRepository;
import tech.divij.repository.ProjectRepository;
import tech.divij.repository.WebsiteProjectRepository;
import tech.divij.repository.YtVideoProjectRepository;
import tech.divij.request.ProjectRequest;
import tech.divij.response.Response;
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

    List<String> statuses = new ArrayList<>();
    statuses.add(ProjectStatus.LIVE.name());
    if (userAuthenticationService.isAdmin()) {
      statuses.add(ProjectStatus.PENDING.name());
    }
    List<ProjectEntity> projectEntityList = projectRepository.findActiveProjectsByStatusesOrderByPublishedDate(statuses);//.findAll();
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

  public Response approveProject(String projectId) {

    if (userAuthenticationService.isAdmin()) {
      Optional<ProjectEntity> projectEntity = projectRepository.findById(Long.parseLong(projectId));
      if (projectEntity.isPresent()) {
        ProjectEntity pe = projectEntity.get();
        pe.setStatus(ProjectStatus.LIVE.name());
        pe.setPublishedOn(LocalDateTime.now());
        projectRepository.save(pe);
        return Response.builder()
          .message(Constants.PROJECT_APPROVED_MESSAGE)
          .responseCode(Constants.SUCCESS)
          .build();
      }else{
        return Response.builder().message(PROJECT_NOT_FOUND).responseCode(FAILURE)
          .responseMessage(PROJECT_NOT_FOUND).build();
      }
    }
    return Response.builder().message(PROJECT_NOT_APPROVED_MESSAGE)
      .responseCode(Constants.FAILURE).responseMessage(USER_NOT_AUTHORISED).build();
  }

  public Response deleteProject(String projectId) {

    Optional<ProjectEntity> projectEntity;

    if (userAuthenticationService.isAdmin()) {
      projectEntity = projectRepository.findById(Long.parseLong(projectId));
      if(projectEntity.isPresent()){
        ProjectEntity pe = projectEntity.get();
        pe.setActive(false);
        projectRepository.save(pe);
      }else{
        return Response.builder().build();
      }
      return Response.builder()
        .message(Constants.PROJECT_DELETED_MESSAGE)
        .responseCode(Constants.SUCCESS)
        .build();
    }
    return Response.builder()
      .message(Constants.PROJECT_COULD_NOT_BE_DELETED)
      .responseCode(Constants.FAILURE)
      .build();
  }
}

package tech.divij.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.divij.constants.ProjectStatus;
import tech.divij.dto.CodeProjectDto;
import tech.divij.dto.ProjectWrapper;
import tech.divij.dto.WebsiteProjectDto;
import tech.divij.dto.YtVideoProjectDto;
import tech.divij.entity.CodeProjectEntity;
import tech.divij.entity.ProjectEntity;
import tech.divij.entity.WebsiteProjectEntity;
import tech.divij.entity.YtVideoEntity;
import tech.divij.mapper.ProjectMapper;
import tech.divij.repository.CodeProjectRepository;
import tech.divij.repository.ProjectRepository;
import tech.divij.repository.WebsiteProjectRepository;
import tech.divij.repository.YtVideoProjectRepository;
import tech.divij.request.ProjectRequest;

@Service
@Slf4j
public class ProjectService {

  private final ProjectMapper projectMapper;

  private final UserAuthenticationService userAuthenticationService;

  private final YtVideoProjectRepository ytVideoProjectRepository;

  private final WebsiteProjectRepository websiteProjectRepository;

  private final CodeProjectRepository codeProjectRepository;

  private final ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectMapper projectMapper,
    UserAuthenticationService userAuthenticationService,
    YtVideoProjectRepository ytVideoProjectRepository,
    WebsiteProjectRepository websiteProjectRepository,
    CodeProjectRepository codeProjectRepository,
    ProjectRepository projectRepository) {
    this.projectMapper = projectMapper;
    this.userAuthenticationService = userAuthenticationService;
    this.ytVideoProjectRepository = ytVideoProjectRepository;
    this.websiteProjectRepository = websiteProjectRepository;
    this.codeProjectRepository = codeProjectRepository;
    this.projectRepository = projectRepository;
  }

  public ResponseEntity createProject(ProjectRequest projectRequest) {
    ResponseEntity responseEntity = null;
    if (userAuthenticationService.isAdmin()) {
      String userName = this.userAuthenticationService.getLoggedInUserDetails();
      ProjectEntity projectEntity;
      projectEntity =
        ProjectEntity.builder()
          .projectType(projectRequest.getProjectType().toString())
          .isActive(true)
          .insertTimestamp(LocalDateTime.now())
          .insertedBy(userName)
          .status(ProjectStatus.PENDING.toString())
          .build();
      switch (projectRequest.getProjectType()) {
        case YTVIDEO:
          YtVideoProjectDto ytVideoProjectDto =
            projectRequest.getYtVideoProjectDto();
          YtVideoEntity ytVideoEntity =
            projectMapper
              .mapYtVideoDtoToEntity((projectRequest.getYtVideoProjectDto()))
              .insertedBy(userName)
              .projectId(projectEntity.toBuilder()
                .heading(ytVideoProjectDto.getHeading())
                .description(ytVideoProjectDto.getDescription())
                .build())
              .insertTime(LocalDateTime.now())

              .build();
          responseEntity = ResponseEntity.ok(
            this.ytVideoProjectRepository.save(ytVideoEntity));
          break;
        case WEBSITE:
          WebsiteProjectDto websiteProjectDto =
            projectRequest.getWebsiteProjectDto();
          WebsiteProjectEntity websiteProjectEntity =
            projectMapper.mapDtoToEntity(websiteProjectDto)
              .projectId(projectEntity.toBuilder()
                .heading(websiteProjectDto.getHeading())
                .description(websiteProjectDto.getDescription())
                .insertTimestamp(LocalDateTime.now())
                .build())
              .build();
          responseEntity = ResponseEntity.ok(
            websiteProjectRepository.save(websiteProjectEntity));
          break;
        case CODE:
          CodeProjectDto codeProjectDto = projectRequest.getCodeProjectDto();

          CodeProjectEntity codeProjectEntity =
            projectMapper.mapDtoToEntity(codeProjectDto)
              .status(ProjectStatus.PENDING.toString())
              .insertTimestamp(LocalDateTime.now())
              .insertedBy(userName)
              .projectId(projectEntity.toBuilder()
                .heading(codeProjectDto.getHeading())
                .description(codeProjectDto.getDescription())
                .build())

              .isActive(true)
              .build();

          responseEntity =
            ResponseEntity.ok(codeProjectRepository.save(codeProjectEntity));

          break;
        case ANONYMOUS:
          responseEntity = ResponseEntity.ok().build();
          break;
      }
    } else {
      responseEntity = ResponseEntity.status(403).build();
    }

    return responseEntity;
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

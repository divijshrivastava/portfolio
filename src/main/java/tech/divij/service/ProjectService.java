package tech.divij.service;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.divij.constants.ProjectStatus;
import tech.divij.dao.CodeProjectEntity;
import tech.divij.dao.ProjectEntity;
import tech.divij.dao.WebsiteProjectEntity;
import tech.divij.dao.YtVideoEntity;
import tech.divij.dto.CodeProjectDto;
import tech.divij.dto.WebsiteProjectDto;
import tech.divij.mapper.ProjectMapper;
import tech.divij.repository.CodeProjectRepository;
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

  @Autowired
  public ProjectService(ProjectMapper projectMapper,
    UserAuthenticationService userAuthenticationService,
    YtVideoProjectRepository ytVideoProjectRepository,
    WebsiteProjectRepository websiteProjectRepository,
    CodeProjectRepository codeProjectRepository) {
    this.projectMapper = projectMapper;
    this.userAuthenticationService = userAuthenticationService;
    this.ytVideoProjectRepository = ytVideoProjectRepository;
    this.websiteProjectRepository = websiteProjectRepository;
    this.codeProjectRepository = codeProjectRepository;
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

          YtVideoEntity ytVideoEntity =
            projectMapper
              .mapYtVideoDtoToEntity((projectRequest.getYtVideoProjectDto()))
              .insertedBy(userName)
              .projectId(projectEntity)
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
              .projectId(projectEntity)
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
}

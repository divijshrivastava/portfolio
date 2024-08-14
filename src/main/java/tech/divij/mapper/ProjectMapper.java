package tech.divij.mapper;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tech.divij.constants.ProjectStatus;
import tech.divij.constants.ProjectType;
import tech.divij.dto.project.CodeProjectDto;
import tech.divij.dto.project.WebsiteProjectDto;
import tech.divij.dto.project.YtVideoProjectDto;
import tech.divij.entity.project.CodeProjectEntity;
import tech.divij.entity.project.ProjectEntity;
import tech.divij.entity.project.WebsiteProjectEntity;
import tech.divij.entity.project.WebsiteProjectEntity.WebsiteProjectEntityBuilder;
import tech.divij.entity.project.YtVideoEntity;

@Component
@Slf4j
@Scope("singleton")
public class ProjectMapper {


  public YtVideoEntity
  mapDtoToEntity(YtVideoProjectDto ytVideoProjectDto, String userName) {
    boolean isImagePresent;
    ProjectEntity projectEntity = ProjectEntity.builder()
      .projectType(ProjectType.YTVIDEO.toString())
      .heading(ytVideoProjectDto.getHeading())
      .description(ytVideoProjectDto.getDescription())
      .isActive(true)
      .insertTimestamp(LocalDateTime.now())
      .insertedBy(userName)
      .status(ProjectStatus.PENDING.toString())
      .build();
    return YtVideoEntity.builder()
      .videoLink(ytVideoProjectDto.getVideoLink())
      .isImagePresent(
        (isImagePresent = ytVideoProjectDto.getIsImagePresent()))
      .imageId(isImagePresent ? ytVideoProjectDto.getImageId() : -1)
      .status(ProjectStatus.PENDING.toString()).projectId(projectEntity)
      .build();
  }

  public WebsiteProjectEntity
  mapDtoToEntity(WebsiteProjectDto websiteProjectDto) {
    Boolean isCodeLinkPresent = websiteProjectDto.getIsCodeLinkPresent();
    Boolean isWebsiteLinkPresent = websiteProjectDto.getIsWebsiteLinkPresent();
    WebsiteProjectEntityBuilder builder =
      WebsiteProjectEntity.builder()
        .codeLink(isCodeLinkPresent ? websiteProjectDto.getCodeLink()
          : null)
        .websiteLink(isWebsiteLinkPresent
          ? websiteProjectDto.getWebsiteLink()
          : null)
        .imageId(websiteProjectDto.getImageId())
        .isWebsiteLinkPresent(isWebsiteLinkPresent)
        .isCodeLinkPresent(isCodeLinkPresent)
        .status(ProjectStatus.PENDING.toString());
    return builder.build();
  }

  public CodeProjectEntity
  mapDtoToEntity(CodeProjectDto codeProjectDto, String userName) {
    Boolean isImagePresent = codeProjectDto.getIsImagePresent();
    ProjectEntity projectEntity = ProjectEntity.builder()
      .projectType(ProjectType.CODE.toString())
      .heading(codeProjectDto.getHeading())
      .description(codeProjectDto.getDescription())
      .isActive(true)
      .insertTimestamp(LocalDateTime.now())
      .insertedBy(userName)
      .status(ProjectStatus.PENDING.toString())
      .build();
    return CodeProjectEntity.builder()
      .codeLink(codeProjectDto.getCodeLink())
      .imageId(isImagePresent ? codeProjectDto.getImageId() : -1)
      .isImagePresent(isImagePresent)
      .projectId(projectEntity).build();
  }

  public YtVideoProjectDto mapEntityToDto(YtVideoEntity ytVideoEntity) {
    YtVideoProjectDto ytVideoProjectDto =
      YtVideoProjectDto.builder()
        .isImagePresent(ytVideoEntity.isImagePresent())
        .videoLink(ytVideoEntity.getVideoLink())
        .build();
    ytVideoProjectDto.setHeading(ytVideoEntity.getProjectId().getHeading());
    ytVideoProjectDto.setDescription(
      ytVideoEntity.getProjectId().getDescription());
    ytVideoProjectDto.setProjectId(ytVideoEntity.getProjectId().getId());

    ytVideoProjectDto.setProjectType(ProjectType.YTVIDEO);

    return ytVideoProjectDto;
  }

  public WebsiteProjectDto
  mapEntityToDto(WebsiteProjectEntity websiteProjectEntity) {

    WebsiteProjectDto websiteProjectDto =
      WebsiteProjectDto.builder()
        .projectId(websiteProjectEntity.getProjectId().getId())
        .id(websiteProjectEntity.getId())
        .imageId(websiteProjectEntity.getImageId())
        .isCodeLinkPresent(websiteProjectEntity.getIsCodeLinkPresent())
        .isWebsiteLinkPresent(
          websiteProjectEntity.getIsWebsiteLinkPresent())
        .codeLink(websiteProjectEntity.getCodeLink())
        .websiteLink(websiteProjectEntity.getWebsiteLink())
        .build();

    websiteProjectDto.setHeading(
      websiteProjectEntity.getProjectId().getHeading());
    websiteProjectDto.setDescription(
      websiteProjectEntity.getProjectId().getDescription());
    websiteProjectDto.setProjectType(ProjectType.WEBSITE);
    return websiteProjectDto;
  }

  public CodeProjectDto mapEntityToDto(CodeProjectEntity codeProjectEntity) {
    return CodeProjectDto.builder()
      .isImagePresent(codeProjectEntity.getIsImagePresent())
      .projectId(codeProjectEntity.getProjectId().getId())
      .id(codeProjectEntity.getId())
      .imageId(codeProjectEntity.getImageId())
      .codeLink(codeProjectEntity.getCodeLink())
      .status(codeProjectEntity.getStatus())
      .heading(codeProjectEntity.getProjectId().getHeading())
      .description(codeProjectEntity.getProjectId().getDescription())
      .projectType(ProjectType.CODE)
      .build();
  }

}

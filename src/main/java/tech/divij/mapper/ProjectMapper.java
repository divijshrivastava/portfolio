package tech.divij.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tech.divij.constants.ProjectStatus;
import tech.divij.constants.ProjectType;
import tech.divij.dto.CodeProjectDto;
import tech.divij.dto.ProjectWrapper;
import tech.divij.dto.WebsiteProjectDto;
import tech.divij.dto.YtVideoProjectDto;
import tech.divij.entity.CodeProjectEntity;
import tech.divij.entity.CodeProjectEntity.CodeProjectEntityBuilder;
import tech.divij.entity.WebsiteProjectEntity;
import tech.divij.entity.WebsiteProjectEntity.WebsiteProjectEntityBuilder;
import tech.divij.entity.YtVideoEntity;
import tech.divij.entity.YtVideoEntity.YtVideoEntityBuilder;

@Component
@Slf4j
@Scope("singleton")
public class ProjectMapper {

  public YtVideoEntityBuilder
  mapYtVideoDtoToEntity(YtVideoProjectDto ytVideoProjectDto) {
    boolean isImagePresent;
    return YtVideoEntity.builder()
      .videoLink(ytVideoProjectDto.getVideoLink())
      .isImagePresent(
        (isImagePresent = ytVideoProjectDto.getIsImagePresent()))
      .imageId(isImagePresent ? ytVideoProjectDto.getImageId() : -1)
      .status(ProjectStatus.PENDING.toString())
      .isActive(true);
  }

  public WebsiteProjectEntityBuilder
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
    return builder;
  }

  public CodeProjectEntityBuilder
  mapDtoToEntity(CodeProjectDto codeProjectDto) {
    Boolean isImagePresent = codeProjectDto.getIsImagePresent();
    return CodeProjectEntity.builder()
      .codeLink(codeProjectDto.getCodeLink())
      .imageId(isImagePresent ? codeProjectDto.getImageId() : -1)
      .isImagePresent(isImagePresent);
  }

  public ProjectWrapper mapEntityToDto(YtVideoEntity ytVideoEntity) {
    YtVideoProjectDto ytVideoProjectDto =
      YtVideoProjectDto.builder()
        .isImagePresent(ytVideoEntity.isImagePresent())
        .videoLink(ytVideoEntity.getVideoLink())
        .build();
    ytVideoProjectDto.setHeading(ytVideoEntity.getProjectId().getHeading());
    ytVideoProjectDto.setDescription(
      ytVideoEntity.getProjectId().getDescription());
    ytVideoProjectDto.setProjectId(ytVideoEntity.getProjectId().getId());

    ytVideoProjectDto.setProjectType(ProjectType.YTVIDEO.toString());

    return ytVideoProjectDto;
  }

  public ProjectWrapper
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
    websiteProjectDto.setProjectType(ProjectType.WEBSITE.toString());
    return websiteProjectDto;
  }

  public ProjectWrapper mapEntityToDto(CodeProjectEntity codeProjectEntity) {
    CodeProjectDto codeProjectDto =
      CodeProjectDto.builder()
        .isImagePresent(codeProjectEntity.getIsImagePresent())
        .projectId(codeProjectEntity.getProjectId().getId())
        .id(codeProjectEntity.getId())
        .imageId(codeProjectEntity.getImageId())
        .codeLink(codeProjectEntity.getCodeLink())
        .status(codeProjectEntity.getStatus())

        .build();

    codeProjectDto.setHeading(codeProjectDto.getHeading());
    codeProjectDto.setDescription(codeProjectDto.getDescription());
    codeProjectDto.setProjectType(ProjectType.CODE.name());

    return codeProjectDto;
  }
}

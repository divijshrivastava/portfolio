package tech.divij.mapper;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tech.divij.constants.Constants;
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
  mapDtoToEntity(YtVideoProjectDto ytVideoProjectDto, String userName) throws Exception {
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
      .videoLink(createYtEmbedUrl(ytVideoProjectDto.getVideoLink()))
      .isImagePresent(
        (isImagePresent = ytVideoProjectDto.getIsImagePresent()))
      .imageId(isImagePresent ? ytVideoProjectDto.getImageId() : -1)
      .projectId(projectEntity)
      .build();
  }

  private String createYtEmbedUrl(String videoLink) throws Exception {
    String youtubeVideoId = extractYtVideoId(videoLink);
    return Constants.YOUTUBE_EMBED_VIDEO_URL_PREFIX+youtubeVideoId;
  }

  private String extractYtVideoId(String videoLink) throws Exception {

    String videoIdPattern = Constants.YOUTUBE_ID_PATTERN;
    String videoId;

    // Compile the regex pattern
    Pattern pattern = Pattern.compile(videoIdPattern);
    Matcher matcher = pattern.matcher(videoLink);

    // If a match is found, return the video ID
    if (matcher.find()) {
      videoId = matcher.group(1); // Group 1 contains the video ID
    } else {
      // Return null if no video ID is found
      throw new Exception("Issue with video url, video id could not be extracted.");
    }
    return videoId;
  }

  public WebsiteProjectEntity
  mapDtoToEntity(WebsiteProjectDto websiteProjectDto, String userName) {
    Boolean isCodeLinkPresent = websiteProjectDto.getIsCodeLinkPresent();
    Boolean isWebsiteLinkPresent = websiteProjectDto.getIsWebsiteLinkPresent();
    ProjectEntity projectEntity = ProjectEntity.builder()
      .projectType(ProjectType.WEBSITE.toString())
      .heading(websiteProjectDto.getHeading())
      .description(websiteProjectDto.getDescription())
      .isActive(true)
      .insertTimestamp(LocalDateTime.now())
      .insertedBy(userName)
      .status(ProjectStatus.PENDING.toString())
      .build();
    WebsiteProjectEntityBuilder builder =
      WebsiteProjectEntity.builder()
        .codeLink(isCodeLinkPresent ? websiteProjectDto.getCodeLink()
          : null)
        .websiteLink(isWebsiteLinkPresent
          ? websiteProjectDto.getWebsiteLink()
          : null).projectId(projectEntity)
        .imageId(websiteProjectDto.getImageId())
        .isWebsiteLinkPresent(isWebsiteLinkPresent)
        .isCodeLinkPresent(isCodeLinkPresent);
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
      .projectDeploymentLink(codeProjectDto.getDeploymentLink())
      .isProjectDeploymentLinkPresent(codeProjectDto.getIsDeploymentLinkPresent())
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
    ytVideoProjectDto.setId(ytVideoProjectDto.getId());
    ytVideoProjectDto.setProjectId(ytVideoEntity.getProjectId().getId());
    ytVideoProjectDto.setProjectType(ProjectType.YTVIDEO);
    ytVideoProjectDto.setStatus(ytVideoEntity.getProjectId().getStatus());

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
    websiteProjectDto.setStatus(websiteProjectEntity.getProjectId().getStatus());
    return websiteProjectDto;
  }

  public CodeProjectDto mapEntityToDto(CodeProjectEntity codeProjectEntity) {
    return CodeProjectDto.builder()
      .isImagePresent(codeProjectEntity.getIsImagePresent())
      .projectId(codeProjectEntity.getProjectId().getId())
      .id(codeProjectEntity.getId())
      .imageId(codeProjectEntity.getImageId())
      .codeLink(codeProjectEntity.getCodeLink())
      .status(codeProjectEntity.getProjectId().getStatus())
      .heading(codeProjectEntity.getProjectId().getHeading())
      .description(codeProjectEntity.getProjectId().getDescription())
      .deploymentLink(codeProjectEntity.getProjectDeploymentLink())
      .isDeploymentLinkPresent(codeProjectEntity.isProjectDeploymentLinkPresent())
      .projectType(ProjectType.CODE)
      .build();
  }

}

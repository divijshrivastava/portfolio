package tech.divij.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tech.divij.constants.ProjectStatus;
import tech.divij.dao.CodeProjectEntity;
import tech.divij.dao.CodeProjectEntity.CodeProjectEntityBuilder;
import tech.divij.dao.WebsiteProjectEntity;
import tech.divij.dao.WebsiteProjectEntity.WebsiteProjectEntityBuilder;
import tech.divij.dao.YtVideoEntity;
import tech.divij.dao.YtVideoEntity.YtVideoEntityBuilder;
import tech.divij.dto.CodeProjectDto;
import tech.divij.dto.WebsiteProjectDto;
import tech.divij.dto.YtVideoProjectDto;

@Component
@Slf4j
@Scope("singleton")
public class ProjectMapper {

  public YtVideoEntityBuilder mapYtVideoDtoToEntity(YtVideoProjectDto ytVideoProjectDto) {
    boolean isImagePresent = false;
    return YtVideoEntity.builder().codeLink(ytVideoProjectDto.getCodeLink()).isImagePresent(
        (isImagePresent = ytVideoProjectDto.getIsImagePresent()))
      .imageId(isImagePresent ? ytVideoProjectDto.getImageId() : -1)
      .status(ProjectStatus.PENDING.toString()).insertedBy("thor")
      .isActive(true);
  }

  public WebsiteProjectEntityBuilder mapDtoToEntity(WebsiteProjectDto websiteProjectDto) {
    Boolean isCodeLinkPresent = websiteProjectDto.getIsCodeLinkPresent();
    Boolean isWebsiteLinkPresent = websiteProjectDto.getIsWebsiteLinkPresent();
    WebsiteProjectEntityBuilder builder = WebsiteProjectEntity.builder()
      .codeLink(isCodeLinkPresent ? websiteProjectDto.getCodeLink() : null)
      .websiteLink(isWebsiteLinkPresent ? websiteProjectDto.getWebsiteLink() : null).imageId(
        websiteProjectDto.getImageId()).status(ProjectStatus.PENDING.toString());
    return builder;
  }

  public CodeProjectEntityBuilder mapDtoToEntity(CodeProjectDto codeProjectDto) {
    Boolean isImagePresent = codeProjectDto.getIsImagePresent();
    return CodeProjectEntity.builder().codeLink(codeProjectDto.getCodeLink())
      .imageId(isImagePresent ? codeProjectDto.getImageId() : -1)
      .isImagePresent(isImagePresent);
  }
}

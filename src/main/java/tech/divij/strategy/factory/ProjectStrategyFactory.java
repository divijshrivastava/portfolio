package tech.divij.strategy.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.divij.dto.ProjectWrapper;
import tech.divij.dto.project.CodeProjectDto;
import tech.divij.dto.project.WebsiteProjectDto;
import tech.divij.dto.project.YtVideoProjectDto;
import tech.divij.strategy.AnonymousProjectStrategy;
import tech.divij.strategy.CodeProjectStrategy;
import tech.divij.strategy.ProjectStrategy;
import tech.divij.strategy.VideoProjectStrategy;
import tech.divij.strategy.WebsiteProjectStrategy;

@AllArgsConstructor
@Component
public class ProjectStrategyFactory {

  private VideoProjectStrategy videoProjectStrategy;
  private CodeProjectStrategy codeProjectStrategy;
  private WebsiteProjectStrategy websiteProjectStrategy;
  private AnonymousProjectStrategy anonymousProjectStrategy;

  public ProjectStrategy getProjectStrategy(ProjectWrapper projectWrapper) {

    if (projectWrapper.getClass().equals(CodeProjectDto.class)) {
      return codeProjectStrategy;
    } else if (projectWrapper.getClass().equals(WebsiteProjectDto.class)) {
      return websiteProjectStrategy;
    } else if (projectWrapper.getClass().equals(YtVideoProjectDto.class)) {
      return videoProjectStrategy;
    }
    return anonymousProjectStrategy;
  }

}

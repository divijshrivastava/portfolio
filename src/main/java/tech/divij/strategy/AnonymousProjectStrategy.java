package tech.divij.strategy;

import org.springframework.stereotype.Component;
import tech.divij.dto.ProjectWrapper;
import tech.divij.request.ProjectRequest;

@Component
public class AnonymousProjectStrategy implements ProjectStrategy {


  public ProjectWrapper save(ProjectWrapper projectDto) {
    return null;
  }

  @Override
  public ProjectWrapper save(ProjectRequest projectWrapper) {
    return null;
  }

}

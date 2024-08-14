package tech.divij.strategy;


import tech.divij.dto.ProjectWrapper;
import tech.divij.request.ProjectRequest;

public interface ProjectStrategy {

  ProjectWrapper save(ProjectRequest projectWrapper);

}

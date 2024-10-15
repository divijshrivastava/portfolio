package tech.divij.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.divij.aspect.LogActivity;
import tech.divij.request.ProjectRequest;
import tech.divij.response.Response;
import tech.divij.service.ProjectService;

@Slf4j
@RestController
@RequestMapping("project")
public class ProjectController {

  private final ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping
  @LogActivity(activityType = "create_project")
  public ResponseEntity
  createProject(HttpServletRequest request,
    @RequestBody final ProjectRequest projectRequest) {
    return projectService.createProject(projectRequest);

  }

  @GetMapping("{page}/{count}")
  @LogActivity(activityType = "fetch_projects")
  public ResponseEntity fetchProject(HttpServletRequest request,
    @PathVariable int page,
    @PathVariable int count) {
    return projectService.fetchProjects(page, count);
  }

  @PostMapping("approve/{projectId}")
  @LogActivity(activityType = "approve_project")
  public Response<String> approveProject(HttpServletRequest request,@PathVariable("projectId") final String projectId
    )
    throws Exception {
    return projectService.approveProject(projectId);
  }

  @DeleteMapping("{projectId}")
  public Response<String> deleteProject(@PathVariable final String projectId) {

    return projectService.deleteProject(projectId);
  }
}

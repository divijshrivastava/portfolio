package tech.divij.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.divij.aspect.LogActivity;
import tech.divij.request.ProjectRequest;
import tech.divij.service.ProjectService;

@Slf4j
@RestController
@RequestMapping("project")
@CrossOrigin(
  origins = "*",
  maxAge = 3600,
  methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
public class ProjectController {

  private final ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping
  @LogActivity(activityType = "create_project")
  public ResponseEntity createBlog(HttpServletRequest request,
    @RequestBody final ProjectRequest projectRequest)
    throws Exception {
    return projectService.createProject(projectRequest);
  }

}

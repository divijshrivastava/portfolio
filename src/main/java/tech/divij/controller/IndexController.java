package tech.divij.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.divij.aspect.LogActivity;
import tech.divij.constants.Constants;

@Slf4j
@Controller
public class IndexController {

  @GetMapping("/")
  @LogActivity(activityType = "homepage_visit")
  public String homePage(HttpServletRequest request) {
    log.info("Index Controller");
    return Constants.APP_INDEX_PATH;
  }

  @GetMapping("/app")
  @LogActivity(activityType = "app")
  public String appPage(HttpServletRequest request) {
    return "/app/index.html";
  }

  @GetMapping("/blog")
  @LogActivity(activityType = "app")
  public String blogPage(HttpServletRequest request) {
    return "/app/blog";
  }
  @GetMapping("/project")
  @LogActivity(activityType = "app")
  public String projectPage(HttpServletRequest request) {
    return "/app/project";
  }

  @GetMapping("/projects")
  @LogActivity(activityType = "app")
  public String projectPageRedirection(HttpServletRequest request) {
    return "/app/project";
  }

  @GetMapping("/about")
  @LogActivity(activityType = "app")
  public String aboutPage(HttpServletRequest request) {
    return "redirect:/app/#about";
  }

  @GetMapping("/contact")
  @LogActivity(activityType = "app")
  public String contactPage(HttpServletRequest request) {
    return "redirect:/app/#contact";
  }

  @GetMapping("/resume")
  @LogActivity(activityType = "app")
  public String resumePage(HttpServletRequest request) {
    return "/app/resume";
  }

  @GetMapping("/login")
  @LogActivity(activityType = "logging_in")
  public String admin(HttpServletRequest request) {
    log.info("login");
    return "/app/login";
  }

  @GetMapping("/error")
  @LogActivity(activityType = "error")
  public String error(HttpServletRequest request) {
    log.info("Error");
    return Constants.APP_ERROR_PATH;
  }

  @GetMapping("/**/{link:[^\\.]*}")
  public String redirect(@PathVariable("link") String path) {
    log.info("Forwarding to index controller");
    log.info("Path: {}", path);
    return Constants.FORWARD_PATH;
  }

}

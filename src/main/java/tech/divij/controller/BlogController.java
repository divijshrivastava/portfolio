package tech.divij.controller;

import java.util.ArrayList;
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
import tech.divij.dto.BlogDto;
import tech.divij.response.Response;
import tech.divij.service.BlogService;

@Slf4j
@RestController
@RequestMapping("blog")
//@CrossOrigin(
//    origins = "*",
//    maxAge = 3600,
//    methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
public class BlogController {

  private final BlogService blogService;

  @Autowired
  public BlogController(BlogService blogService) {
    this.blogService = blogService;
  }

  @GetMapping("link/{link}")
  @LogActivity(activityType = "blog_by_link")
  public ResponseEntity<BlogDto> getBlogContentByLink(
      HttpServletRequest request, @PathVariable("link") String link) {
    log.info("inside controller link is {}", link);
    return blogService.getBlogByLink(link);
  }

  @GetMapping("{page}/{numberOfBlogs}")
  @LogActivity(activityType = "blogs_pagination")
  public Response<ArrayList<BlogDto>> getBlogsByPage(
      HttpServletRequest request,
      @PathVariable("page") int page,
      @PathVariable("numberOfBlogs") int nob) {
    log.info("inside controller: page " + page + " numberOfBlogs : " + nob);
    Response<ArrayList<BlogDto>> response = blogService.getBlogsByPage(page, nob);
    log.info("response: " + response);
    return response;
  }

  @PostMapping
  @LogActivity(activityType = "create_blog")
  public Response<String> createBlog(HttpServletRequest request, @RequestBody final BlogDto blogDto)
      throws Exception {
    return blogService.createBlog(blogDto);
  }

  @PostMapping("approve")
  @LogActivity(activityType = "approve_blog")
  public Response<String> approveBlog(HttpServletRequest request, @RequestBody final String blogId)
      throws Exception {
    return blogService.approveBlog(blogId);
  }

  @DeleteMapping("{blogId}")
  public Response<String> softDeleteBlog(@PathVariable final String blogId) {

    return blogService.softDeleteBlog(blogId);
  }
}

package tech.divij.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.divij.constants.BlogStatus;
import tech.divij.constants.Constants;
import tech.divij.dao.AuthorEntity;
import tech.divij.dao.BlogEntity;
import tech.divij.dto.BlogDto;
import tech.divij.mapper.BlogMapper;
import tech.divij.repository.BlogRepository;
import tech.divij.response.Response;

@Service
@Slf4j
public class BlogService {

  private final BlogRepository blogRepository;

  private final BlogMapper blogMapper;

  private final UserAuthenticationService userAuthenticationService;

  private final AuthorService authorService;

  @Autowired
  public BlogService(
      BlogRepository blogRepository,
      BlogMapper blogMapper,
      UserAuthenticationService userAuthenticationService,
      AuthorService authorService) {
    this.blogRepository = blogRepository;
    this.blogMapper = blogMapper;
    this.userAuthenticationService = userAuthenticationService;
    this.authorService = authorService;
  }

  public ResponseEntity<BlogDto> getBlogByLink(String link) {
    log.info("Inside BlogService, link is {}", link);

    Optional<BlogEntity> blogEntity = blogRepository.findByBlogTitleLink(link);

    if (blogEntity.isPresent()) {
      BlogEntity blogEntityValue = blogEntity.get();
      if (blogEntityValue.getStatus().equals(BlogStatus.PENDING.toString())) {
        if (userAuthenticationService.isAdmin()) {
          return ResponseEntity.ok(blogMapper.mapEntityToDto(blogEntityValue));
        }
        return ResponseEntity.status(403).build();
      } else {
        return ResponseEntity.ok(blogMapper.mapEntityToDto(blogEntityValue));
      }
    }
    return ResponseEntity.notFound().build();
  }

  public Response getBlogsByPage(int page, int nob) {

    List<String> status = new ArrayList<>();
    status.add(BlogStatus.LIVE.toString());

    if (userAuthenticationService.isAdmin()) {
      status.add(BlogStatus.PENDING.toString());
    }

    return Response.builder()
        .message(
            blogRepository.findBlogsByStatusInOrderByPublishedOnDesc(status).stream()
                .map(blogMapper::getBlogSummary)
                .collect(Collectors.toList()))
        .build();
  }

  public Response createBlog(BlogDto blog) throws Exception {

    if (userAuthenticationService.isAdmin()) {

      BlogEntity blogEntity;
      blogEntity = blogMapper.mapDtoToEntity(blog);
      if (blog.getBlogContent().length() < 50) {
        throw new Exception("Blog is too short.");
      }
      blogEntity.setContent(blog.getBlogContent());
      AuthorEntity author =
          authorService.fetchAuthorIdByUserName(userAuthenticationService.getLoggedInUserDetails());
      blogEntity.setAuthorId(author);
      blogEntity.setPublishedOn(LocalDateTime.now());
      BlogEntity savedBlog = blogRepository.save(blogEntity);
      return Response.builder()
          .message(Long.toString(savedBlog.getId()))
          .responseCode(Constants.SUCCESS_RESPONSE_CODE)
          .responseMessage(Constants.BLOG_UPLOAD_SUCCESS_MESSAGE)
          .build();
    }

    return Response.builder()
        .message(null)
        .responseCode(Constants.FAILURE)
        .responseMessage(Constants.BLOG_CREATION_FAILURE_MESSAGE)
        .build();
  }

  public Response softDeleteBlog(String blogId) {

    if (userAuthenticationService.isAdmin()) {
      blogRepository.deleteBlog(Long.parseLong(blogId));
      return Response.builder()
          .message(Constants.BLOG_DELETED_MESSAGE)
          .responseCode(Constants.SUCCESS)
          .build();
    }

    return Response.builder()
        .message(Constants.BLOG_NOT_DELETED_MESSAGE)
        .responseCode(Constants.FAILURE)
        .build();
  }

  public Response approveBlog(String blogId) {

    if (userAuthenticationService.isAdmin()) {
      blogRepository.approveBlog(Long.parseLong(blogId));
      return Response.builder()
          .message(Constants.BLOG_APPROVED_MESSAGE)
          .responseCode(Constants.SUCCESS)
          .build();
    }

    return Response.builder()
        .message(Constants.BLOG_NOT_APPROVED_MESSAGE)
        .responseCode(Constants.FAILURE)
        .build();
  }
}

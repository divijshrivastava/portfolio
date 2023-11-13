package tech.divij.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.divij.dao.AuthorEntity;
import tech.divij.dao.BlogEntity;
import tech.divij.dao.UserEntity;
import tech.divij.dto.BlogDto;
import tech.divij.dto.BlogSummary;
import tech.divij.mapper.BlogMapper;
import tech.divij.repository.BlogRepository;
import tech.divij.response.Response;

class BlogServiceTest {

  @InjectMocks BlogService sut;

  @Mock BlogRepository blogRepository;

  @Mock UserAuthenticationService userAuthenticationService;

  @Mock BlogMapper blogMapper;

  @Mock AuthorService authorService;

  private static BlogEntity getBlogEntity(String blogTitleLink, String status) {
    return BlogEntity.builder()
        .id(1L)
        .publishedOn(LocalDateTime.of(2022, 12, 12, 1, 1))
        .heading("link")
        .minutesToRead(12)
        .coverPhotoId("30")
        .content("This is my Blog.")
        .upVotes(0)
        .views(0)
        .coverPhotoIdDescription("This is cover photo.")
        .status(status)
        .blogSummary("Summary of the blog.")
        .authorId(getAuthorEntity())
        .blogSummaryImageId(1)
        .blogTitleLink(blogTitleLink)
        .build();
  }

  private static BlogDto getBlogDto() {
    return BlogDto.builder()
        .id(1L)
        .publishedDate(LocalDateTime.of(2022, 12, 12, 1, 1) + " min read")
        .blogHeading("link")
        .readTime(12 + " min read")
        .blogImageSrc("30")
        .blogContent("This is my Blog.")
        .blogSummary("Summary of the blog.")
        .authorName(getAuthorEntity().getFirstName() + " " + getAuthorEntity().getLastName())
        .blogHeading("link")
        .authorImageSrc(getAuthorEntity().getPhotoId().toString())
        .blogStatus("PENDING")
        .blogTitleLink("my_blog")
        .build();
  }

  private static AuthorEntity getAuthorEntity() {

    UserEntity user = new UserEntity();
    user.setUserName("user");
    return AuthorEntity.builder()
        .firstName("User")
        .lastName("Last")
        .authorId(1)
        .country("INDIA")
        .userName(user)
        .photoId(1)
        .email("user@user.com")
        .phone("")
        .build();
  }

  private static BlogSummary getBlogSummary() {

    return BlogSummary.builder()
        .id(1L)
        .publishedDate(LocalDateTime.of(2022, 12, 12, 1, 1).toString())
        .blogHeading("link")
        .readTime("12 min read")
        .blogImageSrc("30")
        .blogContent("This is my Blog.")
        .blogStatus("LIVE")
        .authorName(getAuthorEntity().getFirstName())
        .authorImageSrc(Integer.toString(getAuthorEntity().getPhotoId()))
        .blogImageSrc("30")
        .blogTitleLink("link")
        .build();
  }

  @BeforeEach
  public void setUp() {
    // Initialize the mocks using MockitoAnnotations
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getBlog_ByLink_WhenBlogNotPresentInDb() {
    // given
    String link = "link";

    // when
    when(blogRepository.findByBlogTitleLink(link)).thenReturn(Optional.empty());

    // then

    ResponseEntity<BlogDto> response = sut.getBlogByLink(link);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }

  @Test
  void getBlog_ByLink_WhenStatusIsPending_NotAdminCase() {

    // given
    String link = "my_blog";
    String status = "PENDING";
    BlogEntity blogEntity = getBlogEntity(link, status);

    // when
    when(blogRepository.findByBlogTitleLink(link)).thenReturn(Optional.of(blogEntity));
    when(userAuthenticationService.isAdmin()).thenReturn(false);

    // then
    ResponseEntity<BlogDto> response = sut.getBlogByLink(link);
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  @Test
  void getBlog_ByLink_WhenStatusIsPending_AdminCase() {

    // given
    String link = "my_blog";
    String status = "PENDING";
    BlogEntity blogEntity = getBlogEntity(link, status);

    // when
    when(blogRepository.findByBlogTitleLink(anyString())).thenReturn(Optional.of(blogEntity));
    when(userAuthenticationService.isAdmin()).thenReturn(true);
    when(blogMapper.mapEntityToDto(any(BlogEntity.class))).thenReturn(getBlogDto());

    // then
    ResponseEntity<BlogDto> response = sut.getBlogByLink(link);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(response.getBody(), getBlogDto());
  }

  @Test
  void getBlog_ByLink_WhenStatusIsLive() {

    // given
    String link = "my_blog";
    String status = "LIVE";
    BlogEntity blogEntity = getBlogEntity(link, status);
    // when
    when(blogRepository.findByBlogTitleLink(anyString())).thenReturn(Optional.of(blogEntity));
    when(blogMapper.mapEntityToDto(any(BlogEntity.class))).thenReturn(getBlogDto());
    // then
    ResponseEntity<BlogDto> response = sut.getBlogByLink(link);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(response.getBody(), getBlogDto());
  }

  @Test
  void getBlogsByPage_WhenNotAdmin_HasBlogs() {

    // given
    when(userAuthenticationService.isAdmin()).thenReturn(false);
    List<BlogEntity> blogs = Collections.singletonList(getBlogEntity("active blog", "LIVE"));
    when(blogRepository.findBlogsByStatusInOrderByPublishedOnDesc(anyList())).thenReturn(blogs);
    when(blogMapper.getBlogSummary(any(BlogEntity.class))).thenReturn(getBlogSummary());

    // when
    Response response = sut.getBlogsByPage(1, 1);

    // then
    assertNotNull(response.getMessage());
    List<BlogSummary> blogSummaryList = (ArrayList) response.getMessage();
    assertEquals(1, blogSummaryList.size());
    List<BlogSummary> expectedBlogSummaryList = List.of(getBlogSummary());
    assertTrue(expectedBlogSummaryList.containsAll(blogSummaryList));
  }

  @Test
  void getBlogsByPage_ReturnsEmptyList_WhenNoBlogsFound() {

    // given
    when(userAuthenticationService.isAdmin()).thenReturn(false);
    List<BlogEntity> blogs = Collections.emptyList();
    when(blogRepository.findBlogsByStatusInOrderByPublishedOnDesc(anyList())).thenReturn(blogs);

    // when
    Response response = sut.getBlogsByPage(1, 1);

    // then
    assertNotNull(response.getMessage());
    List<BlogSummary> blogSummaryList = (ArrayList) response.getMessage();
    assertEquals(0, blogSummaryList.size());
  }

  @Test
  void getBlogsByPage_ReturnsAllBlogs_WhenUserIsAdmin() {

    // given
    when(userAuthenticationService.isAdmin()).thenReturn(true);
    ArrayList<BlogEntity> blogs = new ArrayList<>();
    blogs.add(getBlogEntity("active blog", "LIVE"));
    blogs.add(getBlogEntity("pending blog", "PENDING"));
    when(blogRepository.findBlogsByStatusInOrderByPublishedOnDesc(anyList())).thenReturn(blogs);
    when(blogMapper.getBlogSummary(any(BlogEntity.class))).thenReturn(getBlogSummary());

    // when
    Response response = sut.getBlogsByPage(1, 1);

    // then
    assertNotNull(response.getMessage());
    List<BlogSummary> blogSummaryList = (ArrayList) response.getMessage();
    assertEquals(2, blogSummaryList.size());
    List<BlogSummary> expectedBlogSummaryList = List.of(getBlogSummary(), getBlogSummary());
    assertTrue(expectedBlogSummaryList.containsAll(blogSummaryList));
  }

  @Test
  void createBlog_WhenUserNotAdmin() throws Exception {
    // given
    BlogDto blogDto = BlogDto.builder().build();

    // when
    when(userAuthenticationService.isAdmin()).thenReturn(false);

    // then
    Response response = sut.createBlog(blogDto);

    assertNotNull(response);
    assertEquals("FAILURE", response.getResponseCode());
    assertEquals("Could not create blog.", response.getResponseMessage());
  }

  @Test
  public void createBlog_Success_When_User_Is_Admin() throws Exception {

    // given
    BlogDto blogDto =
        BlogDto.builder()
            .blogContent(
                "This has more than 50 words. A quick brown fox jumps"
                    + "over a lazy dog. This is great. I am writing junit 5 test cases.")
            .build();

    // when
    when(userAuthenticationService.isAdmin()).thenReturn(true);
    when(blogMapper.mapDtoToEntity(any(BlogDto.class))).thenReturn(BlogEntity.builder().build());
    when(userAuthenticationService.getLoggedInUserDetails()).thenReturn("");
    when(authorService.fetchAuthorIdByUserName(anyString()))
        .thenReturn(AuthorEntity.builder().build());
    when(blogRepository.save(any(BlogEntity.class)))
        .thenReturn(BlogEntity.builder().id(1L).build());

    // then

    Response response = sut.createBlog(blogDto);

    assertEquals("200", response.getResponseCode());
    assertEquals("Blog Uploaded Successfully!", response.getResponseMessage());
    assertEquals("1", response.getMessage());
  }

  @Test
  public void createBlog_Fail_UserIsAdmin_Content_Less_Than_50() throws Exception {

    // given
    BlogDto blogDto = BlogDto.builder().blogContent("This").build();

    // when
    when(userAuthenticationService.isAdmin()).thenReturn(true);
    when(blogMapper.mapDtoToEntity(any(BlogDto.class))).thenReturn(BlogEntity.builder().build());

    // then

    Exception thrown =
        Assertions.assertThrows(
            Exception.class,
            () -> {
              sut.createBlog(blogDto);
            });

    Assertions.assertEquals("Blog is too short.", thrown.getMessage());
  }

  @Test
  void softDeleteBlog_WhenUserNotAdmin() {

    // given
    String blogId = "1";
    when(userAuthenticationService.isAdmin()).thenReturn(false);

    // when
    Response response = sut.softDeleteBlog(blogId);

    // then
    assertEquals("Could not delete blog.", response.getMessage());
    assertEquals("FAILURE", response.getResponseCode());
  }

  @Test
  void softDeleteBlog_WhenUserIsAdmin() {
    // given
    String blogId = "1";
    when(userAuthenticationService.isAdmin()).thenReturn(true);
    // when
    Response response = sut.softDeleteBlog(blogId);
    // then
    verify(blogRepository, times(1)).deleteBlog(anyLong());
    assertEquals("Blog is deleted.", response.getMessage());
    assertEquals("SUCCESS", response.getResponseCode());
  }

  @Test
  void approveBlog_WhenUserNotAdmin() {
    // given
    String blogId = "1";
    when(userAuthenticationService.isAdmin()).thenReturn(false);
    // when
    Response response = sut.approveBlog(blogId);
    // then

    assertEquals("Sorry! Could not approve blog.", response.getMessage());
    assertEquals("FAILURE", response.getResponseCode());
  }

  @Test
  void approveBlog_WhenUserIsAdmin() {
    // given
    String blogId = "1";
    when(userAuthenticationService.isAdmin()).thenReturn(true);

    // when
    Response response = sut.approveBlog(blogId);
    // then

    verify(blogRepository).approveBlog(anyLong());
    assertEquals("Blog is approved.", response.getMessage());
    assertEquals("SUCCESS", response.getResponseCode());
  }
}

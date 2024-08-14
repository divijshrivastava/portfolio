package tech.divij.mapper;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tech.divij.constants.BlogStatus;
import tech.divij.constants.Constants;
import tech.divij.dto.BlogDto;
import tech.divij.dto.BlogSummary;
import tech.divij.entity.AuthorEntity;
import tech.divij.entity.BlogEntity;
import tech.divij.util.DateUtil;

@Component
@Slf4j
@Scope("singleton")
public class BlogMapper {

  @Autowired DateUtil dateUtil;

  @Value("${file.server.url}")
  private String fileServerUrl;

  public BlogDto mapEntityToDto(BlogEntity blogEntity) {

    return BlogDto.builder()
        .authorName(
            blogEntity.getAuthorId().getFirstName()
                + Constants.EMPTY_SPACE
                + blogEntity.getAuthorId().getLastName())
        .blogContent(blogEntity.getContent())
        .blogHeading(blogEntity.getHeading())
        .blogImageSrc(fileServerUrl + Constants.FORWARD_SLASH + blogEntity.getCoverPhotoId())
        .authorImageSrc(
            fileServerUrl + Constants.FORWARD_SLASH + blogEntity.getAuthorId().getPhotoId())
        .publishedDate(dateUtil.mapDate(blogEntity.getPublishedOn()))
        .readTime(blogEntity.getMinutesToRead() + " min read")
        .blogSummary(blogEntity.getBlogSummary())
        .blogStatus(blogEntity.getStatus())
        .blogTitleLink(blogEntity.getBlogTitleLink())
        .id(blogEntity.getId())
        .build();
  }

  public BlogSummary getBlogSummary(BlogEntity blogEntity) {

    log.debug("inside getBlogSummary.");

    BlogSummary blog =
        BlogSummary.builder()
            .id(blogEntity.getId())
            .authorName(
                blogEntity.getAuthorId().getFirstName()
                    + Constants.EMPTY_SPACE
                    + blogEntity.getAuthorId().getLastName())
            .blogContent(blogEntity.getBlogSummary())
            .blogHeading(blogEntity.getHeading())
            .authorImageSrc(
                fileServerUrl + Constants.FORWARD_SLASH + blogEntity.getAuthorId().getPhotoId())
            .blogImageSrc(
                fileServerUrl + Constants.FORWARD_SLASH + blogEntity.getBlogSummaryImageId())
            .publishedDate(dateUtil.mapDate(blogEntity.getPublishedOn()))
            .readTime(blogEntity.getMinutesToRead() + Constants.EMPTY_SPACE + Constants.MIN_READ)
            .blogStatus(blogEntity.getStatus())
            .blogTitleLink(blogEntity.getBlogTitleLink())
            .build();

    log.info("Sending blog {}", blog);

    return blog;
  }

  public BlogEntity mapDtoToEntity(BlogDto blog) {

    return BlogEntity.builder()
        .authorId(AuthorEntity.builder().authorId(1).build())
        .content(blog.getBlogContent())
        .heading(blog.getBlogHeading())
        .coverPhotoId(blog.getBlogImageSrc())
        .minutesToRead(Integer.parseInt(blog.getReadTime()))
        .blogSummaryImageId(Integer.parseInt(blog.getBlogImageSrc()))
        .blogSummary(blog.getBlogSummary())
        .status(BlogStatus.PENDING.name())
        .blogTitleLink(
            blog.getBlogHeading().replaceAll("[\\W_]+", "-")
                + "-"
                + (LocalDateTime.now().toString()).replaceAll("[\\W_]+", "-"))
        .build();
  }
}

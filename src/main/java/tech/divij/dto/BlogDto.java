package tech.divij.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class BlogDto {

  private long id;
  private String blogContent;
  private String blogHeading;
  private String authorName;
  private String publishedDate;
  private String readTime;
  private String blogSummary;
  private String authorImageSrc;
  private String blogImageSrc;
  private String blogStatus;
  private String blogTitleLink;
}

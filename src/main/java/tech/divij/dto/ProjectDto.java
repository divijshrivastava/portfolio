package tech.divij.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProjectDto {

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

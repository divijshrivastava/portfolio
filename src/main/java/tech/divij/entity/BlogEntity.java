package tech.divij.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name = "BLOG")
@NoArgsConstructor
@AllArgsConstructor
public class BlogEntity {

  @Id
  @Column(name = "BLOG_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PUBLISH_TIMESTAMP")
  private LocalDateTime publishedOn;

  @Column(name = "HEADING")
  private String heading;

  @Column(name = "MINUTES_TO_READ")
  private Integer minutesToRead;

  @Column(name = "COVER_PHOTO_ID")
  private String coverPhotoId;

  @Column(name = "CONTENT")
  private String content;

  @Column(name = "UPVOTES")
  private Integer upVotes;

  @Column(name = "VIEWS")
  private Integer views;

  @Column(name = "COVER_PHOTO_ID_DESCRIPTION")
  private String coverPhotoIdDescription;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "BLOG_SUMMARY")
  private String blogSummary;

  @ManyToOne
  @JoinColumn(name = "AUTHOR_ID")
  private AuthorEntity authorId;

  @Column(name = "BLOG_SUMMARY_IMAGE_ID")
  private Integer blogSummaryImageId;

  @Column(name = "BLOG_TITLE_LINK")
  private String blogTitleLink;
}

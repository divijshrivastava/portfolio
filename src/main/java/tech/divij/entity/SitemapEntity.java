package tech.divij.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.divij.dto.ChangeFrequency;

@Entity
@Table(name = "SITEMAP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SitemapEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "URL", nullable = false)
  private String url;

  @Column(name = "PRIORITY", nullable = false)
  private Double priority;

  @Column(name = "LAST_MODIFIED", nullable = false)
  private LocalDateTime lastModified;

  @Column(name = "CHANGE_FREQUENCY", nullable = false)
  private ChangeFrequency changeFrequency;

  @Column(name = "STATUS", nullable = false)
  private String status;
}

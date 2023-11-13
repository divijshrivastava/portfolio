package tech.divij.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SitemapEntryDTO {
  private String loc;
  private LocalDateTime lastMod;
  private ChangeFrequency changeFreq;
  private Double priority;
}

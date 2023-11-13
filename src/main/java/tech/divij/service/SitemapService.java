package tech.divij.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;
import tech.divij.dao.SitemapEntity;
import tech.divij.repository.SitemapRepository;

@Service
public class SitemapService {

  private final SitemapRepository sitemapRepository;

  public SitemapService(SitemapRepository sitemapRepository) {
    this.sitemapRepository = sitemapRepository;
  }

  public List<SitemapEntity> getAllUrls() {
    return sitemapRepository.findAllActiveSitemaps();
  }

  public void addUrl(SitemapEntity sitemapEntity) {
    sitemapRepository.save(sitemapEntity);
  }

  public void deleteAllUrls() {
    sitemapRepository.deleteAll();
  }

  public String getSiteMap() {
    List<SitemapEntity> urls = getAllUrls();
    StringBuilder sitemapBuilder = new StringBuilder();
    sitemapBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    sitemapBuilder.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    for (SitemapEntity url : urls) {
      sitemapBuilder.append("<url>\n");
      sitemapBuilder.append("<loc>").append(url.getUrl()).append("</loc>\n");
      sitemapBuilder
          .append("<lastmod>")
          .append(
              DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
                  .format(url.getLastModified()))
          .append("</lastmod>\n");
      sitemapBuilder
          .append("<changefreq>")
          .append(url.getChangeFrequency().getValue())
          .append("</changefreq>\n");
      sitemapBuilder.append("<priority>").append(url.getPriority()).append("</priority>\n");
      sitemapBuilder.append("</url>\n");
    }
    sitemapBuilder.append("</urlset>\n");
    return sitemapBuilder.toString();
  }
}

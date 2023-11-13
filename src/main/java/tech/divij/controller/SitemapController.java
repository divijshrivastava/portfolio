package tech.divij.controller;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tech.divij.service.SitemapService;

@Controller
public class SitemapController {

  private final SitemapService sitemapService;

  @Autowired
  public SitemapController(SitemapService sitemapService) {
    this.sitemapService = sitemapService;
  }

  @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<String> getSitemapXml() {
    // Generate the sitemap XML content dynamically
    String sitemapXml = sitemapService.getSiteMap();

    // Set the caching headers to cache the file for one day
    HttpHeaders headers = new HttpHeaders();
    headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS).cachePublic());
    headers.setExpires(ZonedDateTime.now().plusDays(1).toInstant().toEpochMilli());

    // Return the sitemap XML as a string with the caching headers
    return ResponseEntity.ok().headers(headers).body(sitemapXml);
  }
}

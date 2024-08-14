package tech.divij.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.divij.entity.SitemapEntity;

@Repository
public interface SitemapRepository extends JpaRepository<SitemapEntity, Long> {

  @Query(value = "Select * from SITEMAP where STATUS='ACTIVE'", nativeQuery = true)
  List<SitemapEntity> findAllActiveSitemaps();
}

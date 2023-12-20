package tech.divij.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import tech.divij.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

  @Modifying
  @Transactional
  @Query(value = "update BLOG set STATUS='DELETED' where BLOG_ID=?1", nativeQuery = true)
  void deleteBlog(long id);

  @Modifying
  @Transactional
  @Query(value = "update BLOG set STATUS='LIVE' where BLOG_ID=?1", nativeQuery = true)
  void approveBlog(long id);

  List<BlogEntity> findBlogsByStatusInOrderByPublishedOnDesc(List<String> status);

  Optional<BlogEntity> findByBlogTitleLink(String link);
}

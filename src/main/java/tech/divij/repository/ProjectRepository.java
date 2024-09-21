package tech.divij.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tech.divij.entity.project.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

  @Modifying
  @Transactional
  @Query(value = "update PROJECT set STATUS='ACTIVE' where ID=?1", nativeQuery = true)
  void approveProject(long id);

  @Query("SELECT p FROM PROJECT p WHERE p.status IN :statuses AND p.isActive = true ORDER BY p.publishedOn DESC")
  List<ProjectEntity> findActiveProjectsByStatusesOrderByPublishedDate(@Param("statuses") List<String> statuses);

}

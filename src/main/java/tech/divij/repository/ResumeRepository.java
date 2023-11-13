package tech.divij.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.divij.dao.ResumeEntity;

public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {

  @Query(
      value =
          "select ID,FILE_ID,TIMESTAMP,STATUS,COMMENT from RESUME_DETAILS where STATUS='ACTIVE' order by TIMESTAMP DESC limit 1 ",
      nativeQuery = true)
  Optional<ResumeEntity> findTopByOrderBySaveTimeDesc();
}

package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.divij.entity.ProjectEntity;
import tech.divij.entity.YtVideoEntity;

public interface YtVideoProjectRepository extends JpaRepository<YtVideoEntity, Long> {

  YtVideoEntity findByProjectId(ProjectEntity projectEntity);
}

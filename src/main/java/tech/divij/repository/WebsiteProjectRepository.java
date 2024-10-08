package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.divij.entity.project.ProjectEntity;
import tech.divij.entity.project.WebsiteProjectEntity;

public interface WebsiteProjectRepository extends JpaRepository<WebsiteProjectEntity, Long> {

  WebsiteProjectEntity findByProjectId(ProjectEntity projectEntity);
}

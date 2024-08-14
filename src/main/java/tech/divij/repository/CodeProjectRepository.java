package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.divij.entity.project.CodeProjectEntity;
import tech.divij.entity.project.ProjectEntity;

public interface CodeProjectRepository extends JpaRepository<CodeProjectEntity, Long> {

  CodeProjectEntity findByProjectId(ProjectEntity projectEntity);

}

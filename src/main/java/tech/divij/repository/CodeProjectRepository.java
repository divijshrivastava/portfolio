package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.divij.entity.CodeProjectEntity;
import tech.divij.entity.ProjectEntity;

public interface CodeProjectRepository extends JpaRepository<CodeProjectEntity, Long> {

  CodeProjectEntity findByProjectId(ProjectEntity projectEntity);

}

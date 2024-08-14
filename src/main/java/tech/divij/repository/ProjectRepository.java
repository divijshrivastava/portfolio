package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.divij.entity.project.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

}

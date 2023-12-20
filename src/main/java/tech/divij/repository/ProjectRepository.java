package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.divij.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

}

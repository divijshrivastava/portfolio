package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.divij.dao.CodeProjectEntity;

public interface CodeProjectRepository extends JpaRepository<CodeProjectEntity, Long> {

}

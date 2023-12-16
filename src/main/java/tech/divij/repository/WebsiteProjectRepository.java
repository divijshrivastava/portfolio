package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.divij.dao.WebsiteProjectEntity;

public interface WebsiteProjectRepository extends JpaRepository<WebsiteProjectEntity, Long> {

}

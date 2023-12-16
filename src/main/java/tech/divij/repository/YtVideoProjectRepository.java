package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.divij.dao.YtVideoEntity;

public interface YtVideoProjectRepository extends JpaRepository<YtVideoEntity, Long> {

}

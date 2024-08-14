package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.divij.entity.UserContactEntity;

@Repository
public interface UserContactRepository extends JpaRepository<UserContactEntity, Long> {

}

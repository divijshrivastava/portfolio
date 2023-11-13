package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.divij.dao.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {}

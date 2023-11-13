package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.divij.dao.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

  UserEntity findByUserName(String username);
}

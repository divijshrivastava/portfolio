package tech.divij.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.divij.entity.UserCredEntity;

@Repository
public interface UserCredRepo extends JpaRepository<UserCredEntity, Integer> {

  List<UserCredEntity> findByUserName(String userName);

  @Query(
      value =
          "Select * from USER_CREDENTIALS where USER_NAME = :userName and USER_PASSWORD = :password",
      nativeQuery = true)
  Collection<Object> isUserValid(
      @Param("userName") String userName, @Param("password") String password);
}

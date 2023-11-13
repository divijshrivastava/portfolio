package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.divij.dao.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

  @Query(
      value =
          "select AUTHOR_ID, AUTHOR_USER_NAME, AUTHOR_PHOTO_ID, AUTHOR_EMAIL, AUTHOR_PHONE, AUTHOR_PHONE_COUNTRY_CODE, AUTHOR_FIRST_NAME, AUTHOR_LAST_NAME, AUTHOR_MIDDLE_NAME from AUTHOR where AUTHOR_USER_NAME = ?1",
      nativeQuery = true)
  AuthorEntity findByUserName(String userName);
}

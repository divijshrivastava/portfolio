package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.divij.dao.UserContact;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {}

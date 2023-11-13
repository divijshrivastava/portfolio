package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.divij.dao.FileEntity;

@Repository
public interface UploadRepository extends JpaRepository<FileEntity, Integer> {}

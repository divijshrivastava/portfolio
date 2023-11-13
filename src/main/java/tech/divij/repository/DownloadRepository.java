package tech.divij.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.divij.dao.FileEntity;

public interface DownloadRepository extends JpaRepository<FileEntity, Integer> {}

package tech.divij.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "FILE_DETAILS")
public class FileEntity {

  @Id
  @Column(name = "FILE_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer fileId;

  @Column(name = "FILE_TYPE")
  private String fileType;

  @Column(name = "FILE_EXTENSION")
  private String fileExtension;

  @Column(name = "ORIGINAL_FILE_NAME")
  private String originalFileName;

  @Column(name = "FILE_PATH")
  private String filePath;

  @Column(name = "FILE_SIZE")
  private Long fileSize;

  @Column(name = "SAVE_TIME")
  private LocalDateTime saveTime;

  @Column(name = "ENCRYPTED")
  private String encrypted;

  public Integer getFileId() {
    return fileId;
  }

  public void setFileId(Integer fileId) {
    this.fileId = fileId;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getFileExtension() {
    return fileExtension;
  }

  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  public String getOriginalFileName() {
    return originalFileName;
  }

  public void setOriginalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public Long getFileSize() {
    return fileSize;
  }

  public void setFileSize(Long fileSize) {
    this.fileSize = fileSize;
  }

  public LocalDateTime getSaveTime() {
    return saveTime;
  }

  public void setSaveTime(LocalDateTime saveTime) {
    this.saveTime = saveTime;
  }

  public String getEncrypted() {
    return encrypted;
  }

  public void setEncrypted(String encrypted) {
    this.encrypted = encrypted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FileEntity that = (FileEntity) o;
    return fileId.equals(that.fileId)
        && fileType.equals(that.fileType)
        && fileExtension.equals(that.fileExtension)
        && originalFileName.equals(that.originalFileName)
        && filePath.equals(that.filePath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileId, fileType, fileExtension, originalFileName, filePath);
  }

  @Override
  public String toString() {
    return "FileEntity{"
        + "fileId="
        + fileId
        + ", fileType='"
        + fileType
        + '\''
        + ", fileExtension='"
        + fileExtension
        + '\''
        + ", originalFileName='"
        + originalFileName
        + '\''
        + ", filePath='"
        + filePath
        + '\''
        + '}';
  }
}

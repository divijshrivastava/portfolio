package tech.divij.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "RESUME_DETAILS")
public class ResumeEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "FILE_ID")
  private FileEntity fileId;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "COMMENT")
  private String comment;

  @Column(name = "TIMESTAMP")
  private LocalDateTime saveTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public FileEntity getFileId() {
    return fileId;
  }

  public void setFileId(FileEntity fileId) {
    this.fileId = fileId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public LocalDateTime getSaveTime() {
    return saveTime;
  }

  public void setSaveTime(LocalDateTime saveTime) {
    this.saveTime = saveTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ResumeEntity that = (ResumeEntity) o;

    if (!Objects.equals(id, that.id)) {
      return false;
    }
    if (!Objects.equals(fileId, that.fileId)) {
      return false;
    }
    if (!Objects.equals(status, that.status)) {
      return false;
    }
    if (!Objects.equals(comment, that.comment)) {
      return false;
    }
    return Objects.equals(saveTime, that.saveTime);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (fileId != null ? fileId.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (comment != null ? comment.hashCode() : 0);
    result = 31 * result + (saveTime != null ? saveTime.hashCode() : 0);
    return result;
  }
}

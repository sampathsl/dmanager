package com.github.sampathsl.dmanager.model;

import com.github.sampathsl.dmanager.util.DownloadStatus;
import com.github.sampathsl.dmanager.util.LocalDateTimeConverter;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "Download_Task_Log", schema = "public")
public class DownloadTaskLog implements Serializable {

  private static final long serialVersionUID = -8295130262875892440L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = new Long(0l);

  @Version private Long version = new Long(0l);

  @NotNull private Long taskId = new Long(0l);

  @NotNull
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime created = LocalDateTime.now();

  @NotNull private String downloadStatus = DownloadStatus.ERROR.getStatus();

  @NotNull private float progress = 0f;

  @Nullable
  private String errorLog = "Error";

  protected DownloadTaskLog() {
    super();
  }

  public DownloadTaskLog(
      Long taskId, LocalDateTime created, String downloadStatus, float progress, String errorLog) {
    this.setTaskId(taskId);
    this.setCreated(created);
    this.setDownloadStatus(downloadStatus);
    this.setProgress(progress);
    this.setErrorLog(errorLog);
  }

  public DownloadTaskLog(
      @NotNull Long version,
      @NotNull Long taskId,
      @NotNull LocalDateTime created,
      @NotNull String downloadStatus,
      @NotNull float progress,
      String errorLog) {
    this.setVersion(version);
    this.setTaskId(taskId);
    this.setCreated(created);
    this.setDownloadStatus(downloadStatus);
    this.setProgress(progress);
    this.setErrorLog(errorLog);
  }

  public Long getId() {
    return id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public String getDownloadStatus() {
    return downloadStatus;
  }

  public void setDownloadStatus(String downloadStatus) {
    this.downloadStatus = downloadStatus;
  }

  public float getProgress() {
    return progress;
  }

  public void setProgress(float progress) {
    this.progress = progress;
  }

  public String getErrorLog() {
    return errorLog;
  }

  public void setErrorLog(String errorLog) {
    this.errorLog = errorLog;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    DownloadTaskLog other = (DownloadTaskLog) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (taskId == null) {
      return other.taskId == null;
    } else return taskId.equals(other.taskId);
  }

  @Override
  public String toString() {
    return "DownloadTaskLog{"
        + "id="
        + id
        + ", version="
        + version
        + ", taskId="
        + taskId
        + ", created="
        + created
        + ", downloadStatus="
        + downloadStatus
        + ", progress="
        + progress
        + '}';
  }
}

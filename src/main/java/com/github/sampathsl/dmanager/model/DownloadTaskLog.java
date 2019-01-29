package com.github.sampathsl.dmanager.model;

import com.github.sampathsl.dmanager.util.DownloadStatus;
import com.github.sampathsl.dmanager.util.LocalDateTimeConverter;
import io.micrometer.core.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Download_Task_Log", schema = "public")
public class DownloadTaskLog implements Serializable {

  private static final long serialVersionUID = -8295130262875892440L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Version private Long version;

  @NotNull private Long taskId;

  @NotNull
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime created;

  @NotNull private String downloadStatus;

  @NotNull private float progress;

  @Nullable
  private String errorLog;

  protected DownloadTaskLog() {
    super();
    taskId = 0L;
    created = LocalDateTime.now();
    downloadStatus = DownloadStatus.DOWNLOADING.getStatus();
    progress = 0F;
    errorLog = "Error";
  }

  public DownloadTaskLog(
      @NotNull Long taskId,
      @NotNull LocalDateTime created,
      @NotNull String downloadStatus,
      @NotNull float progress, String errorLog) {
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
      @NotNull float progress, String errorLog) {
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

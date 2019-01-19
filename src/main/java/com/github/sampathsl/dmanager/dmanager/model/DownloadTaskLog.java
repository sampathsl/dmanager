package com.github.sampathsl.dmanager.dmanager.model;

import com.github.sampathsl.dmanager.dmanager.util.DownloadStatus;
import com.github.sampathsl.dmanager.dmanager.util.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Download_Task_Log", schema = "public")
public class DownloadTaskLog implements Serializable {

  private static final long serialVersionUID = -8295130262875892440L;

  @Id @GeneratedValue private Long id;

  @Version private Long version;

  @NotNull private Long taskId;

  @NotNull
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime created;

  @NotNull private DownloadStatus downloadStatus;

  @NotNull private float progress;

  // TODO - add more

  public DownloadTaskLog(
      @NotNull Long taskId,
      @NotNull LocalDateTime created,
      @NotNull DownloadStatus downloadStatus,
      @NotNull float progress) {
    this.setTaskId(taskId);
    this.setCreated(created);
    this.setDownloadStatus(downloadStatus);
    this.setProgress(progress);
  }

  public DownloadTaskLog(
      @NotNull Long version,
      @NotNull Long taskId,
      @NotNull LocalDateTime created,
      @NotNull DownloadStatus downloadStatus,
      @NotNull float progress) {
    this.setVersion(version);
    this.setTaskId(taskId);
    this.setCreated(created);
    this.setDownloadStatus(downloadStatus);
    this.setProgress(progress);
  }

  public Long getId() {
    return id;
  }

  public Long getVersion() {
    return version;
  }

  private void setVersion(Long version) {
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

  private void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public DownloadStatus getDownloadStatus() {
    return downloadStatus;
  }

  private void setDownloadStatus(DownloadStatus downloadStatus) {
    this.downloadStatus = downloadStatus;
  }

  public float getProgress() {
    return progress;
  }

  private void setProgress(float progress) {
    this.progress = progress;
  }
}

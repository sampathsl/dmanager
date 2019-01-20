package com.github.sampathsl.dmanager.dmanager.dto;

import com.github.sampathsl.dmanager.dmanager.util.DownloadStatus;

import java.time.LocalDateTime;

public class DownloadTaskLogDto {

  private Long version;

  private Long taskId;

  private LocalDateTime created;

  private DownloadStatus downloadStatus;

  private float progress;

  // TODO - add more

  public DownloadTaskLogDto() {}

  public DownloadTaskLogDto(
      Long taskId, LocalDateTime created, DownloadStatus downloadStatus, float progress) {
    this.setTaskId(taskId);
    this.setCreated(created);
    this.setDownloadStatus(downloadStatus);
    this.setProgress(progress);
  }

  public DownloadTaskLogDto(
      Long version,
      Long taskId,
      LocalDateTime created,
      DownloadStatus downloadStatus,
      float progress) {
    this.setVersion(version);
    this.setTaskId(taskId);
    this.setCreated(created);
    this.setDownloadStatus(downloadStatus);
    this.setProgress(progress);
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

  private void setTaskId(Long taskId) {
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

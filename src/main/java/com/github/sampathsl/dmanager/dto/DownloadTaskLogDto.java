package com.github.sampathsl.dmanager.dto;

import com.github.sampathsl.dmanager.util.DownloadStatus;

import java.time.LocalDateTime;

public class DownloadTaskLogDto {

  private Long version;

  private Long taskId;

  private LocalDateTime created;

  private DownloadStatus downloadStatus;

  private float progress;

  private String errorLog;

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

  public DownloadStatus getDownloadStatus() {
    return downloadStatus;
  }

  public void setDownloadStatus(DownloadStatus downloadStatus) {
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
}

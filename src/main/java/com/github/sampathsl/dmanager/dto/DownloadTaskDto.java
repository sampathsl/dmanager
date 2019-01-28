package com.github.sampathsl.dmanager.dto;

import com.github.sampathsl.dmanager.util.FileSizeStatus;
import com.github.sampathsl.dmanager.util.FileSpeedStatus;

import java.time.LocalDateTime;

public class DownloadTaskDto {

  private Long id;

  private Long version;

  private String fileSource;

  private String fileDestination;

  private String protocol;

  private LocalDateTime started;

  private LocalDateTime ended;

  private long fileTotalSize;

  private float failurePercentage;

  private FileSpeedStatus fileSpeedStatus;

  private FileSizeStatus fileSizeStatus;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getFileSource() {
    return fileSource;
  }

  public void setFileSource(String fileSource) {
    this.fileSource = fileSource;
  }

  public String getFileDestination() {
    return fileDestination;
  }

  public void setFileDestination(String fileDestination) {
    this.fileDestination = fileDestination;
  }

  public LocalDateTime getStarted() {
    return started;
  }

  public void setStarted(LocalDateTime started) {
    this.started = started;
  }

  public LocalDateTime getEnded() {
    return ended;
  }

  public void setEnded(LocalDateTime ended) {
    this.ended = ended;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public long getFileTotalSize() {
    return fileTotalSize;
  }

  public void setFileTotalSize(long fileTotalSize) {
    this.fileTotalSize = fileTotalSize;
  }

  public float getFailurePercentage() {
    return failurePercentage;
  }

  public void setFailurePercentage(float failurePercentage) {
    this.failurePercentage = failurePercentage;
  }

  public FileSpeedStatus getFileSpeedStatus() {
    return fileSpeedStatus;
  }

  public void setFileSpeedStatus(FileSpeedStatus fileSpeedStatus) {
    this.fileSpeedStatus = fileSpeedStatus;
  }

  public FileSizeStatus getFileSizeStatus() {
    return fileSizeStatus;
  }

  public void setFileSizeStatus(FileSizeStatus fileSizeStatus) {
    this.fileSizeStatus = fileSizeStatus;
  }
}

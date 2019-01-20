package com.github.sampathsl.dmanager.dmanager.dto;

import com.github.sampathsl.dmanager.dmanager.util.FileSizeStatus;
import com.github.sampathsl.dmanager.dmanager.util.FileSpeedStatus;

import java.time.LocalDateTime;

public class DownloadTaskDto {

  private Long id;

  private Long version;

  private String fileSource;

  private String fileDestination;

  private LocalDateTime started;

  private LocalDateTime ended;

  private String Protocol;

  private long fileTotalSize;

  private float failurePercentage;

  private FileSpeedStatus fileSpeedStatus;

  private FileSizeStatus fileSizeStatus;

  public DownloadTaskDto() {}

  public DownloadTaskDto(
      String fileSource,
      String fileDestination,
      LocalDateTime started,
      LocalDateTime ended,
      String protocol,
      long fileTotalSize,
      float failurePercentage,
      FileSpeedStatus fileSpeedStatus,
      FileSizeStatus fileSizeStatus) {
    this.setFileSource(fileSource);
    this.setFileDestination(fileDestination);
    this.setStarted(started);
    this.setEnded(ended);
    this.setProtocol(protocol);
    this.setFileTotalSize(fileTotalSize);
    this.setFailurePercentage(failurePercentage);
    this.setFileSpeedStatus(fileSpeedStatus);
    this.setFileSizeStatus(fileSizeStatus);
  }

  public DownloadTaskDto(
      Long version,
      String fileSource,
      String fileDestination,
      LocalDateTime started,
      LocalDateTime ended,
      String protocol,
      long fileTotalSize,
      float failurePercentage,
      FileSpeedStatus fileSpeedStatus,
      FileSizeStatus fileSizeStatus) {
    this.setVersion(version);
    this.setFileSource(fileSource);
    this.setFileDestination(fileDestination);
    this.setStarted(started);
    this.setEnded(ended);
    this.setProtocol(protocol);
    this.setFileTotalSize(fileTotalSize);
    this.setFailurePercentage(failurePercentage);
    this.setFileSpeedStatus(fileSpeedStatus);
    this.setFileSizeStatus(fileSizeStatus);
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

  public String getFileSource() {
    return fileSource;
  }

  private void setFileSource(String fileSource) {
    this.fileSource = fileSource;
  }

  public String getFileDestination() {
    return fileDestination;
  }

  private void setFileDestination(String fileDestination) {
    this.fileDestination = fileDestination;
  }

  public LocalDateTime getStarted() {
    return started;
  }

  private void setStarted(LocalDateTime started) {
    this.started = started;
  }

  public LocalDateTime getEnded() {
    return ended;
  }

  private void setEnded(LocalDateTime ended) {
    this.ended = ended;
  }

  public String getProtocol() {
    return Protocol;
  }

  private void setProtocol(String protocol) {
    Protocol = protocol;
  }

  public long getFileTotalSize() {
    return fileTotalSize;
  }

  private void setFileTotalSize(long fileTotalSize) {
    this.fileTotalSize = fileTotalSize;
  }

  public float getFailurePercentage() {
    return failurePercentage;
  }

  private void setFailurePercentage(float failurePercentage) {
    this.failurePercentage = failurePercentage;
  }

  public FileSpeedStatus getFileSpeedStatus() {
    return fileSpeedStatus;
  }

  private void setFileSpeedStatus(FileSpeedStatus fileSpeedStatus) {
    this.fileSpeedStatus = fileSpeedStatus;
  }

  public FileSizeStatus getFileSizeStatus() {
    return fileSizeStatus;
  }

  private void setFileSizeStatus(FileSizeStatus fileSizeStatus) {
    this.fileSizeStatus = fileSizeStatus;
  }
}

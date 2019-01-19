package com.github.sampathsl.dmanager.dmanager.model;

import com.github.sampathsl.dmanager.dmanager.util.FileSizeStatus;
import com.github.sampathsl.dmanager.dmanager.util.FileSpeedStatus;
import com.github.sampathsl.dmanager.dmanager.util.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Download_Task", schema = "public")
public class DownloadTask implements Serializable {

  private static final long serialVersionUID = -271303463116543604L;

  @Id @GeneratedValue private Long id;

  @Version private Long version;

  @NotNull private String fileSource;

  @NotNull private String fileDestination;

  @NotNull
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime started;

  @NotNull
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime ended;

  @NotNull private String Protocol;

  @NotNull private long fileTotalSize;

  @NotNull private float failurePercentage;

  @NotNull private FileSpeedStatus fileSpeedStatus;

  @NotNull private FileSizeStatus fileSizeStatus;

  public DownloadTask(
      @NotNull String fileSource,
      @NotNull String fileDestination,
      @NotNull LocalDateTime started,
      @NotNull LocalDateTime ended,
      @NotNull String protocol,
      @NotNull long fileTotalSize,
      @NotNull float failurePercentage,
      @NotNull FileSpeedStatus fileSpeedStatus,
      @NotNull FileSizeStatus fileSizeStatus) {
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

  public DownloadTask(
      @NotNull Long version,
      @NotNull String fileSource,
      @NotNull String fileDestination,
      @NotNull LocalDateTime started,
      @NotNull LocalDateTime ended,
      @NotNull String protocol,
      @NotNull long fileTotalSize,
      @NotNull float failurePercentage,
      @NotNull FileSpeedStatus fileSpeedStatus,
      @NotNull FileSizeStatus fileSizeStatus) {
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

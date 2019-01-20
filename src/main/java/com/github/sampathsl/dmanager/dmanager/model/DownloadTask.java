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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull private Long sessionId = 0l;

  @Version private Long version;

  @NotNull private String fileSource;

  @NotNull private String fileDestination;

  @NotNull private String protocol;

  @NotNull
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime started;

  @NotNull
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime ended;

  @NotNull private long fileTotalSize;

  @NotNull private float failurePercentage;

  @NotNull private FileSpeedStatus fileSpeedStatus;

  @NotNull private FileSizeStatus fileSizeStatus;

  protected DownloadTask() {
    super();
  }

  public DownloadTask(
      @NotNull Long sessionId,
      @NotNull String fileSource,
      @NotNull String fileDestination,
      @NotNull String protocol,
      @NotNull LocalDateTime started,
      LocalDateTime ended,
      long fileTotalSize,
      float failurePercentage,
      FileSpeedStatus fileSpeedStatus,
      FileSizeStatus fileSizeStatus) {
    this.setSessionId(sessionId);
    this.setFileSource(fileSource);
    this.setFileDestination(fileDestination);
    this.setProtocol(protocol);
    this.setStarted(started);
    this.setEnded(ended);
    this.setFileTotalSize(fileTotalSize);
    this.setFailurePercentage(failurePercentage);
    this.setFileSpeedStatus(fileSpeedStatus);
    this.setFileSizeStatus(fileSizeStatus);
  }

  public DownloadTask(
      @NotNull Long version,
      @NotNull Long sessionId,
      @NotNull String fileSource,
      @NotNull String fileDestination,
      @NotNull String protocol,
      @NotNull LocalDateTime started,
      LocalDateTime ended,
      long fileTotalSize,
      float failurePercentage,
      FileSpeedStatus fileSpeedStatus,
      FileSizeStatus fileSizeStatus) {
    this.setVersion(version);
    this.setSessionId(sessionId);
    this.setFileSource(fileSource);
    this.setFileDestination(fileDestination);
    this.setProtocol(protocol);
    this.setStarted(started);
    this.setEnded(ended);
    this.setFileTotalSize(fileTotalSize);
    this.setFailurePercentage(failurePercentage);
    this.setFileSpeedStatus(fileSpeedStatus);
    this.setFileSizeStatus(fileSizeStatus);
  }

  public Long getId() {
    return id;
  }

  public Long getSessionId() {
    return sessionId;
  }

  public void setSessionId(Long sessionId) {
    this.sessionId = sessionId;
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

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    DownloadTask other = (DownloadTask) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (sessionId == null) {
        return other.sessionId == null;
    } else return sessionId.equals(other.sessionId);
  }

  @Override
  public String toString() {
    return "DownloadTask{"
        + "id="
        + id
        + ", sessionId="
        + sessionId
        + ", version="
        + version
        + ", fileSource='"
        + fileSource
        + '\''
        + ", fileDestination='"
        + fileDestination
        + '\''
        + ", protocol='"
        + protocol
        + '\''
        + ", started="
        + started
        + ", ended="
        + ended
        + ", fileTotalSize="
        + fileTotalSize
        + ", failurePercentage="
        + failurePercentage
        + ", fileSpeedStatus="
        + fileSpeedStatus
        + ", fileSizeStatus="
        + fileSizeStatus
        + '}';
  }
}

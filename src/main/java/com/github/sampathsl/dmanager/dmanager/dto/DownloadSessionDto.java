package com.github.sampathsl.dmanager.dmanager.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DownloadSessionDto {

  private Long id;

  private Long version;

  private LocalDateTime created;

  private ArrayList<String> urls;

  private ArrayList<DownloadTaskDto> downloadTasks;

  public DownloadSessionDto() {}

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

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public ArrayList<String> getUrls() {
    return urls;
  }

  public void setUrls(ArrayList<String> urls) {
    this.urls = urls;
  }

  public ArrayList<DownloadTaskDto> getDownloadTasks() {
    return downloadTasks;
  }

  public void setDownloadTasks(ArrayList<DownloadTaskDto> downloadTasks) {
    this.downloadTasks = downloadTasks;
  }
}

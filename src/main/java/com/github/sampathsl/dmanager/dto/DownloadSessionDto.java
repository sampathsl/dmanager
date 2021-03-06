package com.github.sampathsl.dmanager.dto;

import java.util.ArrayList;

public class DownloadSessionDto {

  private Long id;

  private Long version;

  private ArrayList<String> urls;

  private ArrayList<DownloadTaskDto> downloadTasks;

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

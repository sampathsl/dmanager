package com.github.sampathsl.dmanager.dmanager.util;

public enum DownloadStatus {
  DOWNLOADING("Downloading"),
  PAUSED("Paused"),
  COMPLETED("Complete"),
  CANCELLED("Cancelled"),
  ERROR("Error");

  private final String status;

  DownloadStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}

package com.github.sampathsl.dmanager.util;

public enum FileSizeStatus {
  SMALL("Small"),
  BIG("Big"),
  UNKNOWN("Unknown");

  private final String status;

  FileSizeStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}

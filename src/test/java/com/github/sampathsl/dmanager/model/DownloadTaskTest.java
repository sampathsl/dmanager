package com.github.sampathsl.dmanager.model;

import com.github.sampathsl.dmanager.util.FileSizeStatus;
import com.github.sampathsl.dmanager.util.FileSpeedStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class DownloadTaskTest {

  private Long id;
  private Long sessionId;
  private Long version;
  private String fileSource;
  private String fileDestination;
  private String protocol;
  private LocalDateTime started;
  private LocalDateTime ended;
  private long fileTotalSize;
  private float failurePercentage;
  private String fileSpeedStatus;
  private String fileSizeStatus;

  @Before
  public void init() {
    id = new Long(0l);
    version = new Long(0l);
    sessionId = new Long(0l);
    fileSource = "";
    fileDestination = "";
    protocol = "http";
    started = LocalDateTime.now();
    ended = LocalDateTime.now();
    fileTotalSize = 0l;
    failurePercentage = 0f;
    fileSpeedStatus = FileSpeedStatus.UNKNOWN.toString();
    fileSizeStatus = FileSizeStatus.UNKNOWN.toString();
  }

  @Test
  public void testGetterOne() {
    DownloadTask downloadTask =
        new DownloadTask(
            version,
            sessionId,
            fileSource,
            fileDestination,
            protocol,
            started,
            ended,
            fileTotalSize,
            failurePercentage,
            fileSpeedStatus,
            fileSizeStatus);
    Assert.assertEquals(downloadTask.getId(), id);
    Assert.assertEquals(downloadTask.getVersion(), version);
    Assert.assertEquals(downloadTask.getSessionId(), sessionId);
    Assert.assertEquals(downloadTask.getFileSource(), fileSource);
    Assert.assertEquals(downloadTask.getFileDestination(), fileDestination);
    Assert.assertEquals(downloadTask.getProtocol(), protocol);
    Assert.assertEquals(downloadTask.getStarted(), started);
    Assert.assertEquals(downloadTask.getEnded(), ended);
    Assert.assertEquals(downloadTask.getFileTotalSize(), fileTotalSize);
    Assert.assertEquals(
        new Float(downloadTask.getFailurePercentage()), new Float(failurePercentage));
    Assert.assertEquals(downloadTask.getFileSpeedStatus(), fileSpeedStatus);
    Assert.assertEquals(downloadTask.getFileSizeStatus(), fileSizeStatus);
  }

  @Test
  public void testGetterTwo() {
    DownloadTask downloadTask =
        new DownloadTask(
            sessionId,
            fileSource,
            fileDestination,
            protocol,
            started,
            ended,
            fileTotalSize,
            failurePercentage,
            fileSpeedStatus,
            fileSizeStatus);
    Assert.assertEquals(downloadTask.getId(), id);
    Assert.assertEquals(downloadTask.getVersion(), version);
    Assert.assertEquals(downloadTask.getSessionId(), sessionId);
    Assert.assertEquals(downloadTask.getFileSource(), fileSource);
    Assert.assertEquals(downloadTask.getFileDestination(), fileDestination);
    Assert.assertEquals(downloadTask.getProtocol(), protocol);
    Assert.assertEquals(downloadTask.getStarted(), started);
    Assert.assertEquals(downloadTask.getEnded(), ended);
    Assert.assertEquals(downloadTask.getFileTotalSize(), fileTotalSize);
    Assert.assertEquals(
        new Float(downloadTask.getFailurePercentage()), new Float(failurePercentage));
    Assert.assertEquals(downloadTask.getFileSpeedStatus(), fileSpeedStatus);
    Assert.assertEquals(downloadTask.getFileSizeStatus(), fileSizeStatus);
  }
}

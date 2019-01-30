package com.github.sampathsl.dmanager.model;

import com.github.sampathsl.dmanager.util.DownloadStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class DownloadTaskLogTest {

  private Long id;
  private Long version;
  private Long taskId;
  private LocalDateTime created;
  private String downloadStatus;
  private float progress;
  private String errorLog;

  @Before
  public void init() {
    id = new Long(0l);
    version = new Long(0l);
    taskId = new Long(0l);
    created = LocalDateTime.now();
    downloadStatus = DownloadStatus.ERROR.getStatus();
    progress = 0f;
    errorLog = "Error";
  }

  @Test
  public void testOne() {
    DownloadTaskLog downloadTaskLog = new DownloadTaskLog();
    Assert.assertEquals(downloadTaskLog.getId(), id);
    Assert.assertEquals(downloadTaskLog.getTaskId(), taskId);
    Assert.assertEquals(downloadTaskLog.getDownloadStatus(), downloadStatus);
    Assert.assertEquals(new Float(downloadTaskLog.getProgress()), new Float(progress));
    Assert.assertEquals(downloadTaskLog.getErrorLog(), errorLog);
  }

  @Test
  public void testTwo() {
    DownloadTaskLog downloadTaskLog =
        new DownloadTaskLog(taskId, created, downloadStatus, progress, errorLog);
    Assert.assertEquals(downloadTaskLog.getId(), id);
    Assert.assertEquals(downloadTaskLog.getTaskId(), taskId);
    Assert.assertEquals(downloadTaskLog.getCreated(), created);
    Assert.assertEquals(downloadTaskLog.getDownloadStatus(), downloadStatus);
    Assert.assertEquals(new Float(downloadTaskLog.getProgress()), new Float(progress));
    Assert.assertEquals(downloadTaskLog.getErrorLog(), errorLog);
  }

  @Test
  public void testThree() {
    DownloadTaskLog downloadTaskLog =
        new DownloadTaskLog(version, taskId, created, downloadStatus, progress, errorLog);
    Assert.assertEquals(downloadTaskLog.getId(), id);
    Assert.assertEquals(downloadTaskLog.getVersion(), version);
    Assert.assertEquals(downloadTaskLog.getTaskId(), taskId);
    Assert.assertEquals(downloadTaskLog.getCreated(), created);
    Assert.assertEquals(downloadTaskLog.getDownloadStatus(), downloadStatus);
    Assert.assertEquals(new Float(downloadTaskLog.getProgress()), new Float(progress));
    Assert.assertEquals(downloadTaskLog.getErrorLog(), errorLog);
  }
}

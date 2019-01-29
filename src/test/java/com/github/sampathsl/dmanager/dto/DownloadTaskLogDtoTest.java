package com.github.sampathsl.dmanager.dto;

import com.github.sampathsl.dmanager.util.DownloadStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class DownloadTaskLogDtoTest {

  private Long version;
  private Long taskId;
  private LocalDateTime created;
  private DownloadStatus downloadStatus;
  private float progress;
  private String errorLog;

  @Before
  public void init() {
    created = LocalDateTime.now();
    taskId = 0L;
    version = 0L;
    downloadStatus = DownloadStatus.DOWNLOADING;
    progress = 0F;
    errorLog = "Sample Error";
  }

  @Test
  public void testGetter() {

    DownloadTaskLogDto downloadTaskLogDto = new DownloadTaskLogDto();
    downloadTaskLogDto.setVersion(version);
    downloadTaskLogDto.setTaskId(taskId);
    downloadTaskLogDto.setCreated(created);
    downloadTaskLogDto.setDownloadStatus(downloadStatus);
    downloadTaskLogDto.setErrorLog(errorLog);
    downloadTaskLogDto.setProgress(progress);

    Assert.assertEquals(downloadTaskLogDto.getVersion(), taskId);
    Assert.assertEquals(downloadTaskLogDto.getTaskId(), version);
    Assert.assertEquals(downloadTaskLogDto.getCreated(), created);
    Assert.assertEquals(downloadTaskLogDto.getDownloadStatus(), downloadStatus);
    Assert.assertEquals(new Float(downloadTaskLogDto.getProgress()), new Float(progress));
    Assert.assertEquals(downloadTaskLogDto.getErrorLog(), errorLog);
  }
}

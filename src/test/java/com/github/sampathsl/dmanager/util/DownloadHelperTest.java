package com.github.sampathsl.dmanager.util;

import com.github.sampathsl.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.model.DownloadTaskLog;
import com.github.sampathsl.dmanager.service.DownloadTaskLogService;
import com.github.sampathsl.dmanager.workers.DownloadManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class DownloadHelperTest {

  private int blockSize, bufferSize = 1;

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

  private Long downloadTaskId;
  private LocalDateTime created;
  private String downloadStatus;
  private float progress;
  private String errorLog;

  private DownloadTask downloadTask;

  private DownloadHelper downloadHelper;

  private DownloadTaskLog downloadTaskLog;

  private DownloadManager downloadManager;

  private DownloadTaskLogService downloadTaskLogService;

  @Before
  public void setUp() {

    id = new Long(1l);
    version = new Long(1l);
    sessionId = new Long(1l);
    fileSource = "http://test.com/text.txt";
    fileDestination = "/home/sampath";
    protocol = "http";
    started = LocalDateTime.now();
    ended = LocalDateTime.now();
    fileTotalSize = 0l;
    failurePercentage = 0f;
    fileSpeedStatus = FileSpeedStatus.UNKNOWN.toString();
    fileSizeStatus = FileSizeStatus.UNKNOWN.toString();

    downloadTaskId = new Long(1l);
    created = LocalDateTime.now();
    downloadStatus = DownloadStatus.DOWNLOADING.name();
    progress = 1f;
    errorLog = "Sample Error";

    downloadTask =
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
    downloadHelper = new DownloadHelper();
    downloadTaskLog =
        new DownloadTaskLog(downloadTaskId, created, downloadStatus, progress, errorLog);
    downloadTaskLogService = Mockito.mock(DownloadTaskLogService.class);
    Mockito.when(downloadTaskLogService.create(downloadTaskLog)).thenReturn(downloadTaskLog);
    downloadHelper.setDownloadTaskLogService(downloadTaskLogService);
    downloadManager = DownloadManager.getInstance();
  }

  @Test
  public void testRunDownloadWorkOne() {
    DownloadHelper downloadHelperMock = Mockito.mock(DownloadHelper.class);
    Mockito.doNothing()
        .when(downloadHelperMock)
        .runDownloadWork(
            ArgumentMatchers.isA(DownloadTask.class),
            ArgumentMatchers.isA(Integer.class),
            ArgumentMatchers.isA(Integer.class));
    downloadHelperMock.runDownloadWork(downloadTask, blockSize, bufferSize);
    Mockito.verify(downloadHelperMock, Mockito.times(1))
        .runDownloadWork(downloadTask, blockSize, bufferSize);
  }

  @Test
  public void testRunDownloadWorkTwo() {
    DownloadHelper downloadHelperMock = Mockito.mock(DownloadHelper.class);
    downloadHelperMock.runDownloadWork(downloadTask, blockSize, bufferSize);
    Mockito.verify(downloadHelperMock, Mockito.times(1))
        .runDownloadWork(
            ArgumentMatchers.eq(downloadTask),
            ArgumentMatchers.eq(blockSize),
            ArgumentMatchers.eq(bufferSize));
  }

  @Test
  public void testRunDownloadWorkThree() {
    DownloadHelper downloadHelperMock = Mockito.mock(DownloadHelper.class);
    Mockito.doCallRealMethod()
        .when(downloadHelperMock)
        .runDownloadWork(
            ArgumentMatchers.any(DownloadTask.class),
            ArgumentMatchers.any(Integer.class),
            ArgumentMatchers.any(Integer.class));
    downloadHelperMock.runDownloadWork(downloadTask, blockSize, bufferSize);
    Mockito.verify(downloadHelperMock, Mockito.times(1))
        .runDownloadWork(downloadTask, blockSize, bufferSize);
  }

  @Test
  public void testUpdate() {
    // TODO
    /*DownloadHelper downloadHelperMock = Mockito.mock(DownloadHelper.class);
    Mockito.doCallRealMethod()
        .when(downloadHelperMock)
        .update(ArgumentMatchers.any(DownloadManager.class), ArgumentMatchers.any(Object.class));
    downloadHelperMock.update(downloadManager, new Object());
    Mockito.verify(downloadHelperMock, Mockito.times(1)).update(downloadManager, new Object());*/
  }
}

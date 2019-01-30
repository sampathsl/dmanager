package com.github.sampathsl.dmanager.service;

import com.github.sampathsl.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.repository.DownloadTaskRepository;
import com.github.sampathsl.dmanager.util.FileSizeStatus;
import com.github.sampathsl.dmanager.util.FileSpeedStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class DownloadTaskServiceImplTest {

  @Mock private DownloadTaskRepository downloadTaskRepository;

  private DownloadTaskService downloadTaskService;

  private DownloadTask downloadTask;

  private Optional<DownloadTask> downloadTaskOptional;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    downloadTask =
        new DownloadTask(
            0l,
            1l,
            "http://test.com/test.txt",
            "/home/sampath/",
            "http",
            LocalDateTime.now(),
            LocalDateTime.now(),
            0l,
            0f,
            FileSpeedStatus.UNKNOWN.toString(),
            FileSizeStatus.UNKNOWN.toString());
    downloadTaskService = new DownloadTaskServiceImpl();
    ((DownloadTaskServiceImpl) downloadTaskService)
        .setDownloadTaskRepository(downloadTaskRepository);
    downloadTaskOptional = Optional.of(downloadTask);
  }

  @Test
  public void testCreate() {
    // TODO
  }

  @Test
  public void testFindById() {
    // TODO
  }

  @Test
  public void testUpdate() {
    // TODO
  }

  @Test
  public void testDelete() {
    // TODO
  }

  @Test
  public void testCreateDownloadTasks() {
    // TODO
  }

  @Test
  public void testFindBySessionId() {
    // TODO
  }

}

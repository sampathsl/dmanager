package com.github.sampathsl.dmanager.service;

import com.github.sampathsl.dmanager.model.DownloadTaskLog;
import com.github.sampathsl.dmanager.repository.DownloadTaskLogRepository;
import com.github.sampathsl.dmanager.util.DownloadStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DownloadTaskLogServiceImplTest {

  @Mock private DownloadTaskLogRepository downloadTaskLogRepository;

  private DownloadTaskLog downloadTaskLog;

  private DownloadTaskLogService downloadTaskLogService;

  private Optional<DownloadTaskLog> optionalDownloadTaskLog;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    downloadTaskLog =
        new DownloadTaskLog(
            0l, 0l, LocalDateTime.now(), DownloadStatus.DOWNLOADING.toString(), 0f, "Error");
    downloadTaskLogService = new DownloadTaskLogServiceImpl();
    ((DownloadTaskLogServiceImpl) downloadTaskLogService)
        .setDownloadTaskRepository(downloadTaskLogRepository);
    optionalDownloadTaskLog = Optional.of(downloadTaskLog);
  }

  @Test
  public void testCreate() {
    when(downloadTaskLogRepository.save(any(DownloadTaskLog.class))).thenReturn(downloadTaskLog);
    Assert.assertThat(downloadTaskLogService.create(downloadTaskLog), is(Matchers.notNullValue()));
  }

  @Test
  public void testFindById() {
    when(downloadTaskLogRepository.findById(1l)).thenReturn(optionalDownloadTaskLog);
    Assert.assertThat(downloadTaskLogService.findById(1l), is(Matchers.notNullValue()));
  }

  @Test
  public void testUpdate() {
    when(downloadTaskLogRepository.save(any(DownloadTaskLog.class))).thenReturn(downloadTaskLog);
    Assert.assertThat(downloadTaskLogService.update(downloadTaskLog), is(Matchers.notNullValue()));
  }

  @Test
  public void testDelete() {
    //TODO
  }

  @Test
  public void testFindAllByTaskId() {
    //TODO
  }

  @Test
  public void testFindLastRecordAllByTaskId() {
    //TODO
  }

}

package com.github.sampathsl.dmanager.service;

import com.github.sampathsl.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.repository.DownloadTaskRepository;
import com.github.sampathsl.dmanager.util.FileSizeStatus;
import com.github.sampathsl.dmanager.util.FileSpeedStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DownloadTaskServiceImplTest {

  @Mock private DownloadTaskRepository downloadTaskRepository;

  private DownloadTaskService downloadTaskService;

  private DownloadTask downloadTask;

  private Optional<DownloadTask> downloadTaskOptional;

  private List<DownloadTask> downloadTasks;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    downloadTask =
        new DownloadTask(
            0l,
            0l,
            "http:/test.com/test.txt",
            "/home/sampath",
            "http",
            LocalDateTime.now(),
            LocalDateTime.now(),
            0l,
            0f,
            FileSpeedStatus.UNKNOWN.toString(),
            FileSizeStatus.UNKNOWN.toString());
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
    downloadTasks = new ArrayList<DownloadTask>();
    downloadTasks.add(downloadTask);
  }

  @Test
  public void testCreate() {
    Mockito.when(downloadTaskRepository.save(any(DownloadTask.class))).thenReturn(downloadTask);
    Assert.assertThat(downloadTaskService.create(downloadTask), is(Matchers.notNullValue()));
  }

  @Test
  public void testFindById() {
    when(downloadTaskRepository.findById(1l)).thenReturn(downloadTaskOptional);
    Assert.assertThat(downloadTaskService.findById(1l), is(Matchers.notNullValue()));
  }

  @Test
  public void testUpdate() {
    when(downloadTaskRepository.save(any(DownloadTask.class))).thenReturn(downloadTask);
    Assert.assertThat(downloadTaskService.update(downloadTask), is(Matchers.notNullValue()));
  }

  @Test
  public void testDelete() {
    downloadTaskService.delete(downloadTask);
    verify(downloadTaskRepository, times(1)).delete(eq(downloadTask));
  }

  @Test
  public void testCreateDownloadTasks() {
    when(downloadTaskRepository.save(any(DownloadTask.class))).thenReturn(downloadTask);
    Assert.assertThat(
        downloadTaskService.createDownloadTasks(downloadTasks), is(Matchers.notNullValue()));
  }

  @Test
  public void testFindBySessionId() {
    when(downloadTaskRepository.findAllDownloadTaskBySessionId(1l)).thenReturn(downloadTasks);
    Assert.assertThat(downloadTaskService.findBySessionId(1l), is(Matchers.notNullValue()));
  }
}

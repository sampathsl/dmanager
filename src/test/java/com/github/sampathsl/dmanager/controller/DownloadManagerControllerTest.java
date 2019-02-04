package com.github.sampathsl.dmanager.controller;

import com.github.sampathsl.dmanager.config.ModelMapperUtil;
import com.github.sampathsl.dmanager.dto.DownloadSessionDto;
import com.github.sampathsl.dmanager.dto.DownloadTaskDto;
import com.github.sampathsl.dmanager.model.DownloadSession;
import com.github.sampathsl.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.model.DownloadTaskLog;
import com.github.sampathsl.dmanager.service.DownloadSessionService;
import com.github.sampathsl.dmanager.service.DownloadTaskLogService;
import com.github.sampathsl.dmanager.service.DownloadTaskService;
import com.github.sampathsl.dmanager.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DownloadManagerControllerTest {

  private Environment environment;
  private DownloadHelper downloadHelperMock;

  private String status;
  private Long sessionId;
  private Long taskIdFound;
  private Long taskIdNotFound;

  private Long taskId;
  private String downloadStatus;
  private float progress;
  private String errorLog;

  private Long version;
  private LocalDateTime created;
  private DownloadTask downloadTask;
  private DownloadTaskDto downloadTaskDto;
  private DownloadTaskLog downloadTaskLog;

  private String urlOne;
  private String urlTwo;
  private String downloadDestination;
  private String minBlockSize;
  private String bufferSize;

  private String fileSource;
  private String fileDestination;
  private String protocol;
  private LocalDateTime started;
  private LocalDateTime ended;
  private long fileTotalSize;
  private float failurePercentage;
  private String fileSpeedStatus;
  private String fileSizeStatus;
  private List<DownloadTask> downloadTasks;
  private List<DownloadTaskDto> downloadTaskDtos;
  private List<DownloadTaskLog> downloadTaskLogs;

  private HelperUtil helperUtilMock;
  private DownloadSession downloadSession;
  private DownloadSessionDto downloadSessionDto;
  private DownloadManagerController downloadManagerController;
  private ModelMapperUtil modelMapperUtil;
  private DownloadSessionService downloadSessionServiceMock;
  private DownloadTaskService downloadTaskServiceMock;
  private DownloadTaskLogService downloadTaskLogServiceMock;

  @Before
  public void init() {

    environment = Mockito.mock(Environment.class);
    Mockito.when(environment.getProperty("min_block_size")).thenReturn("100");
    Mockito.when(environment.getProperty("buffer_size")).thenReturn("100");
    Mockito.when(environment.getProperty("download_destination")).thenReturn("/home/sam/");

    downloadDestination = environment.getProperty("download_destination");
    minBlockSize = environment.getProperty("min_block_size");
    bufferSize = environment.getProperty("buffer_size");
    downloadHelperMock = Mockito.mock(DownloadHelper.class);

    status = "ok";
    sessionId = new Long(1);
    taskIdFound = new Long(1);
    taskIdNotFound = new Long(-1);

    taskId = new Long(1);
    downloadStatus = DownloadStatus.DOWNLOADING.name();
    progress = 1f;
    errorLog = "Sample Error";

    version = new Long(0);
    created = LocalDateTime.now();

    urlOne = "http://test.com/test.txt";
    urlTwo = "https://test.com/todo.txt";

    fileSource = "";
    fileDestination = "";
    protocol = "http";
    started = LocalDateTime.now();
    ended = LocalDateTime.now();
    fileTotalSize = 0l;
    failurePercentage = 0f;
    fileSpeedStatus = FileSpeedStatus.UNKNOWN.toString();
    fileSizeStatus = FileSizeStatus.UNKNOWN.toString();

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
    downloadTasks = Arrays.asList(downloadTask);

    downloadTaskLog =
        new DownloadTaskLog(version, taskId, created, downloadStatus, progress, errorLog);
    downloadTaskLogs = Arrays.asList(downloadTaskLog);

    downloadTaskDto = getDownloadTaskDto(downloadTask);
    downloadTaskDtos = Arrays.asList(downloadTaskDto);

    downloadSession = new DownloadSession(sessionId, version, created, downloadTasks);
    downloadSessionDto = getDownloadSessionDto(downloadSession);

    downloadManagerController = new DownloadManagerController();
    modelMapperUtil = new ModelMapperUtil();
    helperUtilMock = Mockito.mock(HelperUtil.class);
    downloadTaskServiceMock = Mockito.mock(DownloadTaskService.class);
    downloadSessionServiceMock = Mockito.mock(DownloadSessionService.class);
    downloadTaskLogServiceMock = Mockito.mock(DownloadTaskLogService.class);

    downloadManagerController.setHelperUtil(helperUtilMock);
    downloadManagerController.setModelMapper(modelMapperUtil);
    downloadManagerController.setDownloadTaskService(downloadTaskServiceMock);
    downloadManagerController.setDownloadSessionService(downloadSessionServiceMock);
    downloadManagerController.setDownloadTaskLogService(downloadTaskLogServiceMock);
    downloadManagerController.setDownloadHelper(downloadHelperMock);
    downloadManagerController.setEnvironment(environment);

    Mockito.when(downloadSessionServiceMock.findById(sessionId))
        .thenReturn(Optional.of(downloadSession));
    Mockito.when(downloadTaskServiceMock.findBySessionId(sessionId)).thenReturn(downloadTasks);
    Mockito.when(helperUtilMock.convertDownloadSessionToDto(modelMapperUtil, downloadSession))
        .thenReturn(downloadSessionDto);
    Mockito.when(downloadTaskLogServiceMock.findAllByTaskId(taskIdFound))
        .thenReturn(downloadTaskLogs);
    Mockito.when(downloadTaskLogServiceMock.findLastRecordByTaskId(taskIdFound))
        .thenReturn(Optional.of(downloadTaskLog));
    Mockito.when(downloadTaskServiceMock.findById(taskIdFound))
        .thenReturn(Optional.of(downloadTask));
    Mockito.when(downloadTaskServiceMock.create(downloadTask)).thenReturn(downloadTask);
    Mockito.when(downloadTaskServiceMock.createDownloadTasks(downloadTasks))
        .thenReturn(downloadTasks);
    Mockito.when(helperUtilMock.convertDownloadSessionDtoToEntity(downloadSessionDto))
        .thenReturn(downloadSession);
    Mockito.when(
            helperUtilMock.createDownloadTasks(
                downloadSessionDto.getUrls(), downloadSessionDto.getId(), downloadDestination))
        .thenReturn(downloadTasks);
    Mockito.when(downloadSessionServiceMock.create(downloadSession)).thenReturn(downloadSession);
    Mockito.doNothing()
        .when(downloadHelperMock)
        .runDownloadWork(
            ArgumentMatchers.isA(DownloadTask.class),
            ArgumentMatchers.isA(Integer.class),
            ArgumentMatchers.isA(Integer.class));
  }

  private DownloadTaskDto getDownloadTaskDto(DownloadTask downloadTask) {
    DownloadTaskDto downloadTaskDto = new DownloadTaskDto();
    downloadTaskDto.setId(downloadTask.getId());
    downloadTaskDto.setVersion(downloadTask.getVersion());
    downloadTaskDto.setStarted(downloadTask.getStarted());
    downloadTaskDto.setEnded(downloadTask.getEnded());
    downloadTaskDto.setProtocol(downloadTask.getProtocol());
    downloadTaskDto.setFileSource(downloadTask.getFileSource());
    downloadTaskDto.setFileDestination(downloadTask.getFileDestination());
    downloadTaskDto.setFailurePercentage(downloadTask.getFailurePercentage());
    downloadTaskDto.setFileSizeStatus(FileSizeStatus.valueOf(downloadTask.getFileSizeStatus()));
    downloadTaskDto.setFileSpeedStatus(FileSpeedStatus.valueOf(downloadTask.getFileSpeedStatus()));
    downloadTaskDto.setFileTotalSize(downloadTask.getFileTotalSize());
    return downloadTaskDto;
  }

  private DownloadSessionDto getDownloadSessionDto(DownloadSession downloadSession) {
    DownloadSessionDto downloadSessionDto = new DownloadSessionDto();
    downloadSessionDto.setId(downloadSession.getId());
    downloadSessionDto.setVersion(downloadSession.getVersion());
    downloadSessionDto.setUrls(new ArrayList<>(Arrays.asList(urlOne, urlTwo)));
    downloadSessionDto.setDownloadTasks(new ArrayList<>(downloadTaskDtos));
    return downloadSessionDto;
  }

  @Test
  public void testStatus() {
    downloadManagerController = new DownloadManagerController();
    Assert.assertEquals(downloadManagerController.status(), status);
  }

  @Test
  public void testGetDownloadSessionInfoOne() {
    ResponseEntity<?> downloadSessionResponseEntity =
        downloadManagerController.getDownloadSessionInfo(taskIdFound);
    Assert.assertEquals(downloadSessionResponseEntity.getStatusCode().value(), 200);
  }

  @Test
  public void testGetDownloadSessionInfoTwo() {
    ResponseEntity<?> downloadSessionResponseEntity =
        downloadManagerController.getDownloadSessionInfo(taskIdNotFound);
    Assert.assertEquals(downloadSessionResponseEntity.getStatusCode().value(), 404);
  }

  @Test
  public void testGetDownloadTaskInfoOne() {
    ResponseEntity<?> downloadTaskInfo = downloadManagerController.getDownloadTaskInfo(taskIdFound);
    Assert.assertEquals(downloadTaskInfo.getStatusCode().value(), 200);
  }

  @Test
  public void testGetDownloadTaskInfoTwo() {
    ResponseEntity<?> downloadTaskInfoError =
        downloadManagerController.getDownloadTaskInfo(taskIdNotFound);
    Assert.assertEquals(downloadTaskInfoError.getStatusCode().value(), 404);
  }

  @Test
  public void testGetAllDownloadTaskLogInfoOne() {
    ResponseEntity<?> downloadTaskInfos =
        downloadManagerController.getAllDownloadTaskLogInfo(taskIdFound);
    List<DownloadTask> downloadTasks = (List<DownloadTask>) downloadTaskInfos.getBody();
    Assert.assertEquals(downloadTasks.size(), 1);
    Assert.assertEquals(downloadTaskInfos.getStatusCode().value(), 200);
  }

  @Test
  public void testGetAllDownloadTaskLogInfTwo() {
    ResponseEntity<?> downloadTaskInfos =
        downloadManagerController.getAllDownloadTaskLogInfo(taskIdNotFound);
    List<DownloadTask> downloadTasks = (List<DownloadTask>) downloadTaskInfos.getBody();
    Assert.assertNull(downloadTasks);
    Assert.assertEquals(downloadTaskInfos.getStatusCode().value(), 404);
  }

  @Test
  public void testGetLastDownloadTaskLogInfoOne() {
    ResponseEntity<?> downloadTaskInfo =
        downloadManagerController.getLastDownloadTaskLogInfo(taskIdFound);
    Assert.assertEquals(downloadTaskInfo.getStatusCode().value(), 200);
  }

  @Test
  public void testGetLastDownloadTaskLogInfoTwo() {
    ResponseEntity<?> downloadTaskInfoError =
        downloadManagerController.getLastDownloadTaskLogInfo(taskIdNotFound);
    Assert.assertEquals(downloadTaskInfoError.getStatusCode().value(), 404);
  }

  @Test
  public void testCreateDownloadTasksOne() {
    ResponseEntity<?> downloadTaskInfo =
        downloadManagerController.createDownloadTasks(downloadSessionDto);
    Assert.assertEquals(downloadTaskInfo.getStatusCode().value(), 201);
  }

  @Test
  public void testCreateDownloadTasksTwo() {
    ResponseEntity<?> downloadTaskInfo = downloadManagerController.createDownloadTasks(null);
    Assert.assertEquals(downloadTaskInfo.getStatusCode().value(), 500);
  }
}

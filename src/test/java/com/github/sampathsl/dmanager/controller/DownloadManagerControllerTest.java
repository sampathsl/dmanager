package com.github.sampathsl.dmanager.controller;

import com.github.sampathsl.dmanager.config.ModelMapperUtil;
import com.github.sampathsl.dmanager.dto.DownloadSessionDto;
import com.github.sampathsl.dmanager.dto.DownloadTaskDto;
import com.github.sampathsl.dmanager.model.DownloadSession;
import com.github.sampathsl.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.service.DownloadSessionService;
import com.github.sampathsl.dmanager.service.DownloadTaskService;
import com.github.sampathsl.dmanager.util.FileSizeStatus;
import com.github.sampathsl.dmanager.util.FileSpeedStatus;
import com.github.sampathsl.dmanager.util.HelperUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DownloadManagerControllerTest {

  private String status;
  private Long sessionId;
  private Long taskIdFound;
  private Long taskIdNotFound;

  private Long version;
  private LocalDateTime created;
  private DownloadTask downloadTask;
  private DownloadTaskDto downloadTaskDto;

  private String urlOne;
  private String urlTwo;

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

  private HelperUtil helperUtilMock;
  private DownloadSession downloadSession;
  private DownloadSessionDto downloadSessionDto;
  private DownloadManagerController downloadManagerController;
  private ModelMapperUtil modelMapperUtil;
  private DownloadSessionService downloadSessionServiceMock;
  private DownloadTaskService downloadTaskServiceMock;

  @Before
  public void init() {

    status = "ok";
    sessionId = new Long(1);
    taskIdFound = new Long(1);
    taskIdNotFound = new Long(-1);

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

    downloadTaskDto = getDownloadTaskDto(downloadTask);
    downloadTasks = Arrays.asList(downloadTask);
    downloadTaskDtos = Arrays.asList(downloadTaskDto);
    downloadSession = new DownloadSession(sessionId, version, created, downloadTasks);
    downloadSessionDto = getDownloadSessionDto(downloadSession);

    downloadManagerController = new DownloadManagerController();
    modelMapperUtil = new ModelMapperUtil();
    helperUtilMock = Mockito.mock(HelperUtil.class);
    downloadTaskServiceMock = Mockito.mock(DownloadTaskService.class);
    downloadSessionServiceMock = Mockito.mock(DownloadSessionService.class);
    downloadManagerController.setHelperUtil(helperUtilMock);
    downloadManagerController.setModelMapper(modelMapperUtil);
    downloadManagerController.setDownloadTaskService(downloadTaskServiceMock);
    downloadManagerController.setDownloadSessionService(downloadSessionServiceMock);

    Mockito.when(downloadSessionServiceMock.findById(sessionId))
        .thenReturn(Optional.of(downloadSession));
    Mockito.when(downloadTaskServiceMock.findBySessionId(sessionId)).thenReturn(downloadTasks);
    Mockito.when(helperUtilMock.convertDownloadSessionToDto(modelMapperUtil, downloadSession))
        .thenReturn(downloadSessionDto);
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
}

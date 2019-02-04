package com.github.sampathsl.dmanager.controller;

import com.github.sampathsl.dmanager.config.ModelMapperUtil;
import com.github.sampathsl.dmanager.dto.DownloadSessionDto;
import com.github.sampathsl.dmanager.dto.DownloadTaskDto;
import com.github.sampathsl.dmanager.dto.DownloadTaskLogDto;
import com.github.sampathsl.dmanager.model.DownloadSession;
import com.github.sampathsl.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.model.DownloadTaskLog;
import com.github.sampathsl.dmanager.service.DownloadSessionService;
import com.github.sampathsl.dmanager.service.DownloadTaskLogService;
import com.github.sampathsl.dmanager.service.DownloadTaskService;
import com.github.sampathsl.dmanager.util.DownloadHelper;
import com.github.sampathsl.dmanager.util.HelperUtil;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class DownloadManagerController {

  private static final Logger LOGGER = Logger.getLogger(DownloadManagerController.class.getName());

  private static final String INTERNAL_SERVER_ERROR = "Internal Server Error Occurred!";

  @Autowired private Environment environment;

  @Autowired private ModelMapperUtil modelMapper;

  @Autowired private HelperUtil helperUtil;

  @Autowired private DownloadSessionService downloadSessionService;

  @Autowired private DownloadTaskService downloadTaskService;

  @Autowired private DownloadTaskLogService downloadTaskLogService;

  @Autowired private DownloadHelper downloadHelper;

  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public void setModelMapper(ModelMapperUtil modelMapper) {
    this.modelMapper = modelMapper;
  }

  public void setHelperUtil(HelperUtil helperUtil) {
    this.helperUtil = helperUtil;
  }

  public void setDownloadSessionService(DownloadSessionService downloadSessionService) {
    this.downloadSessionService = downloadSessionService;
  }

  public void setDownloadTaskService(DownloadTaskService downloadTaskService) {
    this.downloadTaskService = downloadTaskService;
  }

  public void setDownloadTaskLogService(DownloadTaskLogService downloadTaskLogService) {
    this.downloadTaskLogService = downloadTaskLogService;
  }

  public void setDownloadHelper(DownloadHelper downloadHelper) {
    this.downloadHelper = downloadHelper;
  }

  @GetMapping("/status")
  public String status() {
    return "ok";
  }

  @GetMapping("/download/session/{id}")
  public ResponseEntity<?> getDownloadSessionInfo(@Valid @PathVariable("id") Long id) {

    Optional<DownloadSession> downloadSession = downloadSessionService.findById(id);
    if (downloadSession.isPresent()) {
      List<DownloadTask> downloadTasks =
          downloadTaskService.findBySessionId(downloadSession.get().getId());
      downloadSession.get().setDownloadTasks(downloadTasks);
      DownloadSessionDto downloadSessionDtoSaved =
          helperUtil.convertDownloadSessionToDto(modelMapper, downloadSession.get());
      return new ResponseEntity<>(downloadSessionDtoSaved, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/download/task/{id}")
  public ResponseEntity<?> getDownloadTaskInfo(@Valid @PathVariable("id") Long id) {
    Optional<DownloadTask> downloadTask = downloadTaskService.findById(id);
    return downloadTask
        .map(
            downloadTask1 ->
                new ResponseEntity(
                    modelMapper.map(downloadTask1, DownloadTaskDto.class), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/download/task-logs/task-id/{taskId}")
  public ResponseEntity<?> getAllDownloadTaskLogInfo(@Valid @PathVariable("taskId") Long taskId) {
    java.lang.reflect.Type targetListType = new TypeToken<List<DownloadTaskLogDto>>() {}.getType();
    List<DownloadTaskLog> downloadTaskLogs = downloadTaskLogService.findAllByTaskId(taskId);
    return !downloadTaskLogs.isEmpty()
        ? new ResponseEntity<>(
            (List<DownloadTaskLogDto>) modelMapper.map(downloadTaskLogs, targetListType),
            HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/download/task-logs/task-id/{taskId}/last")
  public ResponseEntity<?> getLastDownloadTaskLogInfo(@Valid @PathVariable("taskId") Long taskId) {
    Optional<DownloadTaskLog> downloadTaskLog =
        downloadTaskLogService.findLastRecordByTaskId(taskId);
    return downloadTaskLog.isPresent()
        ? new ResponseEntity(
            modelMapper.map(downloadTaskLog.get(), DownloadTaskLogDto.class), HttpStatus.OK)
        : new ResponseEntity(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/download")
  public ResponseEntity<?> createDownloadTasks(
      @Valid @RequestBody DownloadSessionDto downloadSessionDto) {

    try {
      if (downloadSessionDto == null) throw new Exception(INTERNAL_SERVER_ERROR);
      DownloadSession downloadSession =
          helperUtil.convertDownloadSessionDtoToEntity(downloadSessionDto);
      DownloadSession downloadSessionSaved = downloadSessionService.create(downloadSession);
      List<DownloadTask> downloadTasks =
          helperUtil.createDownloadTasks(
              downloadSessionDto.getUrls(),
              downloadSessionSaved.getId(),
              getEnvironmentVariable("download_destination"));

      List<DownloadTask> downloadTaskSaved = downloadTaskService.createDownloadTasks(downloadTasks);

      // add to download process
      downloadTaskSaved.stream()
          .forEach(
              dt -> {
                downloadHelper.runDownloadWork(
                    dt,
                    new Integer(getEnvironmentVariable("min_block_size")),
                    new Integer(getEnvironmentVariable("buffer_size")));
              });

      downloadSessionSaved.setDownloadTasks(downloadTaskSaved);
      DownloadSessionDto downloadSessionDtoSaved =
          modelMapper.map(downloadSessionSaved, DownloadSessionDto.class);
      downloadSessionDtoSaved.setUrls(downloadSessionDto.getUrls());
      return new ResponseEntity<>(downloadSessionDtoSaved, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public String getEnvironmentVariable(String key) {
    return environment.getProperty(key);
  }
}

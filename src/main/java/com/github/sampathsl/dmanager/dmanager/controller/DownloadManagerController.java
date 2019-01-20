package com.github.sampathsl.dmanager.dmanager.controller;

import com.github.sampathsl.dmanager.dmanager.dto.DownloadSessionDto;
import com.github.sampathsl.dmanager.dmanager.model.DownloadSession;
import com.github.sampathsl.dmanager.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.dmanager.service.DownloadSessionService;
import com.github.sampathsl.dmanager.dmanager.service.DownloadTaskService;
import com.github.sampathsl.dmanager.dmanager.util.CustomErrorTypeException;
import com.github.sampathsl.dmanager.dmanager.util.HelperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class DownloadManagerController {

  private static final Logger LOGGER = Logger.getLogger(DownloadManagerController.class.getName());

  private static final String INTERNAL_SERVER_ERROR = "Internal Server Error Occurred!";

  @Autowired private Environment environment;

  @Autowired private ModelMapper modelMapper;

  @Autowired private HelperUtil helperUtil;

  @Autowired private DownloadSessionService downloadSessionService;

  @Autowired private DownloadTaskService downloadTaskService;

  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public void setModelMapper(ModelMapper modelMapper) {
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

  @GetMapping("/status")
  public String status() {
    return "success";
  }

  @GetMapping("/info/{property}")
  public String getInfo(@Valid @PathVariable("property") String property) {
    return environment.getProperty(property);
  }

  @GetMapping("/download/session/{id}")
  public ResponseEntity<?> getInfo(@Valid @PathVariable("id") Long id) {

    Optional<DownloadSession> downloadSession = downloadSessionService.findById(id);
    if (downloadSession.isPresent()) {
      List<DownloadTask> downloadTasks =
          downloadTaskService.findBySessionId(downloadSession.get().getId());
      downloadSession.get().setDownloadTasks(downloadTasks);
      DownloadSessionDto downloadSessionDtoSaved =
          helperUtil.convertDownloadSessionToDto(modelMapper, downloadSession.get());
      return new ResponseEntity<DownloadSessionDto>(downloadSessionDtoSaved, HttpStatus.OK);
    }
    return new ResponseEntity<DownloadSession>(
        new DownloadSession(LocalDateTime.now()), HttpStatus.NO_CONTENT);
  }

  @PostMapping("/download")
  public ResponseEntity<?> createDownloadTasks(
      @Valid @RequestBody DownloadSessionDto downloadSessionDto, Errors errors) {

    if (errors.hasErrors()) {
      return getErrors(errors);
    }

    DownloadSession downloadSession = modelMapper.map(downloadSessionDto, DownloadSession.class);
    DownloadSession downloadSessionSaved = downloadSessionService.create(downloadSession);
    List<DownloadTask> downloadTasks =
        helperUtil.createDownloadTasks(
            downloadSessionDto.getUrls(),
            downloadSessionSaved.getId(),
            environment.getProperty("downloadDestination"));
    downloadSessionSaved.setDownloadTasks(downloadTasks);
    DownloadSessionDto downloadSessionDtoSaved =
        modelMapper.map(downloadSessionSaved, DownloadSessionDto.class);
    return new ResponseEntity<DownloadSessionDto>(downloadSessionDtoSaved, HttpStatus.CREATED);
  }

  /**
   * Get all the errors
   *
   * @param errors
   * @return ResponseEntity
   */
  private ResponseEntity<List<CustomErrorTypeException>> getErrors(Errors errors) {

    return ResponseEntity.badRequest()
        .body(
            errors.getAllErrors().stream()
                .map(msg -> new CustomErrorTypeException(msg.getDefaultMessage()))
                .collect(Collectors.toList()));
  }
}

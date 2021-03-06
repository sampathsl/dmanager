package com.github.sampathsl.dmanager.config;

import com.github.sampathsl.dmanager.dto.DownloadSessionDto;
import com.github.sampathsl.dmanager.model.DownloadSession;
import com.github.sampathsl.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.model.DownloadTaskLog;
import com.github.sampathsl.dmanager.service.DownloadSessionService;
import com.github.sampathsl.dmanager.service.DownloadTaskLogService;
import com.github.sampathsl.dmanager.service.DownloadTaskService;
import com.github.sampathsl.dmanager.util.DownloadStatus;
import com.github.sampathsl.dmanager.util.FileSizeStatus;
import com.github.sampathsl.dmanager.util.FileSpeedStatus;
import com.github.sampathsl.dmanager.util.HelperUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

  private static final Logger LOGGER = Logger.getLogger(DataLoader.class.getName());

  @Autowired private HelperUtil helperUtil;

  @Autowired private DownloadSessionService downloadSessionService;

  @Autowired private DownloadTaskService downloadTaskService;

  @Autowired private DownloadTaskLogService downloadTaskLogService;

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

  /**
   * Load initial sample data sets
   * @param args Argument to parse to the method
   */
  @Override
  public void run(ApplicationArguments args) {
    LOGGER.info("RUNNING ................... ");
    try {

      DownloadSessionDto downloadSessionDto = new DownloadSessionDto();
      downloadSessionDto.setUrls(
          new ArrayList<>(Arrays.asList("http://my.file.com/file", "https://my.file.com/file")));
      DownloadSession downloadSession =
          helperUtil.convertDownloadSessionDtoToEntity(downloadSessionDto);
      DownloadSession downloadSessionSaved = downloadSessionService.create(downloadSession);

      String fileSource = "http://my.file.com/file";
      String fileDestination = "/home/sam/";
      LocalDateTime started = LocalDateTime.now();
      LocalDateTime ended = LocalDateTime.now();
      String protocol = "http";
      long fileTotalSize = 1000000;
      float failurePercentage = 51.20f;
      FileSpeedStatus fileSpeedStatus = FileSpeedStatus.SLOW;
      FileSizeStatus fileSizeStatus = FileSizeStatus.SMALL;

      String fileSourceTwo = "https://my.file.com/file";
      String fileDestinationTwo = "/home/sam/";
      LocalDateTime startedTwo = LocalDateTime.now();
      LocalDateTime endedTwo = LocalDateTime.now();
      String protocolTwo = "http";
      long fileTotalSizeTwo = 1000000;
      float failurePercentageTwo = 51.20f;
      FileSpeedStatus fileSpeedStatusTwo = FileSpeedStatus.SLOW;
      FileSizeStatus fileSizeStatusTwo = FileSizeStatus.SMALL;

      DownloadTask downloadTaskOne =
          new DownloadTask(
              downloadSessionSaved.getId(),
              fileSource,
              fileDestination,
              protocol,
              started,
              ended,
              fileTotalSize,
              failurePercentage,
              fileSpeedStatus.name(),
              fileSizeStatus.name());

      DownloadTask downloadTaskTwo =
          new DownloadTask(
              downloadSessionSaved.getId(),
              fileSourceTwo,
              fileDestinationTwo,
              protocolTwo,
              startedTwo,
              endedTwo,
              fileTotalSizeTwo,
              failurePercentageTwo,
              fileSpeedStatusTwo.name(),
              fileSizeStatusTwo.name());

      DownloadTask downloadTaskOneSaved = downloadTaskService.create(downloadTaskOne);
      DownloadTask downloadTaskTwoSaved = downloadTaskService.create(downloadTaskTwo);

      DownloadTaskLog downloadTaskLogOne =
          new DownloadTaskLog(
              downloadTaskOneSaved.getId(),
              LocalDateTime.now(),
              DownloadStatus.DOWNLOADING.name(),
              10.0f,
              "");

      DownloadTaskLog downloadTaskLogTwo =
          new DownloadTaskLog(
              downloadTaskOneSaved.getId(),
              LocalDateTime.now(),
              DownloadStatus.DOWNLOADING.name(),
              20.0f,
              "");

      DownloadTaskLog downloadTaskLogThree =
          new DownloadTaskLog(
              downloadTaskTwoSaved.getId(),
              LocalDateTime.now(),
              DownloadStatus.DOWNLOADING.name(),
              10.0f,
              "");

      DownloadTaskLog downloadTaskLogFour =
          new DownloadTaskLog(
              downloadTaskTwoSaved.getId(),
              LocalDateTime.now(),
              DownloadStatus.DOWNLOADING.name(),
              30.0f,
              "");

      downloadTaskLogService.create(downloadTaskLogOne);
      downloadTaskLogService.create(downloadTaskLogTwo);
      downloadTaskLogService.create(downloadTaskLogThree);
      downloadTaskLogService.create(downloadTaskLogFour);

    } catch (Exception e) {
      LOGGER.info("Error occurred " + e.getMessage());
    }
    LOGGER.info("ENDING ................... ");
  }
}

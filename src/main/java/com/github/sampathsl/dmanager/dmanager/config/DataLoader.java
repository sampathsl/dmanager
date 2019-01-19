package com.github.sampathsl.dmanager.dmanager.config;

import com.github.sampathsl.dmanager.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.dmanager.model.DownloadTaskLog;
import com.github.sampathsl.dmanager.dmanager.service.DownloadTaskLogService;
import com.github.sampathsl.dmanager.dmanager.service.DownloadTaskService;
import com.github.sampathsl.dmanager.dmanager.util.DownloadStatus;
import com.github.sampathsl.dmanager.dmanager.util.FileSizeStatus;
import com.github.sampathsl.dmanager.dmanager.util.FileSpeedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class DataLoader implements ApplicationRunner {

  private static final Logger LOGGER = Logger.getLogger( DataLoader.class.getName() );

  @Autowired private DownloadTaskService downloadTaskService;

  @Autowired private DownloadTaskLogService downloadTaskLogService;

  public void setDownloadTaskService(DownloadTaskService downloadTaskService) {
    this.downloadTaskService = downloadTaskService;
  }

  public void setDownloadTaskLogService(DownloadTaskLogService downloadTaskLogService) {
    this.downloadTaskLogService = downloadTaskLogService;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    LOGGER.info("RUNNING ................... ");
    try {
      String fileSource = "http://my.file.com/file";
      String fileDestination = "/home/sampath/";
      LocalDateTime started = LocalDateTime.now();
      LocalDateTime ended = LocalDateTime.now();
      String protocol = "http";
      long fileTotalSize = 1000000;
      float failurePercentage = 51.2f;
      FileSpeedStatus fileSpeedStatus = FileSpeedStatus.SLOW;
      FileSizeStatus fileSizeStatus = FileSizeStatus.BIG;
      DownloadTask downloadTask =
          new DownloadTask(
              fileSource,
              fileDestination,
              started,
              ended,
              protocol,
              fileTotalSize,
              failurePercentage,
              fileSpeedStatus,
              fileSizeStatus);
      DownloadTask downloadTask1 = downloadTaskService.create(downloadTask);
      LOGGER.info(downloadTask1.getId().toString());
      DownloadTaskLog downloadTaskLog =
          new DownloadTaskLog(
              downloadTask.getId(), LocalDateTime.now(), DownloadStatus.DOWNLOADING, 10.0f);
      downloadTaskLogService.create(downloadTaskLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    LOGGER.info("ENDING ................... ");
  }
}

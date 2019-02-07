package com.github.sampathsl.dmanager.util;

import com.github.sampathsl.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.model.DownloadTaskLog;
import com.github.sampathsl.dmanager.service.DownloadTaskLogService;
import com.github.sampathsl.dmanager.workers.DownloadManager;
import com.github.sampathsl.dmanager.workers.DownloadTaskWorker;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DownloadHelper implements Observer {

  @Autowired private DownloadTaskLogService downloadTaskLogService;

  public void setDownloadTaskLogService(DownloadTaskLogService downloadTaskLogService) {
    this.downloadTaskLogService = downloadTaskLogService;
  }

  public void runDownloadWork(DownloadTask downloadTask, int blockSize, int bufferSize) {
    URL verifiedUrl = DownloadManager.verifyURL(downloadTask.getFileSource());
    if (verifiedUrl != null) {
      DownloadTaskWorker download =
          DownloadManager.getInstance()
              .createDownload(
                  verifiedUrl,
                  downloadTask.getFileDestination(),
                  blockSize,
                  bufferSize,
                  downloadTask.getId());
      download.addObserver(this);
    }
  }

  public void update(Observable o, Object arg) {
    int index = DownloadManager.getInstance().getDownloadList().indexOf(o);
    DownloadTaskWorker downloadTaskWorker =
        DownloadManager.getInstance().getDownloadList().get(index);

    long downloadTaskId = downloadTaskWorker.getDownloadTaskId();
    DownloadStatus downloadStatus = downloadTaskWorker.getState();
    float progress = downloadTaskWorker.getProgress();

    DownloadTaskLog downloadTaskLog =
        new DownloadTaskLog(
            downloadTaskId, LocalDateTime.now(), downloadStatus.name(), progress, "No Error");
    downloadTaskLogService.create(downloadTaskLog);
  }
}

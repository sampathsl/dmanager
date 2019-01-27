package com.github.sampathsl.dmanager.service;

import com.github.sampathsl.dmanager.model.DownloadTask;

import java.util.List;
import java.util.Optional;

public interface DownloadTaskService {

  DownloadTask create(DownloadTask downloadTask);

  List<DownloadTask> createDownloadTasks(List<DownloadTask> downloadTasks);

  void delete(DownloadTask downloadTask);

  Optional<DownloadTask> findById(Long id);

  List<DownloadTask> findBySessionId(Long sessionId);

  DownloadTask update(DownloadTask downloadTask);
}

package com.github.sampathsl.dmanager.dmanager.service;

import com.github.sampathsl.dmanager.dmanager.model.DownloadTaskLog;

import java.util.List;
import java.util.Optional;

public interface DownloadTaskLogService {

  DownloadTaskLog create(DownloadTaskLog downloadTaskLog);

  void delete(DownloadTaskLog downloadTask);

  List<DownloadTaskLog> findAllByTaskId(Long taskId);

  Optional<DownloadTaskLog> findById(Long id);

  DownloadTaskLog update(DownloadTaskLog downloadTaskLog);
}

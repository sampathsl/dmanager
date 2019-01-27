package com.github.sampathsl.dmanager.service;

import com.github.sampathsl.dmanager.model.DownloadTaskLog;
import com.github.sampathsl.dmanager.repository.DownloadTaskLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DownloadTaskLogServiceImpl implements DownloadTaskLogService {

  @Autowired private DownloadTaskLogRepository downloadTaskRepository;

  public void setDownloadTaskRepository(DownloadTaskLogRepository downloadTaskRepository) {
    this.downloadTaskRepository = downloadTaskRepository;
  }

  @Override
  public DownloadTaskLog create(DownloadTaskLog downloadTaskLog) {
    return downloadTaskRepository.save(downloadTaskLog);
  }

  @Override
  public void delete(DownloadTaskLog downloadTask) {
    downloadTaskRepository.delete(downloadTask);
  }

  @Override
  public List<DownloadTaskLog> findAllByTaskId(Long taskId) {
    return downloadTaskRepository.findAllByTaskId(taskId);
  }

  @Override
  public Optional<DownloadTaskLog> findLastRecordAllByTaskId(Long taskId) {
    return downloadTaskRepository.findLastRecordAllByTaskId(taskId);
  }

  @Override
  public Optional<DownloadTaskLog> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public DownloadTaskLog update(DownloadTaskLog downloadTaskLog) {
    return null;
  }
}

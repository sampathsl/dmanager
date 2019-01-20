package com.github.sampathsl.dmanager.dmanager.service;

import com.github.sampathsl.dmanager.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.dmanager.repository.DownloadTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DownloadTaskServiceImpl implements DownloadTaskService {

  @Autowired private DownloadTaskRepository downloadTaskRepository;

  public void setDownloadTaskRepository(DownloadTaskRepository downloadTaskRepository) {
    this.downloadTaskRepository = downloadTaskRepository;
  }

  @Override
  public DownloadTask create(DownloadTask downloadTask) {
    return downloadTaskRepository.save(downloadTask);
  }

  @Override
  public List<DownloadTask> createDownloadTasks(List<DownloadTask> downloadTasks) {
    return downloadTasks
        .parallelStream()
        .map(selectedDownloadTask -> downloadTaskRepository.save(selectedDownloadTask))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public void delete(DownloadTask downloadTask) {
    downloadTaskRepository.delete(downloadTask);
  }

  @Override
  public Optional<DownloadTask> findById(Long id) {
    return downloadTaskRepository.findById(id);
  }

  @Override
  public List<DownloadTask> findBySessionId(Long sessionId) {
    return downloadTaskRepository.findAllDownloadTaskBySessionId(sessionId);
  }

  @Override
  public DownloadTask update(DownloadTask downloadTask) {
    return downloadTaskRepository.save(downloadTask);
  }
}

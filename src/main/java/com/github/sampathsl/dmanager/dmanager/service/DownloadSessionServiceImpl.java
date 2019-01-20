package com.github.sampathsl.dmanager.dmanager.service;

import com.github.sampathsl.dmanager.dmanager.model.DownloadSession;
import com.github.sampathsl.dmanager.dmanager.repository.DownloadSessionRepository;
import com.github.sampathsl.dmanager.dmanager.repository.DownloadTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DownloadSessionServiceImpl implements DownloadSessionService {

  @Autowired private DownloadSessionRepository downloadSessionRepository;
  @Autowired private DownloadTaskRepository downloadTaskRepository;

  public void setDownloadSessionRepository(DownloadSessionRepository downloadSessionRepository) {
    this.downloadSessionRepository = downloadSessionRepository;
  }

  public void setDownloadTaskRepository(DownloadTaskRepository downloadTaskRepository) {
    this.downloadTaskRepository = downloadTaskRepository;
  }

  @Override
  public DownloadSession create(DownloadSession downloadSession) {
    DownloadSession downloadSessionSaved = downloadSessionRepository.save(downloadSession);
    return downloadSessionSaved;
  }

  @Override
  public Optional<DownloadSession> findById(Long id) {
    return downloadSessionRepository.findById(id);
  }

  @Override
  public DownloadSession update(DownloadSession downloadSession) {
    return downloadSessionRepository.save(downloadSession);
  }
}

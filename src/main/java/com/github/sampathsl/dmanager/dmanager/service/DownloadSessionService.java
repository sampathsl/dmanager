package com.github.sampathsl.dmanager.dmanager.service;

import com.github.sampathsl.dmanager.dmanager.model.DownloadSession;

import java.util.Optional;

public interface DownloadSessionService {

  DownloadSession create(DownloadSession downloadSession);

  Optional<DownloadSession> findById(Long id);

  DownloadSession update(DownloadSession downloadSession);
}

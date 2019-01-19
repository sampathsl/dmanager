package com.github.sampathsl.dmanager.dmanager.service;

import com.github.sampathsl.dmanager.dmanager.model.DownloadSession;
import com.github.sampathsl.dmanager.dmanager.repository.DownloadSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DownloadSessionServiceImpl implements DownloadSessionService {

    @Autowired
    private DownloadSessionRepository downloadSessionRepository;

    public void setDownloadSessionRepository(DownloadSessionRepository downloadSessionRepository) {
        this.downloadSessionRepository = downloadSessionRepository;
    }

    @Override
    public DownloadSession create(DownloadSession downloadSession) {
        return downloadSessionRepository.save(downloadSession);
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

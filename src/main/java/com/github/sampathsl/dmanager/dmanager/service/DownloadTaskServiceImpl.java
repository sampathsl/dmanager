package com.github.sampathsl.dmanager.dmanager.service;

import com.github.sampathsl.dmanager.dmanager.model.DownloadTask;
import com.github.sampathsl.dmanager.dmanager.repository.DownloadTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DownloadTaskServiceImpl implements DownloadTaskService {

    @Autowired
    private DownloadTaskRepository downloadTaskRepository;

    public void setDownloadTaskRepository(DownloadTaskRepository downloadTaskRepository) {
        this.downloadTaskRepository = downloadTaskRepository;
    }

    @Override
    public DownloadTask create(DownloadTask downloadTask) {
        return downloadTaskRepository.save(downloadTask);
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
    public DownloadTask update(DownloadTask downloadTask) {
        return downloadTaskRepository.save(downloadTask);
    }
}

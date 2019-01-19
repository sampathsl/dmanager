package com.github.sampathsl.dmanager.dmanager.service;

import com.github.sampathsl.dmanager.dmanager.model.DownloadTask;

import java.util.Optional;

public interface DownloadTaskService {

    DownloadTask create(DownloadTask downloadTask);

    void delete(DownloadTask downloadTask);

    Optional<DownloadTask> findById(Long id);

    DownloadTask update(DownloadTask downloadTask);

}

package com.github.sampathsl.dmanager.dmanager.repository;

import com.github.sampathsl.dmanager.dmanager.model.DownloadTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadTaskRepository extends CrudRepository<DownloadTask, Long> {
}

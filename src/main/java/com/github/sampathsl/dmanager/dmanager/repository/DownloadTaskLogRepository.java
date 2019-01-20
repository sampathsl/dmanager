package com.github.sampathsl.dmanager.dmanager.repository;

import com.github.sampathsl.dmanager.dmanager.model.DownloadTaskLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadTaskLogRepository extends CrudRepository<DownloadTaskLog, Long> {

  @Query(value = "SELECT * FROM Download_Task_Log u WHERE u.taskId = ?1", nativeQuery = true)
  List<DownloadTaskLog> findAllByTaskId(Long taskId);
}

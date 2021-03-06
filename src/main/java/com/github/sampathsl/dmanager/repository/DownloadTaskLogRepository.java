package com.github.sampathsl.dmanager.repository;

import com.github.sampathsl.dmanager.model.DownloadTaskLog;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadTaskLogRepository extends CrudRepository<DownloadTaskLog, Long> {

  @Query(value = "SELECT * FROM Download_Task_Log u WHERE u.task_id = ?1", nativeQuery = true)
  List<DownloadTaskLog> findAllByTaskId(Long taskId);

  @Query(
      value =
          "SELECT * FROM Download_Task_Log u WHERE u.task_id = ?1 order by created desc LIMIT 1",
      nativeQuery = true)
  Optional<DownloadTaskLog> findLastRecordByTaskId(Long taskId);
}

package com.github.sampathsl.dmanager.repository;

import com.github.sampathsl.dmanager.model.DownloadTask;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadTaskRepository extends CrudRepository<DownloadTask, Long> {

  @Query(value = "SELECT * FROM Download_Task u WHERE u.session_Id = ?1", nativeQuery = true)
  List<DownloadTask> findAllDownloadTaskBySessionId(Long sessionId);
}

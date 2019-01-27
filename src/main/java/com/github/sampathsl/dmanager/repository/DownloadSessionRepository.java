package com.github.sampathsl.dmanager.repository;

import com.github.sampathsl.dmanager.model.DownloadSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadSessionRepository extends CrudRepository<DownloadSession, Long> {}

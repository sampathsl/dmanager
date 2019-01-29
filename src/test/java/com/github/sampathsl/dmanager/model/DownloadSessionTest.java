package com.github.sampathsl.dmanager.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DownloadSessionTest {

  private Long id;

  private Long version;

  private LocalDateTime created;

  private List<DownloadTask> downloadTasks;

  @Before
  public void init() {
    id = new Long(0l);
    version = new Long(0l);
    created = LocalDateTime.now();
    downloadTasks = new ArrayList<>();
  }

  @Test
  public void testGetter() {
    DownloadSession downloadSession = new DownloadSession();
    downloadSession.setVersion(id);
    downloadSession.setCreated(created);
    downloadSession.setDownloadTasks(downloadTasks);
    Assert.assertEquals(downloadSession.getId(), id);
    Assert.assertEquals(downloadSession.getVersion(), version);
    Assert.assertEquals(downloadSession.getCreated(), created);
    Assert.assertEquals(downloadSession.getDownloadTasks(), downloadTasks);
  }
}

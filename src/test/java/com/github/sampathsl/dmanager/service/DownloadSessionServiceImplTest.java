package com.github.sampathsl.dmanager.service;

import com.github.sampathsl.dmanager.model.DownloadSession;
import com.github.sampathsl.dmanager.repository.DownloadSessionRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DownloadSessionServiceImplTest {

  @Mock private DownloadSessionRepository downloadSessionRepository;

  private DownloadSession downloadSession;

  private DownloadSessionService downloadSessionService;

  private Optional<DownloadSession> optionalDownloadSession;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    downloadSession = new DownloadSession(0l);
    downloadSessionService = new DownloadSessionServiceImpl();
    ((DownloadSessionServiceImpl) downloadSessionService)
        .setDownloadSessionRepository(downloadSessionRepository);
    optionalDownloadSession = Optional.of(downloadSession);
  }

  @Test
  public void testCreate() {
    when(downloadSessionRepository.save(any(DownloadSession.class))).thenReturn(downloadSession);
    Assert.assertThat(downloadSessionService.create(downloadSession), is(Matchers.notNullValue()));
  }

  @Test
  public void testFindById() {
    when(downloadSessionRepository.findById(1l)).thenReturn(optionalDownloadSession);
    Assert.assertThat(downloadSessionService.findById(1l), is(Matchers.notNullValue()));
  }

  @Test
  public void testUpdate() {
    when(downloadSessionRepository.save(any(DownloadSession.class))).thenReturn(downloadSession);
    Assert.assertThat(downloadSessionService.update(downloadSession), is(Matchers.notNullValue()));
  }
}

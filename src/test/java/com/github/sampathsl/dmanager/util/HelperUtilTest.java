package com.github.sampathsl.dmanager.util;

import com.github.sampathsl.dmanager.dto.DownloadSessionDto;
import com.github.sampathsl.dmanager.model.DownloadSession;
import com.github.sampathsl.dmanager.model.DownloadTask;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class HelperUtilTest {

  List<String> urlStrings;
  private String sampleGoodSourceUrl;
  private String sampleBadSourceUrl;
  private String sampleProtocol;
  private String error;
  private HelperUtil helperUtil;
  private ModelMapper modelMapper;
  private DownloadSession downloadSession;
  private DownloadTask downloadTask;
  private Long version;
  private Long sessionId;
  private String fileSource;
  private String fileDestination;
  private String protocol;
  private LocalDateTime started;
  private LocalDateTime ended;
  private long fileTotalSize;
  private float failurePercentage;
  private String fileSpeedStatus;
  private String fileSizeStatus;
  private DownloadSessionDto downloadSessionDto;

  @Before
  public void setUp() {
    sampleGoodSourceUrl = "http://test.com/test.txt";
    sampleBadSourceUrl = "test.com/test.txt";
    sampleProtocol = "http";
    error = "Invalid URL";
    helperUtil = new HelperUtil();
    modelMapper = new ModelMapper();

    sessionId = new Long(0l);
    version = new Long(0l);
    fileSource = "http://test.com/test.txt";
    fileDestination = "/home/sam/";
    protocol = "http";
    started = LocalDateTime.now();
    ended = LocalDateTime.now();
    fileTotalSize = 1l;
    failurePercentage = 1f;
    fileSpeedStatus = FileSpeedStatus.UNKNOWN.name();
    fileSizeStatus = FileSizeStatus.UNKNOWN.name();

    downloadTask =
        new DownloadTask(
            version,
            sessionId,
            fileSource,
            fileDestination,
            protocol,
            started,
            ended,
            fileTotalSize,
            failurePercentage,
            fileSpeedStatus,
            fileSizeStatus);

    downloadSession =
        new DownloadSession(
            new Long(0l), new Long(0l), LocalDateTime.now(), Arrays.asList(downloadTask));

    downloadSessionDto = new DownloadSessionDto();
    urlStrings = Arrays.asList(fileSource);
  }

  @Test
  public void testGetProtocolOne() {
    Assert.assertEquals(helperUtil.getProtocol(sampleGoodSourceUrl), sampleProtocol);
  }

  @Test
  public void testGetProtocolTwo() {
    Assert.assertEquals(helperUtil.getProtocol(sampleBadSourceUrl), error);
  }

  @Test
  public void testConvertDownloadSessionToDto() {
    DownloadSessionDto downloadSessionDto =
        helperUtil.convertDownloadSessionToDto(modelMapper, downloadSession);
    Assert.assertEquals(downloadSessionDto.getId(), new Long(0));
    Assert.assertEquals(downloadSessionDto.getVersion(), version);
    Assert.assertEquals(downloadSessionDto.getUrls().get(0), fileSource);
    Assert.assertEquals(downloadSessionDto.getDownloadTasks().get(0).getId(), downloadTask.getId());
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getVersion(), downloadTask.getVersion());
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getFileSource(), downloadTask.getFileSource());
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getFileDestination(),
        downloadTask.getFileDestination());
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getStarted(), downloadTask.getStarted());
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getEnded(), downloadTask.getEnded());
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getProtocol(), downloadTask.getProtocol());
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getFileSpeedStatus().name(),
        downloadTask.getFileSizeStatus());
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getFileSizeStatus().name(),
        downloadTask.getFileSizeStatus());
  }

  @Test
  public void testConvertDownloadSessionDtoToEntity() {
    DownloadSession downloadSession =
        helperUtil.convertDownloadSessionDtoToEntity(downloadSessionDto);
    Assert.assertEquals(
        helperUtil.convertDownloadSessionDtoToEntity(downloadSessionDto), downloadSession);
  }

  @Test
  public void testCreateDownloadTasks() {
    List<DownloadTask> downloadTasks =
        helperUtil.createDownloadTasks(
            Arrays.asList(sampleGoodSourceUrl), sessionId, fileDestination);
    Assert.assertEquals(downloadTasks.size(), 1);
  }
}

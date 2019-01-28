package com.github.sampathsl.dmanager.dto;

import com.github.sampathsl.dmanager.util.FileSizeStatus;
import com.github.sampathsl.dmanager.util.FileSpeedStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DownloadSessionDtoTest {

  private LocalDateTime start;
  private LocalDateTime end;
  private Long id;
  private Long version;
  private String url1;
  private String url2;
  private Float failurePercentage;
  private String fileDestination;
  private FileSizeStatus fileSizeStatus;
  private FileSpeedStatus fileSpeedStatus;
  private String fileSource;
  private Long totalFileSize;
  private String protocol;
  private ArrayList<String> urls;
  private ArrayList<DownloadTaskDto> downloadTaskDtos;

  @Before
  public void init() {
    start = LocalDateTime.now();
    end = LocalDateTime.now();
    id = 0l;
    version = 0l;
    url1 = "http://test.com";
    url2 = "https://test.com";
    failurePercentage = 50F;
    fileDestination = "/home/test";
    fileSizeStatus = FileSizeStatus.UNKNOWN;
    fileSpeedStatus = FileSpeedStatus.UNKNOWN;
    fileSource = "http://test.com/test.txt";
    totalFileSize = 0l;
    protocol = "http";
    urls = new ArrayList<>(Arrays.asList(url1, url2));
    DownloadTaskDto downloadTaskDto = new DownloadTaskDto();
    downloadTaskDto.setId(id);
    downloadTaskDto.setVersion(version);
    downloadTaskDto.setStarted(start);
    downloadTaskDto.setEnded(end);
    downloadTaskDto.setFailurePercentage(failurePercentage);
    downloadTaskDto.setFileDestination(fileDestination);
    downloadTaskDto.setFileSizeStatus(fileSizeStatus);
    downloadTaskDto.setFileSource(fileSource);
    downloadTaskDto.setFileSpeedStatus(fileSpeedStatus);
    downloadTaskDto.setFileTotalSize(totalFileSize);
    downloadTaskDto.setProtocol(protocol);
    downloadTaskDtos = new ArrayList<DownloadTaskDto>(Arrays.asList(downloadTaskDto));
  }

  @Test
  public void testGetter() {

    DownloadSessionDto downloadSessionDto = new DownloadSessionDto();
    downloadSessionDto.setId(id);
    downloadSessionDto.setVersion(version);
    downloadSessionDto.setUrls(urls);
    downloadSessionDto.setDownloadTasks(downloadTaskDtos);

    Assert.assertEquals(downloadSessionDto.getId(), id);
    Assert.assertEquals(downloadSessionDto.getVersion(), version);
    Assert.assertEquals(downloadSessionDto.getUrls(), urls);
    Assert.assertEquals(downloadSessionDto.getDownloadTasks().get(0).getId(), id);
    Assert.assertEquals(downloadSessionDto.getDownloadTasks().get(0).getVersion(), version);
    Assert.assertEquals(downloadSessionDto.getDownloadTasks().get(0).getStarted(), start);
    Assert.assertEquals(downloadSessionDto.getDownloadTasks().get(0).getEnded(), end);
    Assert.assertEquals(
        new Float(downloadSessionDto.getDownloadTasks().get(0).getFailurePercentage()),
        failurePercentage);
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getFileDestination(), fileDestination);
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getFileSizeStatus(), fileSizeStatus);
    Assert.assertEquals(
        downloadSessionDto.getDownloadTasks().get(0).getFileSpeedStatus(), fileSpeedStatus);
    Assert.assertEquals(downloadSessionDto.getDownloadTasks().get(0).getProtocol(), protocol);
    Assert.assertEquals(downloadSessionDto.getDownloadTasks().get(0).getFileSource(), fileSource);
    Assert.assertEquals(
        new Long(downloadSessionDto.getDownloadTasks().get(0).getFileTotalSize()), totalFileSize);
  }
}

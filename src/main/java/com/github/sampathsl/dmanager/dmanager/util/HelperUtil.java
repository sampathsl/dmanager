package com.github.sampathsl.dmanager.dmanager.util;

import com.github.sampathsl.dmanager.dmanager.dto.DownloadSessionDto;
import com.github.sampathsl.dmanager.dmanager.dto.DownloadTaskDto;
import com.github.sampathsl.dmanager.dmanager.model.DownloadSession;
import com.github.sampathsl.dmanager.dmanager.model.DownloadTask;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class HelperUtil {

  private static final Logger LOGGER = Logger.getLogger(HelperUtil.class.getName());

  /**
   *
   * @param url
   * @return
   */
  public String getProtocol(String url) {
    StringBuffer protocol = new StringBuffer();
    try {
      protocol.append(new URL(url).getProtocol());
    } catch (MalformedURLException e) {
      protocol.append("Invalid URL");
      LOGGER.log(Level.SEVERE, "Exception occur", e);
    }
    return protocol.toString();
  }

  public DownloadSessionDto convertDownloadSessionToDto(
      ModelMapper modelMapper, DownloadSession downloadSession) {
    DownloadSessionDto downloadSessionDto = new DownloadSessionDto();
    downloadSessionDto.setId(downloadSession.getId());
    downloadSessionDto.setVersion(downloadSession.getVersion());
    downloadSessionDto.setCreated(downloadSession.getCreated());
    ArrayList<String> urls =
        downloadSession
            .getDownloadTasks()
            .parallelStream()
            .map(selectedDownloadTask -> selectedDownloadTask.getFileSource())
            .collect(Collectors.toCollection(ArrayList::new));
    downloadSessionDto.setUrls(urls);
    ArrayList<DownloadTaskDto> downloadTaskDtos =
        downloadSession
            .getDownloadTasks()
            .parallelStream()
            .map(
                selectedDownloadTask ->
                    modelMapper.map(selectedDownloadTask, DownloadTaskDto.class))
            .collect(Collectors.toCollection(ArrayList::new));
    downloadSessionDto.setDownloadTasks(downloadTaskDtos);
    return downloadSessionDto;
  }

  public List<DownloadTask> createDownloadTasks(
      List<String> urlStrings, Long sessionId, String downloadDestination) {
    List<DownloadTask> downloadTasks = new ArrayList<>();
    urlStrings.stream()
        .forEach(
            str ->
                new DownloadTask(
                    sessionId,
                    str,
                    downloadDestination,
                    getProtocol(str),
                    LocalDateTime.now(),
                    null,
                    0l,
                    0l,
                    FileSpeedStatus.UNKNOWN,
                    FileSizeStatus.UNKNOWN));
    return downloadTasks;
  }
}

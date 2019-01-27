package com.github.sampathsl.dmanager.workers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

// Make thread safe singleton
public class DownloadManager extends Observable {

  // The unique instance of this class
  private static volatile DownloadManager downloadManagerInstance = new DownloadManager();

  // Member variables
  private int mNumConnPerDownload = 8;
  private List<DownloadTaskWorker> mDownloadList;

  /** Protected constructor */
  protected DownloadManager() {
    mDownloadList = new ArrayList<>();
  }

  /**
   * Get the unique instance of this class
   *
   * @return the instance of this class
   */
  public static DownloadManager getInstance() {
    return downloadManagerInstance;
  }

  /**
   * Verify whether an URL is valid
   *
   * @param fileURL
   * @return the verified URL, null if invalid
   */
  public static URL verifyURL(String fileURL) {
    // Only allow HTTP URLs.
    if (!fileURL.toLowerCase().startsWith("http://")) return null;

    // Verify format of URL.
    URL verifiedUrl = null;
    try {
      verifiedUrl = new URL(fileURL);
    } catch (Exception e) {
      return null;
    }

    // Make sure URL specifies a file.
    if (verifiedUrl.getFile().length() < 2) return null;

    return verifiedUrl;
  }

  /** Get the max. number of connections per download */
  public int getNumConnPerDownload() {
    return mNumConnPerDownload;
  }

  /** Set the max number of connections per download */
  public void SetNumConnPerDownload(int value) {
    mNumConnPerDownload = value;
  }

  /**
   * Get the downloader object in the list
   *
   * @param index
   * @return
   */
  public DownloadTaskWorker getDownload(int index) {
    return mDownloadList.get(index);
  }

  public void removeDownload(int index) {
    mDownloadList.remove(index);
  }

  /**
   * Get the download list
   *
   * @return
   */
  public List<DownloadTaskWorker> getDownloadList() {
    return mDownloadList;
  }

  public DownloadTaskWorker createDownload(
      URL verifiedURL, String outputFolder, int blockSize, int bufferSize, long taskId) {
    DownloadTaskWorker fd =
        new TaskDownloader(verifiedURL, outputFolder, blockSize, bufferSize, taskId);
    mDownloadList.add(fd);
    return fd;
  }
}

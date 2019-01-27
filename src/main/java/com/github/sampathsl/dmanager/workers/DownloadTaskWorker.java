package com.github.sampathsl.dmanager.workers;

import com.github.sampathsl.dmanager.util.DownloadStatus;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;

public abstract class DownloadTaskWorker extends Observable implements Runnable {

  protected static int BLOCK_SIZE;
  protected static int BUFFER_SIZE;
  protected static int MIN_DOWNLOAD_SIZE;

  protected long downloadTaskId;
  /** The URL to download the file */
  protected URL mURL;
  /** Output folder for downloaded file */
  protected String mOutputFolder;
  /** Number of connections (threads) to download the file */
  protected int mNumConnections;
  /** The file name, extracted from URL */
  protected String mFileName;
  /** Size of the downloaded file (in bytes) */
  protected int mFileSize;
  /** The state of the download */
  protected DownloadStatus mState;
  /** downloaded size of the file (in bytes) */
  protected int mDownloaded;
  /** List of download threads */
  protected ArrayList<DownloadThread> mListDownloadThread;

  /**
   * Constructor
   *
   * @param url
   * @param outputFolder
   */
  protected DownloadTaskWorker(
      URL url, String outputFolder, int blockSize, int bufferSize, long downloadTaskId) {
    mURL = url;
    mOutputFolder = outputFolder;
    mNumConnections = 8;

    // Get the file name from url path
    String fileURL = url.getFile();
    mFileName = fileURL.substring(fileURL.lastIndexOf('/') + 1);
    mFileSize = -1;
    mState = DownloadStatus.DOWNLOADING;
    mDownloaded = 0;

    mListDownloadThread = new ArrayList<>();
    BLOCK_SIZE = blockSize;
    BUFFER_SIZE = bufferSize;
    MIN_DOWNLOAD_SIZE = blockSize * 100;
    this.downloadTaskId = downloadTaskId;
  }

  /** Pause the downloader */
  public void pause() {
    setState(DownloadStatus.PAUSED);
  }

  /** Resume the downloader */
  public void resume() {
    setState(DownloadStatus.DOWNLOADING);
    download();
  }

  /** Cancel the downloader */
  public void cancel() {
    setState(DownloadStatus.CANCELLED);
  }

  /** Get the URL (in String) */
  public String getURL() {
    return mURL.toString();
  }

  /** Get the downloaded file's size */
  public int getFileSize() {
    return mFileSize;
  }

  /** Get the current progress of the download */
  public float getProgress() {
    return ((float) mDownloaded / mFileSize) * 100;
  }

  public long getDownloadTaskId() {
    return downloadTaskId;
  }

  /** Get current state of the downloader */
  public DownloadStatus getState() {
    return mState;
  }

  /** Set the state of the downloader */
  protected void setState(DownloadStatus state) {
    mState = state;
    stateChanged();
  }

  /** Start or resume download */
  protected void download() {
    Thread t = new Thread(this);
    t.start();
    stateChanged();
  }

  /** Increase the downloaded size */
  protected synchronized void downloaded(int value) {
    mDownloaded += value;
    stateChanged();
  }

  /** Set the state has changed and notify the observers */
  protected void stateChanged() {
    setChanged();
    notifyObservers();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DownloadTaskWorker that = (DownloadTaskWorker) o;

    return downloadTaskId == that.downloadTaskId;
  }

  @Override
  public int hashCode() {
    return (int) (downloadTaskId ^ (downloadTaskId >>> 32));
  }

  /** Thread to download part of a file */
  protected abstract class DownloadThread implements Runnable {

    protected int mThreadID;
    protected URL mURL;
    protected String mOutputFile;
    protected int mStartByte;
    protected int mEndByte;
    protected boolean mIsFinished;
    protected Thread mThread;

    public DownloadThread(int threadID, URL url, String outputFile, int startByte, int endByte) {
      mThreadID = threadID;
      mURL = url;
      mOutputFile = outputFile;
      mStartByte = startByte;
      mEndByte = endByte;
      mIsFinished = false;
      download();
    }

    /** Get whether the thread is finished download the part of file */
    public boolean isFinished() {
      return mIsFinished;
    }

    /** Start or resume the download */
    public void download() {
      mThread = new Thread(this);
      mThread.start();
    }

    /**
     * Waiting for the thread to finish
     *
     * @throws InterruptedException
     */
    public void waitFinish() throws InterruptedException {
      mThread.join();
    }
  }
}

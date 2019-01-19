package com.github.sampathsl.dmanager.dmanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "Download_Session", schema = "public")
public class DownloadSession implements Serializable {

    private static final long serialVersionUID = -2342802743086552036L;

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    private ArrayList<DownloadTask> downloadTasks = new ArrayList<>();

    public DownloadSession(ArrayList<DownloadTask> downloadTasks) {
        this.downloadTasks = downloadTasks;
    }

    public DownloadSession(Long version, ArrayList<DownloadTask> downloadTasks) {
        this.version = version;
        this.downloadTasks = downloadTasks;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    private void setVersion(Long version) {
        this.version = version;
    }

    public ArrayList<DownloadTask> getDownloadTasks() {
        return downloadTasks;
    }

    private void setDownloadTasks(ArrayList<DownloadTask> downloadTasks) {
        this.downloadTasks = downloadTasks;
    }
}

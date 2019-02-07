package com.github.sampathsl.dmanager.model;

import com.github.sampathsl.dmanager.util.LocalDateTimeConverter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "Download_Session", schema = "public")
public class DownloadSession implements Serializable {

  private static final long serialVersionUID = -2342802743086552036L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = new Long(0l);

  @Version private Long version = new Long(0l);

  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime created = LocalDateTime.now();

  @Transient private List<DownloadTask> downloadTasks = new ArrayList<>();

  protected DownloadSession() {
    super();
  }

  public DownloadSession(
      Long id, Long version, LocalDateTime created, List<DownloadTask> downloadTasks) {
    this.id = id;
    this.version = version;
    this.created = created;
    this.downloadTasks = downloadTasks;
  }

  public Long getId() {
    return id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public List<DownloadTask> getDownloadTasks() {
    return downloadTasks;
  }

  public void setDownloadTasks(List<DownloadTask> downloadTasks) {
    this.downloadTasks = downloadTasks;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    DownloadSession other = (DownloadSession) obj;
    if (id == null) {
      return other.id == null;
    } else return id.equals(other.id);
  }

  @Override
  public String toString() {
    return "DownloadSession{"
        + "id="
        + id
        + ", version="
        + version
        + ", created="
        + created
        + ", downloadTasks="
        + downloadTasks
        + '}';
  }
}

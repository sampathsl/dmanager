package com.github.sampathsl.dmanager.dmanager.model;

import com.github.sampathsl.dmanager.dmanager.util.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Download_Session", schema = "public")
public class DownloadSession implements Serializable {

  private static final long serialVersionUID = -2342802743086552036L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Version private Long version;

  @NotNull
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime created;

  @Transient private List<DownloadTask> downloadTasks;

  protected DownloadSession() {
    super();
  }

  public DownloadSession(Long version, @NotNull LocalDateTime created) {
    this.setVersion(version);
    this.setCreated(created);
  }

  public DownloadSession(@NotNull LocalDateTime created) {
    this.setCreated(created);
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

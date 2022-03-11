package com.github.benoitf.devfile.extractor.entity;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "DevfileVolume")
@Table(name = "devfile_volume")
public class VolumeImpl{

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "container_path", nullable = false)
  private String containerPath;

  public VolumeImpl() {}

  public VolumeImpl(String name, String containerPath) {
    this.name = name;
    this.containerPath = containerPath;
  }

  public VolumeImpl(VolumeImpl volume) {
    this(volume.getName(), volume.getContainerPath());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContainerPath() {
    return containerPath;
  }

  public void setContainerPath(String containerPath) {
    this.containerPath = containerPath;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof VolumeImpl)) {
      return false;
    }
    VolumeImpl volume = (VolumeImpl) o;
    return Objects.equals(id, volume.id)
        && Objects.equals(name, volume.name)
        && Objects.equals(containerPath, volume.containerPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, containerPath);
  }

  @Override
  public String toString() {
    return "VolumeImpl{"
        + "id='"
        + id
        + '\''
        + ", name='"
        + name
        + '\''
        + ", containerPath='"
        + containerPath
        + '\''
        + '}';
  }
}
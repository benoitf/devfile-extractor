package com.github.benoitf.devfile.extractor.entity;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class MetadataImpl {

  @Column(name = "meta_name")
  private String name;

  /**
   * generateName is used just at workspace create time, when name is generated from it and stored
   * into {@link MetadataImpl#name}, thus it's not needed to persist
   */
  @Transient private String generateName;

  public MetadataImpl() {}

  public MetadataImpl(String name) {
    this.name = name;
  }

  public MetadataImpl(String name, String generateName) {
    this.name = name;
    this.generateName = generateName;
  }

  public MetadataImpl(MetadataImpl metadata) {
    this.name = metadata.getName();
    this.generateName = metadata.getGenerateName();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGenerateName() {
    return generateName;
  }

  public void setGenerateName(String generateName) {
    this.generateName = generateName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataImpl metadata = (MetadataImpl) o;
    return Objects.equals(name, metadata.name)
        && Objects.equals(generateName, metadata.generateName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, generateName);
  }

  @Override
  public String toString() {
    return "MetadataImpl{"
        + "name='"
        + name
        + '\''
        + ", generateName='"
        + generateName
        + '\''
        + '}';
  }
}

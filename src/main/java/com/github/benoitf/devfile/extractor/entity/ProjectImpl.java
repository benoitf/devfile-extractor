package com.github.benoitf.devfile.extractor.entity;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "DevfileProject")
@Table(name = "devfile_project")
public class ProjectImpl {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Embedded private SourceImpl source;

  @Column(name = "clone_path", nullable = false)
  private String clonePath;

  public ProjectImpl() {}

  public ProjectImpl(String name, SourceImpl source, String clonePath) {
    this.name = name;
    if (source != null) {
      this.source = new SourceImpl(source);
    }
    this.clonePath = clonePath;
  }

  public ProjectImpl(ProjectImpl project) {
    this(project.getName(), project.getSource(), project.getClonePath());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SourceImpl getSource() {
    return source;
  }

  public void setSource(SourceImpl source) {
    this.source = source;
  }

  public String getClonePath() {
    return clonePath;
  }

  public void setClonePath(String clonePath) {
    this.clonePath = clonePath;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProjectImpl)) {
      return false;
    }
    ProjectImpl project = (ProjectImpl) o;
    return Objects.equals(id, project.id)
        && Objects.equals(name, project.name)
        && Objects.equals(source, project.source)
        && Objects.equals(clonePath, project.clonePath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, source, clonePath);
  }

  @Override
  public String toString() {
    return "ProjectImpl{"
        + "id='"
        + id
        + '\''
        + ", name='"
        + name
        + '\''
        + ", source="
        + source
        + ", clonePath='"
        + clonePath
        + '\''
        + '}';
  }
}

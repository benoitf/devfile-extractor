package com.github.benoitf.devfile.extractor.entity;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SourceImpl {

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "location", nullable = false)
  private String location;

  @Column(name = "branch")
  private String branch;

  @Column(name = "start_point")
  private String startPoint;

  @Column(name = "tag")
  private String tag;

  @Column(name = "commit_id")
  private String commitId;

  @Column(name = "sparse_checkout_dir")
  private String sparseCheckoutDir;

  public SourceImpl() {}

  public SourceImpl(
      String type,
      String location,
      String branch,
      String startPoint,
      String tag,
      String commitId,
      String sparseCheckoutDir) {
    this.type = type;
    this.location = location;
    this.branch = branch;
    this.startPoint = startPoint;
    this.tag = tag;
    this.commitId = commitId;
    this.sparseCheckoutDir = sparseCheckoutDir;
  }

  public SourceImpl(SourceImpl source) {
    this(
        source.getType(),
        source.getLocation(),
        source.getBranch(),
        source.getStartPoint(),
        source.getTag(),
        source.getCommitId(),
        source.getSparseCheckoutDir());
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public String getStartPoint() {
    return startPoint;
  }

  public void setStartPoint(String startPoint) {
    this.startPoint = startPoint;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getCommitId() {
    return commitId;
  }

  public void setCommitId(String commitId) {
    this.commitId = commitId;
  }

  public String getSparseCheckoutDir() {
    return sparseCheckoutDir;
  }

  public void setSparseCheckoutDir(String sparseCheckoutDir) {
    this.sparseCheckoutDir = sparseCheckoutDir;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SourceImpl)) {
      return false;
    }
    SourceImpl source = (SourceImpl) o;
    return Objects.equals(type, source.type)
        && Objects.equals(location, source.location)
        && Objects.equals(branch, source.branch)
        && Objects.equals(startPoint, source.startPoint)
        && Objects.equals(tag, source.tag)
        && Objects.equals(commitId, source.commitId)
        && Objects.equals(sparseCheckoutDir, source.sparseCheckoutDir);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, location, branch, startPoint, tag, commitId, sparseCheckoutDir);
  }

  @Override
  public String toString() {
    return "SourceImpl{"
        + "type='"
        + type
        + '\''
        + ", location='"
        + location
        + '\''
        + ", branch='"
        + branch
        + '\''
        + ", startPoint='"
        + startPoint
        + '\''
        + ", tag='"
        + tag
        + '\''
        + ", commitId='"
        + commitId
        + '\''
        + ", sparseCheckoutDir='"
        + sparseCheckoutDir
        + '\''
        + '}';
  }
}

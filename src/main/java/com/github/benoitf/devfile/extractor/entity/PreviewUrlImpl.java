package com.github.benoitf.devfile.extractor.entity;


import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public class PreviewUrlImpl {

  @Column(name = "preview_url_port")
  @Convert(disableConversion = true)
  private int port;

  @Column(name = "preview_url_path")
  private String path;

  public PreviewUrlImpl() {}

  public PreviewUrlImpl(PreviewUrlImpl previewUrl) {
    this(previewUrl.getPort(), previewUrl.getPath());
  }

  public PreviewUrlImpl(int port, String path) {
    this.port = port;
    this.path = path;
  }

  public int getPort() {
    return port;
  }

  public String getPath() {
    return path;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PreviewUrlImpl.class.getSimpleName() + "[", "]")
        .add("port=" + port)
        .add("path='" + path + "'")
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PreviewUrlImpl that = (PreviewUrlImpl) o;
    return port == that.port && Objects.equals(path, that.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(port, path);
  }
}
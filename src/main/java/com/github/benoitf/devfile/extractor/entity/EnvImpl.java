package com.github.benoitf.devfile.extractor.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "DevfileEnv")
@Table(name = "devfile_env")
public class EnvImpl {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "value", nullable = false)
  private String value;

  public EnvImpl() {}

  public EnvImpl(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public EnvImpl(EnvImpl env) {
    this(env.getName(), env.getValue());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EnvImpl)) {
      return false;
    }
    EnvImpl env = (EnvImpl) o;
    return Objects.equals(id, env.id)
        && Objects.equals(name, env.name)
        && Objects.equals(value, env.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, value);
  }

  @Override
  public String toString() {
    return "EnvImpl{"
        + "id='"
        + id
        + '\''
        + ", name='"
        + name
        + '\''
        + ", value='"
        + value
        + '\''
        + '}';
  }
}


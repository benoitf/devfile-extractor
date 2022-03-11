package com.github.benoitf.devfile.extractor.entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity(name = "DevfileEntrypoint")
@Table(name = "devfile_entrypoint")
public class EntrypointImpl {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "parent_name", nullable = false)
  private String parentName;

  @Column(name = "container_name", nullable = false)
  private String containerName;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      name = "devfile_entrypoint_commands",
      joinColumns = @JoinColumn(name = "devfile_entrypoint_id"))
  @Column(name = "command")
  private List<String> command;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      name = "devfile_entrypoint_arg",
      joinColumns = @JoinColumn(name = "devfile_entrypoint_id"))
  @Column(name = "arg")
  private List<String> args;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      name = "devfile_entrypoint_selector",
      joinColumns = @JoinColumn(name = "devfile_entrypoint_id"))
  @MapKeyColumn(name = "selector_key")
  @Column(name = "selector")
  private Map<String, String> parentSelector;

  public EntrypointImpl() {}

  public EntrypointImpl(
      String parentName,
      Map<String, String> parentSelector,
      String containerName,
      List<String> command,
      List<String> args) {
    this.parentName = parentName;
    this.parentSelector = parentSelector;
    this.containerName = containerName;
    this.command = command;
    this.args = args;
  }

  public EntrypointImpl(EntrypointImpl entrypoint) {
    this(
        entrypoint.getParentName(),
        entrypoint.getParentSelector(),
        entrypoint.getContainerName(),
        entrypoint.getCommand(),
        entrypoint.getArgs());
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public Map<String, String> getParentSelector() {
    if (parentSelector == null) {
      parentSelector = new HashMap<>();
    }
    return parentSelector;
  }

  public void setParentSelector(Map<String, String> parentSelector) {
    this.parentSelector = parentSelector;
  }

  public String getContainerName() {
    return containerName;
  }

  public void setContainerName(String containerName) {
    this.containerName = containerName;
  }

  public List<String> getCommand() {
    if (command == null) {
      command = new ArrayList<>();
    }
    return command;
  }

  public void setCommand(List<String> command) {
    this.command = command;
  }

  public List<String> getArgs() {
    if (args == null) {
      args = new ArrayList<>();
    }
    return args;
  }

  public void setArgs(List<String> args) {
    this.args = args;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EntrypointImpl)) {
      return false;
    }
    EntrypointImpl that = (EntrypointImpl) o;
    return Objects.equals(parentName, that.parentName)
        && Objects.equals(containerName, that.containerName)
        && Objects.equals(getParentSelector(), that.getParentSelector())
        && Objects.equals(getCommand(), that.getCommand())
        && Objects.equals(getArgs(), that.getArgs());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, parentName, getParentSelector(), getContainerName(), getCommand(), getArgs());
  }
}

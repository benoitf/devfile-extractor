package com.github.benoitf.devfile.extractor.entity;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "DevfileAction")
@Table(name = "devfile_action")
public class ActionImpl {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "component", nullable = false)
  private String component;

  @Column(name = "command", nullable = false)
  private String command;

  @Column(name = "workdir")
  private String workdir;

  @Column(name = "reference")
  private String reference;

  @Column(name = "reference_content")
  private String referenceContent;

  public ActionImpl() {}

  public ActionImpl(
      String type,
      String component,
      String command,
      String workdir,
      String reference,
      String referenceContent) {
    this.type = type;
    this.component = component;
    this.command = command;
    this.workdir = workdir;
    this.reference = reference;
    this.referenceContent = referenceContent;
  }

  public ActionImpl(ActionImpl action) {
    this(
        action.getType(),
        action.getComponent(),
        action.getCommand(),
        action.getWorkdir(),
        action.getReference(),
        action.getReferenceContent());
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getComponent() {
    return component;
  }

  public void setComponent(String component) {
    this.component = component;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getWorkdir() {
    return workdir;
  }

  public void setWorkdir(String workdir) {
    this.workdir = workdir;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }


  public String getReferenceContent() {
    return referenceContent;
  }

  public void setReferenceContent(String referenceContent) {
    this.referenceContent = referenceContent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ActionImpl)) {
      return false;
    }
    ActionImpl action = (ActionImpl) o;
    return Objects.equals(id, action.id)
        && Objects.equals(type, action.type)
        && Objects.equals(component, action.component)
        && Objects.equals(command, action.command)
        && Objects.equals(workdir, action.workdir);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, component, command, workdir);
  }

  @Override
  public String toString() {
    return "ActionImpl{"
        + "id='"
        + id
        + '\''
        + ", type='"
        + type
        + '\''
        + ", component='"
        + component
        + '\''
        + ", command='"
        + command
        + '\''
        + ", workdir='"
        + workdir
        + '\''
        + '}';
  }
}
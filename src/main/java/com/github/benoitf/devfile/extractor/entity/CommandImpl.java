package com.github.benoitf.devfile.extractor.entity;

import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "DevfileCommand")
@Table(name = "devfile_command")
public class CommandImpl {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Embedded private PreviewUrlImpl previewUrl;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "devfile_command_id")
  private List<ActionImpl> actions;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      name = "devfile_command_attributes",
      joinColumns = @JoinColumn(name = "devfile_command_id"))
  @MapKeyColumn(name = "name")
  @Column(name = "value", columnDefinition = "TEXT")
  private Map<String, String> attributes;

  public CommandImpl() {}

  public CommandImpl(
      String name,
      List<? extends ActionImpl> actions,
      Map<String, String> attributes,
      PreviewUrlImpl previewUrl) {
    this.name = name;
    if (actions != null) {
      this.actions = actions.stream().map(ActionImpl::new).collect(toCollection(ArrayList::new));
    }
    if (attributes != null) {
      this.attributes = new HashMap<>(attributes);
    }
    if (previewUrl != null) {
      this.previewUrl = new PreviewUrlImpl(previewUrl.getPort(), previewUrl.getPath());
    }
  }

  public CommandImpl(CommandImpl command) {
    this(command.getName(), command.getActions(), command.getAttributes(), command.getPreviewUrl());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PreviewUrlImpl getPreviewUrl() {
    return previewUrl;
  }

  public void setPreviewUrl(PreviewUrlImpl previewUrl) {
    this.previewUrl = previewUrl;
  }

  public List<ActionImpl> getActions() {
    if (actions == null) {
      actions = new ArrayList<>();
    }
    return actions;
  }

  public void setActions(List<ActionImpl> actions) {
    this.actions = actions;
  }

  public Map<String, String> getAttributes() {
    if (attributes == null) {
      attributes = new HashMap<>();
    }
    return attributes;
  }

  public void setAttributes(Map<String, String> attributes) {
    this.attributes = attributes;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CommandImpl.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("name='" + name + "'")
        .add("previewURL=" + previewUrl)
        .add("actions=" + actions)
        .add("attributes=" + attributes)
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
    CommandImpl command = (CommandImpl) o;
    return Objects.equals(id, command.id)
        && Objects.equals(name, command.name)
        && Objects.equals(previewUrl, command.previewUrl)
        && Objects.equals(actions, command.actions)
        && Objects.equals(attributes, command.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, previewUrl, actions, attributes);
  }
}
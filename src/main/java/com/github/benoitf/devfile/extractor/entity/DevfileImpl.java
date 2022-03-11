package com.github.benoitf.devfile.extractor.entity;

import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

@Entity(name = "Devfile")
@Table(name = "devfile")
public class DevfileImpl {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "api_version", nullable = false)
  private String apiVersion;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "devfile_id")
  private List<ProjectImpl> projects;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "devfile_id")
  private List<ComponentImpl> components;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "devfile_id")
  private List<CommandImpl> commands;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "devfile_attributes", joinColumns = @JoinColumn(name = "devfile_id"))
  @MapKeyColumn(name = "name")
  @Column(name = "value", columnDefinition = "TEXT")
  private Map<String, String> attributes;

  @Embedded private MetadataImpl metadata;

  public DevfileImpl() {}

  public DevfileImpl(
      String apiVersion,
      List<? extends ProjectImpl> projects,
      List<? extends ComponentImpl> components,
      List<? extends CommandImpl> commands,
      Map<String, String> attributes,
      MetadataImpl metadata) {
    this.apiVersion = apiVersion;
    if (projects != null) {
      this.projects = projects.stream().map(ProjectImpl::new).collect(toCollection(ArrayList::new));
    }
    if (components != null) {
      this.components =
          components.stream().map(ComponentImpl::new).collect(toCollection(ArrayList::new));
    }
    if (commands != null) {
      this.commands = commands.stream().map(CommandImpl::new).collect(toCollection(ArrayList::new));
    }
    if (attributes != null) {
      this.attributes = new HashMap<>(attributes);
    }

    if (metadata != null) {
      this.metadata = new MetadataImpl(metadata);
    }
  }

  public DevfileImpl(DevfileImpl devfile) {
    this(
        devfile.getApiVersion(),
        devfile.getProjects(),
        devfile.getComponents(),
        devfile.getCommands(),
        devfile.getAttributes(),
        devfile.getMetadata());
  }

  public String getId() {
    return id.toString();
  }

  public String getApiVersion() {
    return apiVersion;
  }

  public void setApiVersion(String apiVersion) {
    this.apiVersion = apiVersion;
  }

  public void setName(String name) {
    getMetadata().setName(name);
  }

  public List<ProjectImpl> getProjects() {
    if (projects == null) {
      projects = new ArrayList<>();
    }
    return projects;
  }

  public void setProjects(List<ProjectImpl> projects) {
    this.projects = projects;
  }

  public List<ComponentImpl> getComponents() {
    if (components == null) {
      components = new ArrayList<>();
    }
    return components;
  }

  public void setComponents(List<ComponentImpl> components) {
    this.components = components;
  }

  public List<CommandImpl> getCommands() {
    if (commands == null) {
      commands = new ArrayList<>();
    }
    return commands;
  }

  public void setCommands(List<CommandImpl> commands) {
    this.commands = commands;
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

  public MetadataImpl getMetadata() {
    if (metadata == null) {
      metadata = new MetadataImpl();
    }
    return metadata;
  }

  public void setMetadata(MetadataImpl metadata) {
    this.metadata = metadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DevfileImpl)) {
      return false;
    }
    DevfileImpl devfile = (DevfileImpl) o;
    return Objects.equals(id, devfile.id)
        && Objects.equals(apiVersion, devfile.apiVersion)
        && Objects.equals(getProjects(), devfile.getProjects())
        && Objects.equals(getComponents(), devfile.getComponents())
        && Objects.equals(getCommands(), devfile.getCommands())
        && Objects.equals(getAttributes(), devfile.getAttributes())
        && Objects.equals(getMetadata(), devfile.getMetadata());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        apiVersion,
        getProjects(),
        getComponents(),
        getCommands(),
        getAttributes(),
        getMetadata());
  }

  @Override
  public String toString() {
    return "DevfileImpl{"
        + "id='"
        + id
        + '\''
        + ", apiVersion='"
        + apiVersion
        + '\''
        + ", projects="
        + projects
        + ", components="
        + components
        + ", commands="
        + commands
        + ", attributes="
        + attributes
        + ", metadata="
        + metadata
        + '}';
  }
}

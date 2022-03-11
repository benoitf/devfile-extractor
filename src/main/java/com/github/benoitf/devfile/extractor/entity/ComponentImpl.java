package com.github.benoitf.devfile.extractor.entity;

import static java.util.stream.Collectors.toCollection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "DevfileComponent")
@Table(name = "devfile_component")
public class ComponentImpl {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long generatedId;

  @Column(name = "component_id", nullable = false)
  private String componentId;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      name = "devfile_component_preferences",
      joinColumns = @JoinColumn(name = "devfile_component_id"))
  @MapKeyColumn(name = "preference_key")
  @Convert(converter = SerializableConverter.class)
  @Column(name = "preference")
  @JsonDeserialize(using = PreferencesDeserializer.class)
  private Map<String, Serializable> preferences;

  @Column(name = "alias")
  private String alias;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "registry_url")
  private String registryUrl;

  @Column(name = "reference")
  private String reference;

  @Column(name = "reference_content")
  private String referenceContent;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      name = "devfile_component_selector",
      joinColumns = @JoinColumn(name = "devfile_component_id"))
  @MapKeyColumn(name = "selector_key")
  @Column(name = "selector")
  private Map<String, String> selector;

  @Column(name = "image")
  private String image;

  @Column(name = "memory_limit")
  private String memoryLimit;

  @Column(name = "memory_request")
  private String memoryRequest;

  @Column(name = "cpu_limit")
  private String cpuLimit;

  @Column(name = "cpu_request")
  private String cpuRequest;

  @Column(name = "mount_sources")
  private Boolean mountSources;

  @Column(name = "automount_secrets")
  private Boolean automountWorkspaceSecrets;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      name = "devfile_component_command",
      joinColumns = @JoinColumn(name = "devfile_component_id"))
  @Column(name = "command")
  private List<String> command;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      name = "devfile_component_arg",
      joinColumns = @JoinColumn(name = "devfile_component_id"))
  @Column(name = "args")
  private List<String> args;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "devfile_component_id")
  private List<EntrypointImpl> entrypoints;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "devfile_component_id")
  private List<VolumeImpl> volumes;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "devfile_component_id")
  private List<EnvImpl> env;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "devfile_component_id")
  private List<EndpointImpl> endpoints;

  public ComponentImpl() {}

  public ComponentImpl(String type, String id) {
    this.type = type;
    this.componentId = id;
  }

  public ComponentImpl(String type, String id, Map<String, String> preferences) {
    this.type = type;
    this.componentId = id;
    if (preferences != null) {
      this.preferences = new HashMap<>(preferences);
    }
  }

  public ComponentImpl(
      String type,
      String id,
      String reference,
      String referenceContent,
      Map<String, String> selector,
      List<? extends EntrypointImpl> entrypoints) {
    this.type = type;
    this.componentId = id;
    this.reference = reference;
    this.referenceContent = referenceContent;
    if (selector != null) {
      this.selector = new HashMap<>(selector);
    }
    if (entrypoints != null) {
      this.entrypoints =
          entrypoints.stream().map(EntrypointImpl::new).collect(toCollection(ArrayList::new));
    }
  }

  public ComponentImpl(
      String type,
      String alias,
      String id,
      Map<String, Serializable> preferences,
      String registryUrl,
      String reference,
      String referenceContent,
      Map<String, String> selector,
      List<? extends EntrypointImpl> entrypoints,
      String image,
      String memoryLimit,
      String memoryRequest,
      String cpuLimit,
      String cpuRequest,
      Boolean mountSources,
      Boolean automountWorkspaceSecrets,
      List<String> command,
      List<String> args,
      List<? extends VolumeImpl> volumes,
      List<? extends EnvImpl> env,
      List<? extends EndpointImpl> endpoints) {
    this.alias = alias;
    this.type = type;
    this.componentId = id;
    this.registryUrl = registryUrl;
    if (preferences != null) {
      this.preferences = new HashMap<>(preferences);
    }
    this.reference = reference;
    this.referenceContent = referenceContent;
    if (selector != null) {
      this.selector = new HashMap<>(selector);
    }
    if (entrypoints != null) {
      this.entrypoints =
          entrypoints.stream().map(EntrypointImpl::new).collect(toCollection(ArrayList::new));
    }
    this.image = image;
    this.memoryLimit = memoryLimit;
    this.memoryRequest = memoryRequest;
    this.cpuLimit = cpuLimit;
    this.cpuRequest = cpuRequest;
    this.mountSources = mountSources;
    this.automountWorkspaceSecrets = automountWorkspaceSecrets;
    this.command = command;
    this.args = args;
    if (volumes != null) {
      this.volumes = volumes.stream().map(VolumeImpl::new).collect(toCollection(ArrayList::new));
    }
    if (env != null) {
      this.env = env.stream().map(EnvImpl::new).collect(toCollection(ArrayList::new));
    }
    if (endpoints != null) {
      this.endpoints =
          endpoints.stream().map(EndpointImpl::new).collect(toCollection(ArrayList::new));
    }
  }

  public ComponentImpl(ComponentImpl component) {
    this(
        component.getType(),
        component.getAlias(),
        component.getId(),
        component.getPreferences(),
        component.getRegistryUrl(),
        component.getReference(),
        component.getReferenceContent(),
        component.getSelector(),
        component.getEntrypoints(),
        component.getImage(),
        component.getMemoryLimit(),
        component.getMemoryRequest(),
        component.getCpuLimit(),
        component.getCpuRequest(),
        component.getMountSources(),
        component.getAutomountWorkspaceSecrets(),
        component.getCommand(),
        component.getArgs(),
        component.getVolumes(),
        component.getEnv(),
        component.getEndpoints());
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getId() {
    return componentId;
  }

  public void setId(String id) {
    this.componentId = id;
  }

  public String getRegistryUrl() {
    return registryUrl;
  }

  public void setRegistryUrl(String registryUrl) {
    this.registryUrl = registryUrl;
  }

  public Map<String, Serializable> getPreferences() {
    if (preferences == null) {
      preferences = new HashMap<>();
    }
    return preferences;
  }

  public void setPreferences(Map<String, Serializable> preferences) {
    this.preferences = preferences;
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

  public Map<String, String> getSelector() {
    if (selector == null) {
      selector = new HashMap<>();
    }
    return selector;
  }

  public void setSelector(Map<String, String> selector) {
    this.selector = selector;
  }

  public List<EntrypointImpl> getEntrypoints() {
    if (entrypoints == null) {
      entrypoints = new ArrayList<>();
    }
    return entrypoints;
  }

  public void setEntrypoints(List<EntrypointImpl> entrypoints) {
    this.entrypoints = entrypoints;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getMemoryLimit() {
    return memoryLimit;
  }

  public void setMemoryLimit(String memoryLimit) {
    this.memoryLimit = memoryLimit;
  }

  public String getMemoryRequest() {
    return memoryRequest;
  }

  public void setMemoryRequest(String memoryRequest) {
    this.memoryRequest = memoryRequest;
  }

  public String getCpuLimit() {
    return cpuLimit;
  }

  public void setCpuLimit(String cpuLimit) {
    this.cpuLimit = cpuLimit;
  }

  public String getCpuRequest() {
    return cpuRequest;
  }

  public void setCpuRequest(String cpuRequest) {
    this.cpuRequest = cpuRequest;
  }

  public Boolean getMountSources() {
    return mountSources;
  }

  public void setMountSources(Boolean mountSources) {
    this.mountSources = mountSources;
  }

  public Boolean getAutomountWorkspaceSecrets() {
    return automountWorkspaceSecrets;
  }

  public void setAutomountWorkspaceSecrets(Boolean automountWorkspaceSecrets) {
    this.automountWorkspaceSecrets = automountWorkspaceSecrets;
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

  public List<VolumeImpl> getVolumes() {
    if (volumes == null) {
      volumes = new ArrayList<>();
    }
    return volumes;
  }

  public void setVolumes(List<VolumeImpl> volumes) {
    this.volumes = volumes;
  }

  public List<EnvImpl> getEnv() {
    if (env == null) {
      env = new ArrayList<>();
    }
    return env;
  }

  public void setEnv(List<EnvImpl> env) {
    this.env = env;
  }

  public List<EndpointImpl> getEndpoints() {
    if (endpoints == null) {
      endpoints = new ArrayList<>();
    }
    return endpoints;
  }

  public void setEndpoints(List<EndpointImpl> endpoints) {
    this.endpoints = endpoints;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ComponentImpl)) {
      return false;
    }
    ComponentImpl component = (ComponentImpl) o;
    return getMountSources() == component.getMountSources()
        && getAutomountWorkspaceSecrets() == component.getAutomountWorkspaceSecrets()
        && Objects.equals(generatedId, component.generatedId)
        && Objects.equals(alias, component.alias)
        && Objects.equals(type, component.type)
        && Objects.equals(componentId, component.componentId)
        && Objects.equals(registryUrl, component.registryUrl)
        && Objects.equals(reference, component.reference)
        && Objects.equals(referenceContent, component.referenceContent)
        && Objects.equals(image, component.image)
        && Objects.equals(memoryLimit, component.memoryLimit)
        && Objects.equals(getPreferences(), component.getPreferences())
        && Objects.equals(getSelector(), component.getSelector())
        && Objects.equals(getEntrypoints(), component.getEntrypoints())
        && Objects.equals(getCommand(), component.getCommand())
        && Objects.equals(getArgs(), component.getArgs())
        && Objects.equals(getVolumes(), component.getVolumes())
        && Objects.equals(getEnv(), component.getEnv())
        && Objects.equals(getEndpoints(), component.getEndpoints());
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        generatedId,
        alias,
        type,
        componentId,
        registryUrl,
        reference,
        referenceContent,
        image,
        memoryLimit,
        getPreferences(),
        getSelector(),
        getEntrypoints(),
        getMountSources(),
        getAutomountWorkspaceSecrets(),
        getCommand(),
        getArgs(),
        getVolumes(),
        getEnv(),
        getEndpoints());
  }

  @Override
  public String toString() {
    return "ComponentImpl{"
        + "id='"
        + componentId
        + '\''
        + ", alias='"
        + alias
        + '\''
        + ", type='"
        + type
        + '\''
        + ", preferences="
        + preferences
        + ", registryUrl='"
        + registryUrl
        + '\''
        + ", reference='"
        + reference
        + '\''
        + ", referenceContent='"
        + referenceContent
        + '\''
        + ", selector="
        + selector
        + ", entrypoints="
        + entrypoints
        + ", image='"
        + image
        + '\''
        + ", memoryLimit='"
        + memoryLimit
        + '\''
        + ", mountSources="
        + mountSources
        + ", automountWorkspaceSecrets="
        + automountWorkspaceSecrets
        + ", command="
        + command
        + ", args="
        + args
        + ", volumes="
        + volumes
        + ", env="
        + env
        + ", endpoints="
        + endpoints
        + '}';
  }
}
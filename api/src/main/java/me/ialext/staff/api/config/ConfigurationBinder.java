package me.ialext.staff.api.config;

import me.yushust.inject.Binder;

public class ConfigurationBinder {

  private final Binder binder;

  public ConfigurationBinder(
      Binder binder
  ) {
    this.binder = binder;
  }

  public void bind(
      ConfigurationWrapper... configurationWrappers
  ) {
    for (ConfigurationWrapper configurationWrapper : configurationWrappers) {
      binder.bind(ConfigurationWrapper.class)
          .named(configurationWrapper.getFileName().replace(".yml", ""))
          .toInstance(configurationWrapper);
    }

  }

}

package me.ialext.staff.api.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigurationWrapper extends YamlConfiguration {

  private final String fileName;
  private final Plugin plugin;
  private final File file;

  public ConfigurationWrapper(
      Plugin plugin,
      String filename,
      String fileExtension,
      File folder
  ) {
    this.plugin = plugin;
    this.fileName = filename + (filename.endsWith(fileExtension) ? "" : fileExtension);
    this.file = new File(folder, this.fileName);
    this.createFile();
  }

  public ConfigurationWrapper(
      Plugin plugin,
      String fileName
  ) {
    this(
        plugin,
        fileName,
        ".yml"
    );
  }

  public ConfigurationWrapper(
      Plugin plugin,
      String fileName,
      String fileExtension
  ) {
    this(
        plugin,
        fileName,
        fileExtension,
        plugin.getDataFolder()
    );
  }

  public ConfigurationWrapper(
      Plugin plugin,
      String fileName,
      String fileExtension,
      String filePath
  ) {
    this(
        plugin,
        fileName,
        fileExtension,
        new File(
            plugin
                .getDataFolder()
                .getAbsolutePath()
                + "/"
                + filePath
        )
    );
  }

  public String getFileName() {
    return fileName;
  }

  private void createFile() {
    try {
      if (!file.exists()) {
        if (
            plugin
                .getResource(
                    fileName
                ) != null
        ) {
          plugin
              .saveResource(
                  fileName,
                  false
              );
        } else {
          save(file);
        }

        load(file);

        return;
      }

      load(file);

      save(file);
    } catch (InvalidConfigurationException | IOException e) {
      e.printStackTrace();
    }
  }

  public void reload() {
    try {
      load(file);
    } catch (IOException | InvalidConfigurationException e) {
      e.printStackTrace();
    }
  }

  public void save() {
    try {
      save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getString(String path) {
    return ChatColor.translateAlternateColorCodes('&', super.getString(path));
  }

  @Override
  public String getString(
      String path,
      String def
  ) {
    String s = super.getString(path, def);

    return s != null ? ChatColor.translateAlternateColorCodes('&', s) : def;
  }

  @Override
  public List<String> getStringList(String path) {
    List<String> list = super.getStringList(path);

    list.replaceAll(line -> ChatColor.translateAlternateColorCodes('&', line));

    return list;
  }

}

package me.ialext.staff.plugin;

import me.ialext.staff.api.service.Service;
import me.yushust.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.inject.Named;

public class StaffPlugin extends JavaPlugin {

  @Inject @Named("staff") private Service staffService;

  @Override public void onEnable() {
    Injector injector = Injector.create(

    );
    injector.injectMembers(this);

    staffService.start();
  }
}

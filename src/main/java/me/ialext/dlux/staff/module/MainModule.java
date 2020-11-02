package me.ialext.dlux.staff.module;

import me.ialext.dlux.staff.Staff;
import me.ialext.dlux.staff.files.FileBinder;
import me.ialext.dlux.staff.files.FileManager;
import org.bukkit.plugin.Plugin;
import team.unnamed.inject.bind.AbstractModule;

public class MainModule extends AbstractModule {

    private final Staff plugin;

    public MainModule(Staff plugin) {
        this.plugin = plugin;
    }
    @Override
    protected void configure() {
        FileBinder binder = new FileBinder()
                .bind("config", new FileManager(plugin, "config"));

        bind(Plugin.class).to(Staff.class).singleton();
        bind(Staff.class).toInstance(plugin);

        install(binder.build());
        install(new CacheModule());
    }
}

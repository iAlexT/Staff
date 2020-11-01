package me.ialext.dlux.staff;

import me.ialext.dlux.staff.module.MainModule;
import me.ialext.dlux.staff.service.StaffService;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.inject.Inject;
import team.unnamed.inject.Injector;
import team.unnamed.inject.InjectorFactory;
import team.unnamed.inject.name.Named;

public final class Staff extends JavaPlugin {

    @Inject
    @Named("staff-service")
    private StaffService service;

    @Override
    public void onEnable() {
        Injector injector = InjectorFactory.create(new MainModule(this));
        injector.injectMembers(this);

        service.setup();
    }

    @Override
    public void onDisable() {
        service.shutdown();
    }
}

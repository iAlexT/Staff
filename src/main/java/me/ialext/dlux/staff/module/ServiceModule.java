package me.ialext.dlux.staff.module;

import me.ialext.dlux.staff.service.Service;
import me.ialext.dlux.staff.service.StaffService;
import team.unnamed.inject.bind.AbstractModule;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Service.class).named("staff-service").to(StaffService.class)
                .singleton();
    }
}

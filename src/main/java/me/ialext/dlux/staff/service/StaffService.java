package me.ialext.dlux.staff.service;

import me.ialext.dlux.staff.loader.CommandLoader;
import me.ialext.dlux.staff.loader.ListenerLoader;
import team.unnamed.inject.Inject;

public class StaffService implements Service {

    @Inject
    private CommandLoader commandLoader;

    @Inject
    private ListenerLoader listenerLoader;

    @Override
    public void setup() {
        commandLoader.load();
        listenerLoader.load();
    }

    @Override
    public void shutdown() {

    }
}

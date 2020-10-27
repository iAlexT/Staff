package me.ialext.dlux.staff.loader;

import me.ialext.dlux.staff.listener.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import team.unnamed.inject.InjectAll;

@InjectAll
public class ListenerLoader implements Loadable {

    private Plugin plugin;

    private BlockBreakListener blockBreakListener;
    private BlockPlaceListener blockPlaceListener;
    private PlayerAttackListener playerAttackListener;
    private PlayerInteractAtEntityListener playerInteractAtEntityListener;
    private PlayerInteractListener playerInteractListener;
    private PlayerPickupItemListener playerPickupItemListener;
    private PlayerDropListener playerDropListener;

    @Override
    public void load() {
        registerListeners(
                blockBreakListener,
                blockPlaceListener,
                playerAttackListener,
                playerInteractAtEntityListener,
                playerInteractListener,
                playerPickupItemListener,
                playerDropListener
        );
    }

    private void registerListeners(Listener... listeners) {
        for(Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}

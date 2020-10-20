package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.CacheMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

public class PlayerDropListener implements Listener {

    @Inject
    @Named("freeze")
    private CacheMap<UUID, UUID> freezeCache;

    @Inject
    @Named("staff")
    private CacheMap<UUID, ItemStack[]> staffCache;

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if((freezeCache.exists(player.getUniqueId())) || (staffCache.exists(player.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}

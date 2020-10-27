package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.SimpleCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

public class PlayerPickupItemListener implements Listener {

    @Inject
    @Named("staff")
    private SimpleCache<UUID> staffCache;

    @Inject
    @Named("freeze")
    private Cache<UUID, UUID> freezeCache;

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if((staffCache.exists(player.getUniqueId())) || (freezeCache.exists(player.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}

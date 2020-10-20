package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.CacheMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

public class BlockBreakListener implements Listener {

    @Inject
    @Named("staff")
    private CacheMap<UUID, ItemStack[]> staffCache;

    @Inject
    @Named("freeze")
    private CacheMap<UUID, UUID> freezeCache;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if((staffCache.exists(player.getUniqueId())) || (freezeCache.exists(player.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}

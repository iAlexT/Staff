package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

public class PlayerMoveListener implements Listener {

    @Inject
    @Named("freeze")
    private Cache<UUID, UUID> freezeCache;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        UUID player = event.getPlayer().getUniqueId();

        Location from = event.getFrom();
        Location to = event.getTo();

        if(freezeCache.exists(player)) {
            if((from.getX() != to.getX()) || (from.getY() != to.getY())
            || (from.getZ() != to.getZ())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ColorUtil.colorize("&cCannot move while &bfrozen!"));
            }
        }
    }
}

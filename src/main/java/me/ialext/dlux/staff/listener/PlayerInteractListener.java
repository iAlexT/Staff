package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.CacheMap;
import me.ialext.dlux.staff.CacheSet;
import me.ialext.dlux.staff.staff.StaffItems;
import me.ialext.dlux.staff.staff.StaffManager;
import me.ialext.dlux.staff.teleport.TeleportManager;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import team.unnamed.gui.item.type.ItemBuilder;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.Arrays;
import java.util.UUID;

public class PlayerInteractListener implements Listener {

    @Inject
    @Named("staff")
    private CacheMap<UUID, ItemStack[]> staffCache;

    @Inject
    private TeleportManager teleportManager;

    @Inject
    private StaffManager staffManager;

    @Inject
    @Named("vanish")
    private CacheSet<UUID> vanishCache;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getItemInHand();

        ItemStack vanisher = StaffItems.getVanisher();

        ItemStack teleporter = StaffItems.getTeleporter();

        ItemStack randomTeleporter = StaffItems.getRandomTeleport();

        Action action = event.getAction();

        if ((action.equals(Action.RIGHT_CLICK_BLOCK)) || (action.equals(Action.RIGHT_CLICK_AIR))) {
            if (staffCache.exists(player.getUniqueId())) {
                if (item.equals(vanisher)) {
                    if(!vanishCache.exists(player.getUniqueId())) {
                        staffManager.hide(player);
                    } else {
                        staffManager.unhide(player);
                    }
                }

            } else if (player.getItemInHand().equals(teleporter)) {
                Location location = event.getClickedBlock().getLocation();
                player.teleport(location);
            } else if (player.getItemInHand().equals(randomTeleporter)) {
                teleportManager.randomTeleport(player.getUniqueId(), 0, false);
            }
        }
    }
}

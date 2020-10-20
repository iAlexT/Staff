package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.teleport.TeleportManager;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.item.type.ItemBuilder;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.Arrays;
import java.util.UUID;

public class PlayerInteractListener implements Listener {

    @Inject
    @Named("staff")
    private Cache<UUID, ItemStack[]> staffCache;

    @Inject
    private TeleportManager teleportManager;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getItemInHand();

        if (staffCache.exists(player.getUniqueId())) {
            if (item.equals(getVanisher())) {
                event.setCancelled(true);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.canSee(player)) {
                        p.hidePlayer(player);
                    } else {
                        p.showPlayer(player);
                    }
                }

            }
        } else if (player.getItemInHand().equals(getTeleporter())) {
            event.setCancelled(true);
            player.teleport(player.getEyeLocation());
        } else if (player.getItemInHand().equals(getRandomTeleporter())) {
            event.setCancelled(true);
            teleportManager.randomTeleport(player.getUniqueId(), 0, false);
        }
    }

    private ItemStack getVanisher() {
        return ItemBuilder.newBuilder(Material.INK_SACK,
                1,
                (byte) 9)
                .name(ColorUtil.colorize("&eToggle vanish"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cWith this dye you can toggle vanish",
                        "&9-", "&9- &bDlux")))
                .build();
    }
    private ItemStack getTeleporter() {
        return ItemBuilder.newBuilder(Material.COMPASS)
                .name(ColorUtil.colorize("&5Teleporter"))
                .lore(ColorUtil.colorizeList(Arrays.asList(
                        "&9- &cWith this compass you can teleport there where you are looking",
                        "&9-", "&bDlux")))
                .build();
    }

    private ItemStack getRandomTeleporter() {

        return ItemBuilder.newBuilder(Material.EYE_OF_ENDER)
                .name(ColorUtil.colorize("&5Random teleport"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cWith this eye you can teleport to a random player",
                        "&9-", "&9- &bDlux")))
                .build();
    }
}

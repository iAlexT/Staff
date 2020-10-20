package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.CacheMap;
import me.ialext.dlux.staff.teleport.TeleportManager;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.item.type.ItemBuilder;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.Arrays;
import java.util.UUID;

public class PlayerInteractAtEntityListener implements Listener {

    @Inject
    @Named("staff")
    private CacheMap<UUID, ItemStack[]> staffCache;

    @Inject
    @Named("freeze")
    private CacheMap<UUID, UUID> freezeCache;

    @Inject
    private TeleportManager teleportManager;

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if(!(entity instanceof Player)) return;

        Player clicked = (Player) entity;
        if(clicked.hasPermission("dlux.staff")) return;

        if(staffCache.exists(player.getUniqueId())) {
            if(player.getItemInHand().equals(getFreezeWand())) {
                if(!freezeCache.exists(clicked.getUniqueId())) {
                    freezeCache.add(clicked.getUniqueId(), player.getUniqueId());
                } else {
                    freezeCache.remove(clicked.getUniqueId());
                }
            } else if(player.getItemInHand().equals(getInspector())) {
                player.openInventory(clicked.getInventory());
            }
        }
    }

    private ItemStack getFreezeWand() {

        return ItemBuilder.newBuilder(Material.BLAZE_ROD)
                .name(ColorUtil.colorize("&bFreeze &6wand"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cUse this wand to freeze players",
                        "", "&dDlux")))
                .build();
    }

    private ItemStack getInspector() {
        return ItemBuilder.newBuilder(Material.ENDER_CHEST)
                .name(ColorUtil.colorize("&9Inspection"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cUse this chest to open player inventories",
                        "", "&dDlux")))
                .build();
    }
}

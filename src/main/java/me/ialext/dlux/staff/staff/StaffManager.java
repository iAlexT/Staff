package me.ialext.dlux.staff.staff;

import me.ialext.dlux.staff.CacheMap;
import me.ialext.dlux.staff.CacheSet;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.item.type.ItemBuilder;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StaffManager {

    private Map<UUID, ItemStack[]> armor = new HashMap<>();

    @Inject
    @Named("staff")
    private CacheMap<UUID, ItemStack[]> staffCache;

    @Inject
    @Named("vanish")
    private CacheSet<UUID> vanishCache;

    public void enable(Player player) {
        saveItems(player);
        getStaffItems(player);

        hide(player);
        staffCache.add(player.getUniqueId(), player.getInventory().getContents());
        armor.put(player.getUniqueId(), player.getInventory().getContents());
    }

    public void disable(Player player) {
        player.getInventory().clear();

        player.getInventory().setContents(staffCache.get().get(player.getUniqueId()));

        unhide(player);
        staffCache.remove(player.getUniqueId());
        armor.remove(player.getUniqueId());
    }

    public void hide(Player player) {
        for(Player players : Bukkit.getOnlinePlayers()) {
            players.hidePlayer(player);
        }

        vanishCache.add(player.getUniqueId());
    }

    public void unhide(Player player) {
        for(Player players : Bukkit.getOnlinePlayers()) {
            players.showPlayer(player);
        }

        vanishCache.remove(player.getUniqueId());
    }

    private void saveItems(Player player) {
        staffCache.add(player.getUniqueId(), player.getInventory().getContents());
        armor.put(player.getUniqueId(), player.getInventory().getArmorContents());
    }

    private void getStaffItems(Player player) {
        ItemStack freeze = ItemBuilder.newBuilder(Material.BLAZE_ROD)
                .name(ColorUtil.colorize("&bFreeze &6wand"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cUse this wand to freeze players",
                        "", "&dDlux")))
                .build();

        ItemStack inspector = ItemBuilder.newBuilder(Material.ENDER_CHEST)
                .name(ColorUtil.colorize("&9Inspection"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cUse this chest to open player inventories",
                        "", "&dDlux")))
                .build();

        ItemStack teleport = ItemBuilder.newBuilder(Material.COMPASS)
                .name(ColorUtil.colorize("&5Teleporter"))
                .lore(ColorUtil.colorizeList(Arrays.asList(
                        "&9- &cWith this compass you can teleport there where you are looking",
                        "&9-", "&bDlux")))
                .build();

        ItemStack randomTeleport = ItemBuilder.newBuilder(Material.EYE_OF_ENDER)
                .name(ColorUtil.colorize("&5Random teleport"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cWith this eye you can teleport to a random player",
                        "&9-", "&9- &bDlux")))
                .build();

        ItemStack vanisher = ItemBuilder.newBuilder(Material.INK_SACK,
                1,
                (byte) 9)
                .name(ColorUtil.colorize("&eToggle vanish"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cWith this dye you can toggle vanish",
                        "&9-", "&9- &bDlux")))
                .build();

        player.getInventory().clear();
        player.getInventory().setItem(1, freeze);
        player.getInventory().setItem(3, inspector);
        player.getInventory().setItem(5, teleport);
        player.getInventory().setItem(7, randomTeleport);
        player.getInventory().setItem(9, vanisher);
    }

    private void getPlayerItems(Player player) {
        player.getInventory().setContents(staffCache.get().get(player.getUniqueId()));
        player.getInventory().setArmorContents(armor.get(player.getUniqueId()));

        staffCache.remove(player.getUniqueId());
        armor.remove(player.getUniqueId());
    }
}
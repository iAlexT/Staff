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
        player.getInventory().clear();
        player.getInventory().setItem(1, StaffItems.getFreezeWand());
        player.getInventory().setItem(3, StaffItems.getInspector());
        player.getInventory().setItem(5, StaffItems.getTeleporter());
        player.getInventory().setItem(7, StaffItems.getRandomTeleport());
        player.getInventory().setItem(9, StaffItems.getVanisher());
    }

    private void getPlayerItems(Player player) {
        player.getInventory().setContents(staffCache.get().get(player.getUniqueId()));
        player.getInventory().setArmorContents(armor.get(player.getUniqueId()));

        staffCache.remove(player.getUniqueId());
        armor.remove(player.getUniqueId());
    }
}
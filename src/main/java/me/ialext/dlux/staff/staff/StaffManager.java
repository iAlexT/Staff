package me.ialext.dlux.staff.staff;

import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.SimpleCache;
import me.ialext.dlux.staff.factory.ItemFactory;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.*;

import static org.bukkit.Bukkit.getPlayer;

public class StaffManager {

    @Inject
    @Named("staff")
    private SimpleCache<UUID> staffCache;

    @Inject
    @Named("vanish")
    private SimpleCache<UUID> vanishCache;

    @Inject
    @Named("freeze")
    private Cache<UUID, UUID> freezeCache;

    @Inject
    @Named("items")
    private Cache<UUID, ItemStack[]> itemsCache;

    public void enable(UUID player) {
        saveItems(player);
        giveStaffItems(player);
        staffCache.add(player);
        getPlayer(player).sendMessage(ColorUtil.colorize("&eSuccessfully &aenabled &dStaff mode"));
    }

    public void disable(UUID player) {
        getItems(player);
        staffCache.remove(player);
        getPlayer(player).sendMessage(ColorUtil.colorize("&eSuccessfully &cdisabled &dStaff mode"));
    }
    
    public boolean isInStaffMode(UUID player) {
        return staffCache.exists(player);
    }

    private void saveItems(UUID player) {
        itemsCache.add(player, getPlayer(player).getInventory().getContents());
    }
    
    private void getItems(UUID player) {
        getPlayer(player).getInventory().clear();
        Optional<ItemStack[]> contents = itemsCache.find(player);
        contents.ifPresent(items -> getPlayer(player).getInventory().setContents(items));
    }
    
    private void giveStaffItems(UUID player) {

        getPlayer(player).getInventory().clear();
        Arrays.stream(getPlayer(player).getInventory().getArmorContents()).forEach(item -> item.setType(Material.AIR));

        getPlayer(player).getInventory().setItem(0, ItemFactory.getFreezeWand());
        getPlayer(player).getInventory().setItem(2, ItemFactory.getInspector());
        getPlayer(player).getInventory().setItem(4, ItemFactory.getTeleporter());
        getPlayer(player).getInventory().setItem(6, ItemFactory.getRandomTeleport());
        getPlayer(player).getInventory().setItem(8, ItemFactory.getVanisher());
    }
}

package me.ialext.dlux.staff.staff;

import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.SimpleCache;
import me.ialext.dlux.staff.util.ColorUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.*;

public class StaffManager {

    private final Map<UUID, ItemStack[]> armor = new HashMap<>();

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

    public void enable(Player player) {
        saveItems(player);
        addToCache(player);
        getStaffItems(player);

        staffCache.add(player.getUniqueId());

        player.sendMessage(ColorUtil.colorize("&aEnabled &dStaff mode"));
    }

    public void disable(Player player) {
        removeFromCache(player);
        getItems(player);
        staffCache.remove(player.getUniqueId());
    }
    public void hideIfVisible(Player player) {
        for(Player players : Bukkit.getOnlinePlayers()) {
            if(players.canSee(player)) {
                players.hidePlayer(player);
                player.sendMessage(ColorUtil.colorize("&aEnabled vanish mode."));

                vanishCache.remove(player.getUniqueId());
            } else {
                player.showPlayer(player);
                player.sendMessage(ColorUtil.colorize("&aDisabled vanish mode."));
                vanishCache.add(player.getUniqueId());
            }
        }

        Bukkit.getOnlinePlayers()
                .stream()
                .filter(p -> p.canSee(player))
                .forEach(pl -> pl.hidePlayer(player));

        Bukkit.getOnlinePlayers()
                .stream()
                .filter(p -> !p.canSee(player))
                .forEach(p -> p.showPlayer(player));
    }

    private void addToCache(Player player) {
        staffCache.add(player.getUniqueId());
    }

    private void removeFromCache(Player player) {
        staffCache.remove(player.getUniqueId());
    }

    public void freeze(Player player, Player staff) {
        if(!freezeCache.exists(player.getUniqueId())) {
            freezeCache.add(player.getUniqueId(), staff.getUniqueId());
            staff.sendMessage(ColorUtil.colorize("&aSuccessfully &bfrozen &e" + player.getName()));
            player.getPlayer().sendMessage(ColorUtil.colorize("&cYou have been &bfrozen &cby &e" + staff.getName()));
        } else {
            freezeCache.remove(player.getUniqueId());
            staff.sendMessage(ColorUtil.colorize("&aSuccessfully &bun-frozen &e" + player.getName()));
            player.getPlayer().sendMessage(ColorUtil.colorize("&aYou are no longer frozen!"));
        }
    }
    private void hide(Player player) {
        Bukkit.getOnlinePlayers().forEach(p -> p.hidePlayer(player));

        vanishCache.add(player.getUniqueId());
    }

    private void unhide(Player player) {
        Bukkit.getOnlinePlayers().forEach(p -> p.hidePlayer(player));

        vanishCache.remove(player.getUniqueId());
    }

    private void saveItems(Player player) {
        itemsCache.add(player.getUniqueId(), player.getInventory().getContents());
    }
    private void getStaffItems(Player player) {
        player.getInventory().clear();
        Arrays.stream(player.getInventory().getArmorContents()).forEach(item -> item.setType(Material.AIR));

        player.getInventory().setItem(1, ItemFactory.getFreezeWand());
        player.getInventory().setItem(3, ItemFactory.getInspector());
        player.getInventory().setItem(5, ItemFactory.getTeleporter());
        player.getInventory().setItem(7, ItemFactory.getRandomTeleport());
        player.getInventory().setItem(9, ItemFactory.getVanisher());
    }

    private void getItems(Player player) {
        player.getInventory().clear();
        Optional<ItemStack[]> contents = itemsCache.find(player.getUniqueId());
        contents.ifPresent(items -> player.getInventory().setContents(items));
    }
}
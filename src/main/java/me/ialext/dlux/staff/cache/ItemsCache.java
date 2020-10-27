package me.ialext.dlux.staff.cache;

import me.ialext.dlux.staff.Cache;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemsCache implements Cache<UUID, ItemStack[]> {

    private final Map<UUID, ItemStack[]> items = new HashMap<>();

    @Override
    public Map<UUID, ItemStack[]> get() {
        return items;
    }
}

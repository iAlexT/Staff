package me.ialext.dlux.staff.cache;

import me.ialext.dlux.staff.CacheMap;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StaffCache implements CacheMap<UUID, ItemStack[]> {

    private final Map<UUID, ItemStack[]> staffs = new HashMap<>();

    @Override
    public Map<UUID, ItemStack[]> get() {
        return staffs;
    }
}

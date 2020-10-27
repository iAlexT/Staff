package me.ialext.dlux.staff.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface ValidationUtils {

    static boolean compareItems(ItemStack first, ItemStack second) {
        ItemMeta firstMeta = first.getItemMeta();
        ItemMeta secondMeta = second.getItemMeta();

        return (firstMeta.getDisplayName().equals(secondMeta.getDisplayName()))
                && (firstMeta.getLore().equals(secondMeta.getLore()));
    }
}

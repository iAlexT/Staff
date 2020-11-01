package me.ialext.dlux.staff.factory;

import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.item.type.ItemBuilder;

import java.util.Arrays;

public interface ItemFactory {

    static ItemStack getFreezeWand() {
        return ItemBuilder.newBuilder(Material.BLAZE_ROD)
                .name(ColorUtil.colorize("&bFreeze &6wand"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cUse this wand to freeze players",
                        "", "&dDlux")))
                .build();

    }

    static ItemStack getInspector() {
        return ItemBuilder.newBuilder(Material.ENDER_CHEST)
                .name(ColorUtil.colorize("&9Inspection"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cUse this chest to open player inventories",
                        "", "&dDlux")))
                .build();
    }

    static ItemStack getTeleporter() {
        return ItemBuilder.newBuilder(Material.COMPASS)
                .name(ColorUtil.colorize("&5Teleporter"))
                .lore(ColorUtil.colorizeList(Arrays.asList(
                        "&9- &cWith this compass you can teleport there where you are looking",
                        "&9-", "&bDlux")))
                .build();

    }

    static ItemStack getRandomTeleport() {
        return ItemBuilder.newBuilder(Material.EYE_OF_ENDER)
                .name(ColorUtil.colorize("&5Random teleport"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cWith this eye you can teleport to a random player",
                        "&9-", "&9- &bDlux")))
                .build();

    }

    static ItemStack getVanisher() {
        return ItemBuilder.newBuilder(Material.INK_SACK,
                1,
                (byte) 9)
                .name(ColorUtil.colorize("&eToggle vanish"))
                .lore(ColorUtil.colorizeList(Arrays.asList("&9- &cWith this dye you can toggle vanish",
                        "&9-", "&9- &bDlux")))
                .build();
    }
}

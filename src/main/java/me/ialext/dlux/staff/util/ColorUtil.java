package me.ialext.dlux.staff.util;

import org.bukkit.ChatColor;

import java.util.List;

public interface ColorUtil {

    static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    static List<String> colorizeList(List<String> list) {
        list.replaceAll(line -> ChatColor.translateAlternateColorCodes('&',
                line));

        return list;
    }
}

package me.ialext.dlux.staff.staff;

import me.ialext.dlux.staff.SimpleCache;
import me.ialext.dlux.staff.util.ColorUtil;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import static org.bukkit.Bukkit.getPlayer;

import java.util.UUID;

public class StaffChatManager {

    @Inject
    @Named("staff-chat")
    private SimpleCache<UUID> staffChatCache;

    public void enable(UUID player) {
        staffChatCache.add(player);
        getPlayer(player).sendMessage(ColorUtil.colorize("&eSuccessfully &aenabled &9Staff Chat"));
    }

    public void disable(UUID player) {
        staffChatCache.remove(player);
        getPlayer(player).sendMessage(ColorUtil.colorize("&eSuccessfully &cdisabled &9Staff Chat"));
    }

    public boolean isEnabled(UUID player) {
        return staffChatCache.exists(player);
    }
}

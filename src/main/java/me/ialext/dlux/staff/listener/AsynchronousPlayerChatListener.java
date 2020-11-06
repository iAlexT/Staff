package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.files.FileCreator;
import me.ialext.dlux.staff.staff.StaffChatManager;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

public class AsynchronousPlayerChatListener implements Listener {

    @Inject
    private StaffChatManager staffChatManager;

    @Inject
    @Named("config")
    private FileCreator config;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String format = config.getString("chat.staff.format")
                .replace("%player%", player.getName());

        if(staffChatManager.isEnabled(player.getUniqueId())) {
            event.setCancelled(true);

            Bukkit.getOnlinePlayers()
                    .stream()
                    .filter(p -> p.hasPermission("dlux.staff"))
                    .forEach(p -> p.sendMessage(ColorUtil.colorize(format + event.getMessage())));
        }
    }
}

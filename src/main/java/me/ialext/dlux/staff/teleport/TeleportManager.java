package me.ialext.dlux.staff.teleport;

import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import team.unnamed.inject.Inject;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

/**
 * Inspired by https://github.com/pixeldev/RedisTeleport/blob/master/src/main/java/perfect/teleport/managers/TPAManager.java
 */
public class TeleportManager {

    @Inject
    private Plugin plugin;

    /**
     *
     * @param player Player that is gonna be teleported
     * @param delay Delay in seconds
     */
    public void startCountdown(UUID player, int delay) {

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () ->
                Bukkit.getPlayer(player).sendMessage(ColorUtil.colorize(
                "&fTeleporting in &a" + delay)), delay * 20);
    }
    /**
     *
     * @param player Player that is gonna be teleported
     * @param delay Delay time in seconds
     * @param cooldown Teleport is instant
     */
    public void randomTeleport(UUID player, int delay, boolean cooldown) {
        Random r = new Random();
        Optional<? extends Player> target = Bukkit.getOnlinePlayers().stream().filter(p -> p.getUniqueId() != player).findAny();
        if(!target.isPresent()) {
            Bukkit.getPlayer(player).sendMessage(ColorUtil.colorize("&cCannot use random teleport if online players is minor than 2"));

        } else {
            Bukkit.getPlayer(player).teleport(target.get());
        }
    }

    public void randomTeleport(UUID player) {
        Random r = new Random();
        Optional<? extends Player> target = Bukkit.getOnlinePlayers().stream().filter(p -> p.getUniqueId() != player).findAny();
        if(!target.isPresent()) {
            Bukkit.getPlayer(player).sendMessage(ColorUtil.colorize("&cCannot use random teleport yet, you're alone in the server!"));
        } else {
            Bukkit.getPlayer(player).teleport(target.get());
        }
    }
}

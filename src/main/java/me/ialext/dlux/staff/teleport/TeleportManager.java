package me.ialext.dlux.staff.teleport;

import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import team.unnamed.inject.Inject;

import java.util.ArrayList;
import java.util.List;
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

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Bukkit.getPlayer(player).sendMessage(ColorUtil.colorize(
                    "&fTeleporting in &a" + delay));

        }, delay * 20);
    }

    /**
     *
     * @param player Player that is gonna be teleported
     * @param delay Delay time in seconds
     * @param cooldown Teleport is instant
     */
    public void randomTeleport(UUID player, int delay, boolean cooldown) {
        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        onlinePlayers.remove(Bukkit.getPlayer(player));
        Random random = new Random();
        int randomInt = random.nextInt(onlinePlayers.size());
        Player target = onlinePlayers.get(randomInt);

        if(cooldown) {
            startCountdown(player, delay);
            Bukkit.getPlayer(player).teleport(target);
        } else {
            Bukkit.getPlayer(player).teleport(target);
        }
    }
}

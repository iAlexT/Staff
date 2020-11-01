package me.ialext.dlux.staff.staff;

import me.ialext.dlux.staff.SimpleCache;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Bukkit;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import static org.bukkit.Bukkit.getPlayer;

import java.util.UUID;

public class VanishManager {

    @Inject
    @Named("vanish")
    private SimpleCache<UUID> vanishCache;

    public void hide(UUID player) {
        Bukkit.getOnlinePlayers()
                .stream()
                .filter(p -> p.getUniqueId() != player)
                .filter(p -> !p.hasPermission("dlux.staff"))
                .forEach(p -> p.hidePlayer(getPlayer(player)));
        vanishCache.add(player);
        getPlayer(player).sendMessage(ColorUtil.colorize("&eSuccessfully &aenabled &dVanish mode"));
    }

    public void show(UUID player) {
        Bukkit.getOnlinePlayers()
                .forEach(p -> p.showPlayer(getPlayer(player)));
        vanishCache.remove(player);
        getPlayer(player).sendMessage(ColorUtil.colorize("&eSuccessfully &cdisabled &dVanish mode"));
    }

    public boolean isHidden(UUID player) {
        return vanishCache.exists(player);
    }
}

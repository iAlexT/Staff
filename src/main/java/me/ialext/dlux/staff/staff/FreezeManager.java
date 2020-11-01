package me.ialext.dlux.staff.staff;

import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.util.ColorUtil;
import me.ialext.dlux.staff.util.PotionUtil;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

import static org.bukkit.Bukkit.getPlayer;

public class FreezeManager {

    @Inject
    @Named("freeze")
    private Cache<UUID, UUID> freezeCache;

    public void freezePlayer(UUID player, UUID staff) {
        freezeCache.add(player, staff);
        getPlayer(player).sendMessage(ColorUtil.colorize("&cYou've been &bfrozen &cby &e" +
                getPlayer(staff).getName()));
        getPlayer(staff).sendMessage(ColorUtil.colorize("&aSuccessfully &bfrozen &e" +
                getPlayer(player).getName()));
        PotionUtil.addFrozenEffects(player);
    }

    public void unfreezePlayer(UUID player) {
        freezeCache.remove(player);
        getPlayer(player).sendMessage(ColorUtil.colorize("&aYou are no longer &bfrozen"));
        PotionUtil.removeFrozenEffects(player);
    }

    public boolean isFrozen(UUID player) {
        return freezeCache.exists(player);
    }
}

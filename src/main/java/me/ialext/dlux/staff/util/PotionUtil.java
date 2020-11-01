package me.ialext.dlux.staff.util;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

import static org.bukkit.Bukkit.getPlayer;

public interface PotionUtil {

    static void addFrozenEffects(UUID player) {
        getPlayer(player).addPotionEffect(new PotionEffect(PotionEffectType.JUMP,
                Integer.MAX_VALUE, 128));
        getPlayer(player).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
                Integer.MAX_VALUE, 128));
    }

    static void removeFrozenEffects(UUID player) {
        getPlayer(player).removePotionEffect(PotionEffectType.JUMP);
        getPlayer(player).removePotionEffect(PotionEffectType.SLOW);
    }
}

package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.Cache;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

public class PlayerAttackListener implements Listener {

    @Inject
    @Named("freeze")
    private Cache<UUID, UUID> freezeCache;

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity attacker = event.getDamager();

        if((!(entity instanceof Player)) || (!(attacker instanceof Player))) return;

        Player player = (Player) entity;
        Player damager = (Player) attacker;

        if((freezeCache.exists(player.getUniqueId())) || (freezeCache.exists(damager.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}

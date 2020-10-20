package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.CacheMap;
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
    private CacheMap<UUID, UUID> freezeCache;

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        Entity player = event.getEntity();
        Entity attacker = event.getDamager();

        if(!(attacker instanceof Player)) {
            event.setCancelled(true);
        }

        if((freezeCache.exists(player.getUniqueId())) || (freezeCache.exists(attacker.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}

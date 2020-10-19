package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.Cache;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;
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
        Entity damager = event.getDamager();

        if(!(entity instanceof Player)) return;

        Player playerSender = null;
        Player playerTarget = (Player) entity;

        if (entity instanceof Projectile) {
            Projectile projectile = (Projectile) entity;

            ProjectileSource shooter = projectile.getShooter();

            if (shooter instanceof Player) {
                playerSender = (Player) shooter;
            }
        } else if (damager instanceof Player) {
            playerSender = (Player) damager;
        }

        Player player = (Player) entity;
        Player attacker = (Player) damager;
        if((freezeCache.exists(player.getUniqueId())) || (freezeCache.exists(attacker.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}

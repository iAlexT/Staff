package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.staff.FreezeManager;
import me.ialext.dlux.staff.staff.StaffManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import team.unnamed.inject.Inject;

public class PlayerAttackListener implements Listener {

    @Inject
    private StaffManager staffManager;

    @Inject
    private FreezeManager freezeManager;

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity attacker = event.getDamager();

        if((!(entity instanceof Player)) || (!(attacker instanceof Player))) return;

        Player player = (Player) entity;
        Player damager = (Player) attacker;

        if((staffManager.isInStaffMode(player.getUniqueId())) || (freezeManager.isFrozen(player.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}

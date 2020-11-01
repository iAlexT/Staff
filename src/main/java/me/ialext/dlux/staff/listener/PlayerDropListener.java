package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.staff.FreezeManager;
import me.ialext.dlux.staff.staff.StaffManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import team.unnamed.inject.Inject;

public class PlayerDropListener implements Listener {

    @Inject
    private StaffManager staffManager;

    @Inject
    private FreezeManager freezeManager;

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if((staffManager.isInStaffMode(player.getUniqueId())) || (freezeManager.isFrozen(player.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}

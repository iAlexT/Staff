package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.factory.ItemFactory;
import me.ialext.dlux.staff.staff.StaffManager;
import me.ialext.dlux.staff.staff.VanishManager;
import me.ialext.dlux.staff.teleport.TeleportManager;
import me.ialext.dlux.staff.util.ValidationUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import team.unnamed.inject.Inject;

public class PlayerInteractListener implements Listener {


    @Inject
    private TeleportManager teleportManager;

    @Inject
    private StaffManager staffManager;

    @Inject
    private VanishManager vanishManager;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        ItemStack vanisher = ItemFactory.getVanisher();

        ItemStack teleporter = ItemFactory.getTeleporter();

        ItemStack randomTeleporter = ItemFactory.getRandomTeleport();

        if(item == null) return;
        
        if(staffManager.isInStaffMode(player.getUniqueId())) {
            if(item.hasItemMeta()) {
                if(ValidationUtils.compareItems(item, vanisher)) {
                    if(!vanishManager.isHidden(player.getUniqueId())) {
                        vanishManager.hide(player.getUniqueId());
                    } else {
                        vanishManager.show(player.getUniqueId());
                    }
                }

                if(ValidationUtils.compareItems(item, teleporter)) {
                    Vector vector = player.getEyeLocation().getDirection().normalize().multiply(2);
                    player.setVelocity(vector);
                }

                if(ValidationUtils.compareItems(item, randomTeleporter)) {
                    teleportManager.randomTeleport(player.getUniqueId(), 0, false);
                }
            }
        }
    }
}

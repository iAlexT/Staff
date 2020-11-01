package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.factory.ItemFactory;
import me.ialext.dlux.staff.staff.FreezeManager;
import me.ialext.dlux.staff.staff.StaffManager;
import me.ialext.dlux.staff.teleport.TeleportManager;
import me.ialext.dlux.staff.util.ValidationUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import team.unnamed.inject.Inject;

public class PlayerInteractAtEntityListener implements Listener {


    @Inject
    private FreezeManager freezeManager;

    @Inject
    private TeleportManager teleportManager;

    @Inject
    private StaffManager staffManager;

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack item = player.getItemInHand();

        if(player.getItemInHand() == null) return;
        if(entity == null) return;
        if(!(entity instanceof Player)) return;

        ItemStack inspector = ItemFactory.getInspector();

        ItemStack freezeWand = ItemFactory.getFreezeWand();

        Player clicked = (Player) entity;

        if(staffManager.isInStaffMode(player.getUniqueId())) {
            if(clicked.hasPermission("dlux.staff")) return;

            if(item.hasItemMeta()) {

                if(ValidationUtils.compareItems(item, inspector)) {
                    player.openInventory(clicked.getInventory());
                }

                if(ValidationUtils.compareItems(item, freezeWand)) {
                    if(!freezeManager.isFrozen(clicked.getUniqueId())) {
                        freezeManager.freezePlayer(clicked.getUniqueId(), player.getUniqueId());
                    } else {
                        freezeManager.unfreezePlayer(clicked.getUniqueId());
                    }
                }
            }
        }
    }
}

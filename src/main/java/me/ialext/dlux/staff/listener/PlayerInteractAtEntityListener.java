package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.SimpleCache;
import me.ialext.dlux.staff.staff.ItemFactory;
import me.ialext.dlux.staff.staff.StaffManager;
import me.ialext.dlux.staff.teleport.TeleportManager;
import me.ialext.dlux.staff.util.ColorUtil;
import me.ialext.dlux.staff.util.ValidationUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

public class PlayerInteractAtEntityListener implements Listener {

    @Inject
    @Named("staff")
    private SimpleCache<UUID> staffCache;

    @Inject
    @Named("freeze")
    private Cache<UUID, UUID> freezeCache;

    @Inject
    private TeleportManager teleportManager;

    @Inject
    private StaffManager staffManager;

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack item = player.getItemInHand();
        ItemMeta meta = item.getItemMeta();

        if(player.getItemInHand() == null) return;
        if(entity == null) return;
        if(!(entity instanceof Player)) return;

        ItemStack inspector = ItemFactory.getInspector();

        ItemStack freezeWand = ItemFactory.getFreezeWand();

        Player clicked = (Player) entity;
        if(clicked.hasPermission("dlux.staff")) return;

        if(staffCache.exists(player.getUniqueId())) {
            if(item.hasItemMeta()) {
                if(ValidationUtils.compareItems(item, inspector)) {
                    player.openInventory(clicked.getInventory());
                }
                if(ValidationUtils.compareItems(item, freezeWand)) {
                    staffManager.freeze(clicked, player);
                }
            }
        }
    }
}

package me.ialext.dlux.staff.listener;

import me.ialext.dlux.staff.SimpleCache;
import me.ialext.dlux.staff.staff.ItemFactory;
import me.ialext.dlux.staff.staff.StaffManager;
import me.ialext.dlux.staff.teleport.TeleportManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

public class PlayerInteractListener implements Listener {

    @Inject
    @Named("staff")
    private SimpleCache<UUID> staffCache;

    @Inject
    private TeleportManager teleportManager;

    @Inject
    private StaffManager staffManager;

    @Inject
    @Named("vanish")
    private SimpleCache<UUID> vanishCache;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();

        ItemMeta vanisher = ItemFactory.getVanisher().getItemMeta();

        ItemMeta teleporter = ItemFactory.getTeleporter().getItemMeta();

        ItemMeta randomTeleporter = ItemFactory.getRandomTeleport().getItemMeta();

        if(staffCache.exists(player.getUniqueId())) {
            if(item.hasItemMeta()) {
                if((meta.getDisplayName().equals(vanisher.getDisplayName())) && (meta.getLore().equals(vanisher.getLore()))) {
                    staffManager.hideIfVisible(player);
                } else if((meta.getDisplayName().equals(teleporter.getDisplayName())) && (meta.getLore().equals(teleporter.getLore()))) {
                    Vector vector = player.getEyeLocation().getDirection().normalize().multiply(2);
                    player.setVelocity(vector);
                    
                } else if((meta.getDisplayName().equals(randomTeleporter.getDisplayName())) && (meta.getLore().equals(randomTeleporter.getLore()))) {
                    teleportManager.randomTeleport(player.getUniqueId(), 0, false);
                }
            }
        }
    }
}

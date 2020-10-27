package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.SimpleCache;
import me.ialext.dlux.staff.staff.StaffManager;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

@Command(names = "staff", permission = "dlux.staff")
public class StaffCommand implements CommandClass {

    @Inject
    @Named("staff")
    private SimpleCache<UUID> staffCache;

    @Inject
    private StaffManager staffManager;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        if(!staffCache.exists(sender.getUniqueId())) {
            staffManager.enable(sender);
            staffCache.add(sender.getUniqueId());

            return true;
        }

        staffCache.remove(sender.getUniqueId());
        staffManager.disable(sender);

        return true;
    }
}

package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.CacheMap;
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
    private CacheMap<UUID, ItemStack[]> staffCache;

    @Inject
    private StaffManager staffManager;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        if(!staffCache.exists(sender.getUniqueId())) {
            staffManager.enable(sender);
            sender.sendMessage(ColorUtil.colorize("&aSuccessfully enabled &dStaff mode!"));
        } else {
            staffManager.disable(sender);
            sender.sendMessage(ColorUtil.colorize("&cSuccessfully disabled &dStaff mode!"));

            return true;
        }

        return true;
    }
}

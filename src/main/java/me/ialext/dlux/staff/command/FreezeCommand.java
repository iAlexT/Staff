package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.staff.StaffManager;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

@Command(names = "freeze", permission = "dlux.staff")
public class FreezeCommand implements CommandClass {

    @Inject
    @Named("freeze")
    private Cache<UUID, UUID> freezeCache;

    @Inject
    private StaffManager staffManager;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender, OfflinePlayer target) {
        if(target == null) {
            sender.sendMessage(ColorUtil.colorize("&cUnknown player."));

            return true;
        }

        if(!target.isOnline()) {
            sender.sendMessage(ColorUtil.colorize("&cOffline player."));

            return true;
        }

        staffManager.freeze(target.getPlayer(), sender);
        return true;
    }
}

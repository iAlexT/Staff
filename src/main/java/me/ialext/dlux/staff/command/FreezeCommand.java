package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.staff.FreezeManager;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import team.unnamed.inject.Inject;

@Command(names = "freeze", permission = "dlux.staff")
public class FreezeCommand implements CommandClass {

    @Inject
    private FreezeManager freezeManager;

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

        if(!freezeManager.isFrozen(target.getUniqueId())) {
            freezeManager.freezePlayer(target.getUniqueId(), sender.getUniqueId());

            return true;
        }

        freezeManager.unfreezePlayer(target.getUniqueId());
        return true;
    }
}

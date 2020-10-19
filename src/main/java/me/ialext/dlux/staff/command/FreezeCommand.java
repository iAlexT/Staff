package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.Cache;
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

        if(!freezeCache.exists(target.getUniqueId())) {
            freezeCache.add(target.getUniqueId(), sender.getUniqueId());
            sender.sendMessage(ColorUtil.colorize("&aSuccessfully &bfrozen &e" + target.getName()));
            target.getPlayer().sendMessage(ColorUtil.colorize("&cYou have been &bfrozen &cby &e" + sender.getName()));
        } else {
            freezeCache.remove(target.getUniqueId());
            sender.sendMessage(ColorUtil.colorize("&aSuccessfully &bun-frozen &e" + target.getName()));
            target.getPlayer().sendMessage(ColorUtil.colorize("&aYou are no longer frozen!"));

            return true;
        }

        return true;
    }
}

package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.CacheSet;
import me.ialext.dlux.staff.staff.StaffManager;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

import java.util.UUID;

@Command(names = {"v", "vanish"}, permission = "dlux.staff")
public class VanishCommand implements CommandClass {

    @Inject
    private StaffManager staffManager;

    @Inject
    @Named("vanish")
    private CacheSet<UUID> vanishCache;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        if(!vanishCache.exists(sender.getUniqueId())) {
            staffManager.hide(sender);
            sender.sendMessage(ColorUtil.colorize("&aSuccessfully toggled &evanish"));
        } else {
            staffManager.unhide(sender);
            sender.sendMessage(ColorUtil.colorize("&cSuccessfully untoggled &evanish"));

            return true;
        }
        return true;
    }
}

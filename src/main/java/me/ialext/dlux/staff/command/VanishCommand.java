package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.SimpleCache;
import me.ialext.dlux.staff.staff.StaffManager;
import me.ialext.dlux.staff.util.ColorUtil;
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
    private SimpleCache<UUID> vanishCache;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        if(!vanishCache.exists(sender.getUniqueId())) {
            staffManager.hideIfVisible(sender);
        }
        return true;
    }
}

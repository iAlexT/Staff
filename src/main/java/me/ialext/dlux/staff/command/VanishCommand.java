package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.staff.StaffManager;
import me.ialext.dlux.staff.staff.VanishManager;
import org.bukkit.entity.Player;
import team.unnamed.inject.Inject;

@Command(names = {"v", "vanish"}, permission = "dlux.staff")
public class VanishCommand implements CommandClass {

    @Inject
    private VanishManager vanishManager;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        if(!vanishManager.isHidden(sender.getUniqueId())) {
            vanishManager.hide(sender.getUniqueId());

            return true;
        }

        vanishManager.show(sender.getUniqueId());

        return true;
    }
}

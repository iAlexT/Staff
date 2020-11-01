package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.staff.StaffManager;
import org.bukkit.entity.Player;
import team.unnamed.inject.Inject;

@Command(names = {"v", "vanish"}, permission = "dlux.staff")
public class VanishCommand implements CommandClass {

    @Inject
    private StaffManager staffManager;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        if(!staffManager.isHidden(sender.getUniqueId())) {
            staffManager.hide(sender.getUniqueId());

            return true;
        }

        staffManager.show(sender.getUniqueId());

        return true;
    }
}

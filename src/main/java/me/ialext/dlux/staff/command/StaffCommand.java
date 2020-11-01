package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.staff.StaffManager;
import org.bukkit.entity.Player;
import team.unnamed.inject.Inject;

@Command(names = "staff", permission = "dlux.staff")
public class StaffCommand implements CommandClass {

    @Inject
    private StaffManager staffManager;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        if(!staffManager.isInStaffMode(sender.getUniqueId())) {
            staffManager.enable(sender.getUniqueId());

            return true;
        }

        staffManager.disable(sender.getUniqueId());

        return true;
    }
}

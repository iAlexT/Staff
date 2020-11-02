package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.staff.StaffChatManager;
import org.bukkit.entity.Player;
import team.unnamed.inject.Inject;

@Command(names = {"staffchat", "sc"}, permission = "dlux.staff")
public class StaffChatCommand implements CommandClass {

    @Inject
    private StaffChatManager staffChatManager;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        if(!staffChatManager.isEnabled(sender.getUniqueId())) {
            staffChatManager.enable(sender.getUniqueId());

            return true;
        }

        staffChatManager.disable(sender.getUniqueId());

        return true;
    }
}

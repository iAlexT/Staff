package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.teleport.TeleportManager;
import org.bukkit.entity.Player;
import team.unnamed.inject.Inject;

@Command(names = {"rtp", "randomtp"}, permission = "dlux.staff")
public class RandomTeleportCommand implements CommandClass {

    @Inject
    private TeleportManager teleportManager;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        teleportManager.randomTeleport(sender.getUniqueId());

        return true;
    }
}

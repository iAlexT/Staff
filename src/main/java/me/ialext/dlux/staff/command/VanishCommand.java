package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Command(names = {"v", "vanish"}, permission = "dlux.staff")
public class VanishCommand implements CommandClass {

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.canSee(sender)) {
                p.hidePlayer(sender);
            } else {
                p.showPlayer(sender);

                return true;
            }
        }
        return true;
    }
}

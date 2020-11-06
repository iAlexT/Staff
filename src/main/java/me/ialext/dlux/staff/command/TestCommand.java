package me.ialext.dlux.staff.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.ialext.dlux.staff.files.FileCreator;
import org.bukkit.entity.Player;
import team.unnamed.inject.Inject;
import team.unnamed.inject.name.Named;

@Command(names = "test")
public class TestCommand implements CommandClass {

    @Inject
    @Named("config")
    private FileCreator config;

    @Command(names = "")
    public boolean mainCommand(@Sender Player sender) {
        try {
            int size = config.getStringList("users").size();
            if(config.getStringList("users").isEmpty()) {
                size = 0;
            }

            config.getStringList("users").set(++size, sender.getUniqueId().toString());
            config.save();

            sender.sendMessage("Well");
        } catch (Exception exception) {
            exception.fillInStackTrace();
            sender.sendMessage("Error");

            return true;
        }

        return true;
    }
}

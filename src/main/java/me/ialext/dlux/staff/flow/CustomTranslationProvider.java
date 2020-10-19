package me.ialext.dlux.staff.flow;

import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.translator.TranslationProvider;
import me.ialext.dlux.staff.util.ColorUtil;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CustomTranslationProvider implements TranslationProvider {

    protected Map<String, String> translations = new HashMap<>();

    @Override
    public String getTranslation(Namespace namespace, String key) {
        CommandSender sender = namespace.getObject(CommandSender.class, BukkitCommandManager.SENDER_NAMESPACE);
        switch(key) {
            case "sender.only-player":
                return ColorUtil.colorize("&cThis command is only available for players!");
            case "player.offline":
                return ColorUtil.colorize("&cCannot find player.");
            case "command.no-permission":
                return ColorUtil.colorize("&cYou are not allowed to perform this command.");
        }
        return translations.get(key);
    }
}

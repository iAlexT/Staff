package me.ialext.dlux.staff.loader;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.SimpleCommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitAuthorizer;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.ialext.dlux.staff.command.FreezeCommand;
import me.ialext.dlux.staff.command.RandomTeleportCommand;
import me.ialext.dlux.staff.command.StaffCommand;
import me.ialext.dlux.staff.command.VanishCommand;
import team.unnamed.inject.InjectAll;

@InjectAll
public class CommandLoader implements Loadable {

    private FreezeCommand freezeCommand;
    private RandomTeleportCommand randomTeleportCommand;
    private StaffCommand staffCommand;
    private VanishCommand vanishCommand;

    @Override
    public void load() {
        load(
                freezeCommand,
                randomTeleportCommand,
                staffCommand,
                vanishCommand
        );
    }

    private void load(CommandClass... commandClasses) {
        PartInjector injector = new SimplePartInjector();
        injector.install(new DefaultsModule());
        injector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder commandBuilder = new AnnotatedCommandTreeBuilderImpl(injector);
        CommandManager commandManager = new BukkitCommandManager(new SimpleCommandManager(new BukkitAuthorizer()),
                "staff");

        for(CommandClass commandClass : commandClasses) {
            commandManager.registerCommands(commandBuilder.fromClass(commandClass));
        }
    }
}

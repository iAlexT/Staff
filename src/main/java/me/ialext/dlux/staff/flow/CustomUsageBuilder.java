package me.ialext.dlux.staff.flow;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.command.Command;
import me.fixeddev.commandflow.usage.UsageBuilder;
import net.kyori.text.Component;
import net.kyori.text.TextComponent;

public class CustomUsageBuilder implements UsageBuilder {

    @Override
    public Component getUsage(CommandContext commandContext) {
        Command toExecute = commandContext.getCommand();

        String label = String.join(" ", commandContext.getLabels());

        Component labelComponents = TextComponent.of(label);
        Component partComponents = toExecute.getPart().getLineRepresentation();

        if(partComponents != null) {
            labelComponents = labelComponents.append(TextComponent.of(" ")).append(partComponents);
        }
        return labelComponents;
    }
}

package com.pedestriamc.ghasts.commands.main;

import com.pedestriamc.ghasts.Ghasts;
import com.pedestriamc.ghasts.commands.helpers.NonPrefixMessengerCommand;
import com.pedestriamc.ghasts.messages.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.wiicart.commands.command.CartCommandExecutor;
import net.wiicart.commands.command.tree.CommandTree;
import org.jetbrains.annotations.NotNull;

public class EnchantedHarnessesCommand extends CommandTree {

    static final Component VERSION_MESSAGE = Component
            .text("Running EnchantedHarnesses version ", NamedTextColor.GRAY)
            .append(Component.text(Ghasts.VERSION, NamedTextColor.GREEN));

    public EnchantedHarnessesCommand(@NotNull Ghasts ghasts) {
        super(builder()
                .executes(data -> data.sender().sendMessage(ghasts
                        .getMessenger()
                        .prefix()
                        .append(VERSION_MESSAGE))
                )
                .withChild("enchant", b -> b.executes(new EnchantCommand(ghasts)))
                .withChild("help", b -> b.executes(messenger(ghasts, Message.HELP)))
                .withChild("reload", b -> b.executes(messenger(ghasts, Message.RELOAD_UNSUPPORTED)))
                .build()
        );
    }

    private static CartCommandExecutor messenger(@NotNull Ghasts ghasts, @NotNull Message message) {
        return new NonPrefixMessengerCommand(ghasts.getMessenger(), message);
    }


}

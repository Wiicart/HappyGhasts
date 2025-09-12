package com.pedestriamc.ghasts.commands.helpers;

import com.pedestriamc.ghasts.messages.Message;
import com.pedestriamc.ghasts.messages.Messenger;
import net.wiicart.commands.command.CommandData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class NonPrefixMessengerCommand extends MessengerCommand{

    public NonPrefixMessengerCommand(@NotNull Messenger messenger, @NotNull Message message) {
        super(messenger, message);
    }

    @Override
    public void onCommand(@NotNull CommandData data) {
        messenger().sendMessageNoPrefix(data.sender(), message());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        messenger().sendMessageNoPrefix(sender, message());
        return true;
    }
}

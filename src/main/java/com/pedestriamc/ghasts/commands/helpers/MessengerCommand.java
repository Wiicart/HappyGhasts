package com.pedestriamc.ghasts.commands.helpers;

import com.pedestriamc.ghasts.messages.Message;
import com.pedestriamc.ghasts.messages.Messenger;
import net.wiicart.commands.command.CartCommandExecutor;
import net.wiicart.commands.command.CommandData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MessengerCommand implements CommandExecutor, CartCommandExecutor {

    private final Messenger messenger;

    private final Message message;

    public MessengerCommand(@NotNull Messenger messenger, @NotNull Message message) {
        this.messenger = messenger;
        this.message = message;
    }

    @Override
    public void onCommand(@NotNull CommandData data) {
        messenger.sendMessage(data.sender(), message);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        messenger.sendMessage(sender, message);
        return true;
    }

    protected Messenger messenger() {
        return messenger;
    }

    protected Message message() {
        return message;
    }
}

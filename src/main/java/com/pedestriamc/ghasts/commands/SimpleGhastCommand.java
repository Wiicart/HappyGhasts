package com.pedestriamc.ghasts.commands;

import com.pedestriamc.ghasts.Ghasts;
import com.pedestriamc.ghasts.messages.Message;
import com.pedestriamc.ghasts.messages.Messenger;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@SuppressWarnings("unused")
public class SimpleGhastCommand implements CommandExecutor {

    private static final Map<String, Component> VERSION_PLACEHOLDER = Map.of("{version}", Component.text(Ghasts.VERSION));

    private final Messenger messenger;

    public SimpleGhastCommand(@NotNull Ghasts plugin) {
        messenger = plugin.getMessenger();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        messenger.sendMessage(sender, Message.PLUGIN_VERSION, VERSION_PLACEHOLDER);
        return true;
    }
}

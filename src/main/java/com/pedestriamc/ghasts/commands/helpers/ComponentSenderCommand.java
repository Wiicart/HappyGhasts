package com.pedestriamc.ghasts.commands.helpers;

import net.kyori.adventure.text.Component;
import net.wiicart.commands.command.CartCommandExecutor;
import net.wiicart.commands.command.CommandData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ComponentSenderCommand implements CommandExecutor, CartCommandExecutor {

    private final Component message;

    public ComponentSenderCommand(@NotNull Component message) {
        this.message = message;
    }

    @Override
    public void onCommand(@NotNull CommandData data) {
        data.sender().sendMessage(message);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        sender.sendMessage(message);
        return true;
    }
}

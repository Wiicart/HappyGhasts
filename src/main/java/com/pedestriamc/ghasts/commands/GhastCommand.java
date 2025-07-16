package com.pedestriamc.ghasts.commands;

import com.pedestriamc.ghasts.Ghasts;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class GhastCommand implements CommandExecutor {

    private static final Component PREFIX = Component
            .text("[").color(NamedTextColor.DARK_GRAY)
            .append(Component.text("Ghasts")).color(NamedTextColor.WHITE)
            .append(Component.text("] ").color(NamedTextColor.DARK_GRAY));

    private static final Component VERSION_MESSAGE = PREFIX
            .append(Component.text("Running Ghasts version")).color(NamedTextColor.WHITE)
            .append(Component.text(Ghasts.VERSION)).color(NamedTextColor.GREEN);

    private static final Component RELOAD_NOT_SUPPORTED = PREFIX
            .append(Component.text("Reloading is not supported, please restart instead.")).color(NamedTextColor.WHITE);

    public GhastCommand(@NotNull Ghasts plugin) {

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (args.length) {
            case 1 -> oneArg(sender, args);
            case 2 -> twoArgs(sender, args);
            default -> sendDefaultMessage(sender);
        }

        return true;
    }

    private void sendDefaultMessage(@NotNull CommandSender sender) {
        sender.sendMessage(VERSION_MESSAGE);
    }

    private void oneArg(@NotNull CommandSender sender, @NotNull String[] args) {
        if (args[0].equalsIgnoreCase("reload") && hasReloadPermission(sender)) {
            sender.sendMessage(RELOAD_NOT_SUPPORTED);
        }

        sendDefaultMessage(sender);
    }

    private void twoArgs(@NotNull CommandSender sender, @NotNull String[] args) {

    }

    private boolean hasReloadPermission(@NotNull CommandSender sender) {
        return sender.isOp() ||
                sender.hasPermission("*") ||
                sender.hasPermission("ghasts.*") ||
                sender.hasPermission("ghasts.reload");
    }

}

package com.pedestriamc.ghasts.commands.main;

import com.pedestriamc.ghasts.Ghasts;
import com.pedestriamc.ghasts.tabcompleters.GhastsTabCompleter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GhastsBukkitCommand extends Command {

    private final EnchantedHarnessesCommand command;
    private final GhastsTabCompleter completer;

    public GhastsBukkitCommand(@NotNull Ghasts ghasts) {
        super("ghasts", "The main command for EnchantedHarnesses", "/ghasts <help | enchant>", List.of());
        command = new EnchantedHarnessesCommand(ghasts);
        completer = new GhastsTabCompleter(ghasts.getManager().getEnchantment().getMaxLevel());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String @NotNull [] args) {
        return command.onCommand(sender, this, "ghats", args);
    }

    @Override
    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) {
        List<String> list = completer.onTabComplete(sender, this, alias, args);
        return list != null ? list : List.of();
    }
}

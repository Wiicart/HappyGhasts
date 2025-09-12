package com.pedestriamc.ghasts.tabcompleters;

import net.wiicart.commands.tabcomplete.TabCompleteUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GhastsTabCompleter implements TabCompleter {

    private static final List<String> SUBCOMMANDS = List.of("enchant", "help");

    private final List<String> enchantLevels;

    public GhastsTabCompleter(int maxLevel) {
        enchantLevels = getEnchantLevelList(maxLevel);
    }

    @Override
    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        return switch(args.length) {
            case 0 -> SUBCOMMANDS;
            case 1 -> TabCompleteUtil.filter(SUBCOMMANDS, args[0]);
            case 2 -> {
                if (args[0].equals("enchant") || args[0].equals("enchant ")) {
                    yield enchantLevels;
                }
                yield null;
            }
            default -> null;
        };
    }

    @NotNull
    private List<String> getEnchantLevelList(int maxLevel) {
        List<String> ints = new ArrayList<>();
        for (int i = 1; i <= maxLevel; i++) {
            ints.add(String.valueOf(i));
        }

        return ints;
    }

}

package com.pedestriamc.ghasts.listeners;

import com.pedestriamc.ghasts.Ghasts;
import com.pedestriamc.ghasts.enchantment.EnchantmentBootstrapper;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Set;

// https://minecraft.wiki/w/Data_component_format#enchantable
public class PrepareItemEnchantListener implements Listener {

    private static final Random RANDOM = new Random(44833);

    private static final Set<Material> HARNESSES = Set.of(
            Material.BLACK_HARNESS,
            Material.BLUE_HARNESS,
            Material.BROWN_HARNESS,
            Material.CYAN_HARNESS,
            Material.GRAY_HARNESS,
            Material.GREEN_HARNESS,
            Material.LIGHT_BLUE_HARNESS,
            Material.LIGHT_GRAY_HARNESS,
            Material.LIME_HARNESS,
            Material.MAGENTA_HARNESS,
            Material.ORANGE_HARNESS,
            Material.PINK_HARNESS,
            Material.PURPLE_HARNESS,
            Material.RED_HARNESS,
            Material.WHITE_HARNESS,
            Material.YELLOW_HARNESS
    );

    private final int minimum;

    private final int maximum;

    public PrepareItemEnchantListener(@NotNull Ghasts plugin) {
        FileConfiguration config = plugin.getConfig();

        minimum = config.getInt("enchantment.cost.minimum");
        int tempMax = determineMax(config);

        if (minimum > tempMax) { // Ensure max > min
            maximum = minimum + 1;
        } else {
            maximum = tempMax;
        }
    }

    // max = base-max + (level - 1) * max-mod
    private int determineMax(@NotNull FileConfiguration config) {
        int highestEnchantLevel = EnchantmentBootstrapper.getMaxLevel(config);

        int base = config.getInt("enchantment.cost.maximum", 25);
        int modifier = config.getInt("enchantment.cost.maximum-modifier", 3);

        return base + (highestEnchantLevel - 1) * modifier;
    }

    @EventHandler
    void onEvent(@NotNull PrepareItemEnchantEvent event) {
        ItemStack item = event.getItem();
        if (!HARNESSES.contains(item.getType())) {
            return;
        }

        ItemMeta meta = item.getItemMeta();

        // Enchantable int should only be set once
        if (meta.hasEnchantable()) {
            return;
        }

        meta.setEnchantable(RANDOM.nextInt(minimum, maximum + 1));
        item.setItemMeta(meta);
    }
}

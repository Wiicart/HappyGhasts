package com.pedestriamc.ghasts.enchantment;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Singleton;
import java.io.File;

// https://docs.papermc.io/paper/dev/registries/#mutating-registries
@Singleton
@SuppressWarnings("unused") // Paper calls this class
public class VelocityEnchantment implements PluginBootstrap {

    @Subst("pedestria:velocity")
    private final String keyString;

    private final String description;

    // Default is 3
    private final int maxLevel;

    public VelocityEnchantment() {
        System.out.println("Enchantment Bootstrapper loading...");

        FileConfiguration config = getConfig();
        if (config != null) {
            System.out.println("Ghasts config.yml found.");
            keyString = "pedestria:" + config.getString("enchantment.name", "velocity");
            description = config.getString("enchantment.description", "Velocity");
            maxLevel = getMaxLevel(config);
        } else {
            System.out.println("Failed to read Ghasts config.yml, using default values.");
            keyString = "pedestria:velocity";
            description = "Velocity";
            maxLevel = 3;
        }
    }

    @Nullable
    private FileConfiguration getConfig() {
        File file = new File("Ghasts/config.yml");
        if (!file.exists()) {
            return null;
        }

        try {
            return YamlConfiguration.loadConfiguration(file);
        } catch(Exception ignored) {}

        return null;
    }

    // Attempts to get the max enchantment level from the config, returns 3 if no levels are found.
    private int getMaxLevel(@NotNull FileConfiguration config) {
        ConfigurationSection enchantments = config.getConfigurationSection("enchantments");
        if (enchantments == null) {
            return 3;
        }

        int highest = 1;
        for (String key : enchantments.getKeys(false)) {
            try {
                int level = Integer.parseInt(key);
                if (level > highest) {
                    highest = level;
                }
            } catch(NumberFormatException ignored) {}
        }

        return highest;
    }

    @Override
    public void bootstrap(@NotNull BootstrapContext context) {

        context.getLifecycleManager().registerEventHandler(
                RegistryEvents.ENCHANTMENT.compose().newHandler(
                event -> event.registry().register(
                        EnchantmentKeys.create(Key.key(keyString)),
                        b-> {
                            b.description(Component.text(description));
                            b.supportedItems(event.getOrCreateTag(ItemTypeTagKeys.HARNESSES));
                            b.maxLevel(maxLevel);
                            b.weight(10);
                            b.minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 2));
                            b.maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(2, 1));
                            b.anvilCost(5);
                            b.activeSlots(EquipmentSlotGroup.ANY);
                        }
                ))
        );
        System.out.println("Registered enchantment " + keyString);
    }
}

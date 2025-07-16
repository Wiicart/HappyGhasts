package com.pedestriamc.ghasts;

import com.pedestriamc.ghasts.enchantment.EnchantmentManager;
import com.pedestriamc.ghasts.listeners.EntityDismountListener;
import com.pedestriamc.ghasts.listeners.EntityMountListener;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class FastHappyGhast extends JavaPlugin {

    public static final String VERSION = "1.0";

    private EnchantmentManager manager;

    private FastHappyGhast() {}

    @Override
    public void onLoad() {
        saveDefaultConfig();
        manager = new EnchantmentManager(this);
        getLogger().info("Loading...");
    }

    @Override
    public void onEnable() {
        registerListener(new EntityDismountListener(this));
        registerListener(new EntityMountListener(this));
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    public EnchantmentManager getManager() {
        return manager;
    }

    private void registerListener(@NotNull Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}

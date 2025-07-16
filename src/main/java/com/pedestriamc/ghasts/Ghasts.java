package com.pedestriamc.ghasts;

import com.pedestriamc.ghasts.enchantment.EnchantmentManager;
import com.pedestriamc.ghasts.listeners.EntityDismountListener;
import com.pedestriamc.ghasts.listeners.EntityMountListener;
import com.pedestriamc.ghasts.listeners.PrepareItemEnchantListener;
import com.pedestriamc.ghasts.messages.Messenger;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Ghasts extends JavaPlugin {

    public static final String VERSION = "1.0";

    private EnchantmentManager manager;
    private Messenger messenger;

    private Ghasts() {}

    @Override
    public void onLoad() {
        saveDefaultConfig();
        manager = new EnchantmentManager(this);
        messenger = new Messenger(getConfig());
        getLogger().info("Loading...");
    }

    @Override
    public void onEnable() {
        registerListener(new EntityDismountListener(this));
        registerListener(new EntityMountListener(this));
        registerListener(new PrepareItemEnchantListener(this));
        getLogger().info("Enabled.");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getLogger().info("Disabled.");
    }

    @NotNull
    public EnchantmentManager getManager() {
        return manager;
    }

    @NotNull
    public Messenger getMessenger() {
        return messenger;
    }

    private void registerListener(@NotNull Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}

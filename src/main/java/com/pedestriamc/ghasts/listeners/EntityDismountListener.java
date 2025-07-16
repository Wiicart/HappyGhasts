package com.pedestriamc.ghasts.listener;

import com.pedestriamc.ghasts.FastHappyGhast;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.HappyGhast;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDismountEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDismountListener implements Listener {

    private final double idleSpeed;

    public EntityDismountListener(@NotNull FastHappyGhast plugin) {
        idleSpeed = plugin.getManager().getIdleSpeed();
    }

    @EventHandler
    void onEvent(@NotNull EntityDismountEvent event) {
        if (event.getDismounted() instanceof HappyGhast ghast) {
            AttributeInstance instance = ghast.getAttribute(Attribute.FLYING_SPEED);
            if (instance != null) {
                instance.setBaseValue(idleSpeed);
            }
        }
    }
}

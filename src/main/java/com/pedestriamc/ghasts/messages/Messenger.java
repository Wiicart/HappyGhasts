package com.pedestriamc.ghasts.messages;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Messenger {

    private final EnumMap<Message, String> map = new EnumMap<>(Message.class);

    public Messenger(@NotNull FileConfiguration config) {
        String prefix = config.getString("messages.prefix", "<dark_gray>[<white>Ghasts</white>]</dark_gray> ");
        for (Message message : Message.values()) {
            String string = config.getString(message.getKey());
            if (string != null) {
                map.put(message, prefix + string);
            } else {
                map.put(message, prefix + message.getFallback());
            }
        }
    }

    public void sendMessage(@NotNull Audience audience, @NotNull Message message) {
        sendMessage(audience, message, Collections.emptyMap());
    }

    public void sendMessage(@NotNull Audience audience, @NotNull Message message, @NotNull Map<String, String> placeholders) {
        String raw = map.get(message);
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            raw = raw.replace(entry.getKey(), entry.getValue());
        }

        Component component = MiniMessage.miniMessage().deserialize(raw);
        audience.sendMessage(component);
    }

}

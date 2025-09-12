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

    private final EnumMap<Message, Component> map = new EnumMap<>(Message.class);

    private final Component prefix;

    public Messenger(@NotNull FileConfiguration config) {
        String prefixString = config.getString("messages.prefix", "<dark_gray>[<white>Ghasts</white>]</dark_gray> ");
        prefix = MiniMessage.miniMessage().deserialize(prefixString);
        for (Message message : Message.values()) {
            String raw = config.getString(message.key());
            if (raw != null) {
                map.put(message, deserialize(raw));
            } else {
                map.put(message, deserialize(message.fallback()));
            }
        }
    }

    public void sendMessage(@NotNull Audience audience, @NotNull Message message) {
        sendMessage(audience, message, Collections.emptyMap());
    }

    public void sendMessage(@NotNull Audience audience, @NotNull Message message, @NotNull Map<String, Component> placeholders) {
        sendMessage(audience, prefix.append(map.get(message)), placeholders);
    }

    public void sendMessageNoPrefix(@NotNull Audience audience, @NotNull Message message) {
        sendMessageNoPrefix(audience, message, Collections.emptyMap());
    }

    public void sendMessageNoPrefix(@NotNull Audience audience, @NotNull Message message, @NotNull Map<String, Component> placeholders) {
        sendMessage(audience, map.get(message), placeholders);
    }


    public Component prefix() {
        return prefix;
    }

    private void sendMessage(@NotNull Audience audience, @NotNull Component component, @NotNull Map<String, Component> placeholders) {
        for (Map.Entry<String, Component> entry : placeholders.entrySet()) {
            component = component.replaceText(builder -> builder
                    .matchLiteral(entry.getKey())
                    .replacement(entry.getValue())
            );
        }

        audience.sendMessage(component);
    }

    @NotNull
    private Component deserialize(@NotNull String raw) {
        return MiniMessage.miniMessage().deserialize(raw);
    }


}

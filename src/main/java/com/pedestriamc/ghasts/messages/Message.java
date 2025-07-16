package com.pedestriamc.ghasts.messages;

import org.jetbrains.annotations.NotNull;

public enum Message {
    BOOK_GIVEN("messages.book-given", "<white>Gave <gray>{quantity}</gray> books to <gray>{player}</gray></white>"),
    PLUGIN_VERSION("messages.plugin-version", "<white>Running Ghasts version <green>{version}</green></white>"),
    RELOAD_UNSUPPORTED("messages.reload-unsupported", "<white>Reloading is not supported, please restart instead.</white>"),
    CONSOLE_MUST_DEFINE_PLAYER("messages.console-insufficient", "<white>Console must define a player to use this command.</white>"),
    NO_PERMISSION("messages.no-perms", "<red>No permission!</red>");

    private final String key;
    private final String fallback;

    Message(@NotNull String key, @NotNull String fallback) {
        this.key = key;
        this.fallback = fallback;
    }

    public String getKey() {
        return key;
    }

    public String getFallback() {
        return fallback;
    }
}

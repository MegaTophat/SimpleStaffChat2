/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simplestaffchat.spigot.utilities;

import me.refrac.simplestaffchat.spigot.utilities.chat.Color;
import org.bukkit.Bukkit;

public enum Logger {

    NONE('r'), SUCCESS('a'), ERROR('c'), WARNING('e'), INFO('b');

    char color;

    Logger(char color) { this.color = color; }

    public void out(String message) {
        message = Color.translate(String.format("&%c%s", this.color, message));
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }
}
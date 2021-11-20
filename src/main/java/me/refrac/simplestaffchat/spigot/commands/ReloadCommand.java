/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simplestaffchat.spigot.commands;

import me.refrac.simplestaffchat.spigot.SimpleStaffChat;
import me.refrac.simplestaffchat.spigot.utilities.files.Config;
import me.refrac.simplestaffchat.spigot.utilities.files.Files;
import me.refrac.simplestaffchat.spigot.utilities.chat.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (!sender.hasPermission("simplestaffchat.reload")) {
                sender.sendMessage(Color.translate(Config.NO_PERMISSION));
                return true;
            }

            Files.reloadFiles(SimpleStaffChat.getInstance());
            Config.loadConfig();
            sender.sendMessage(Color.translate(Config.RELOAD));
            return true;
        }
        return false;
    }
}
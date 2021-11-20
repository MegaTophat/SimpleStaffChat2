/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simplestaffchat.bungee.commands;

import me.refrac.simplestaffchat.bungee.utilities.files.Config;
import me.refrac.simplestaffchat.bungee.utilities.files.Files;
import me.refrac.simplestaffchat.bungee.utilities.chat.Color;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {

    public ReloadCommand() {
        super("staffchatreload", "", "screload");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (!sender.hasPermission("simplestaffchat.reload")) {
                Color.sendMessage(sender, Config.NO_PERMISSION, true);
                return;
            }

            Files.loadFiles();
            Config.loadConfig();
            Color.sendMessage(sender, Config.RELOAD, true);
        }
    }
}
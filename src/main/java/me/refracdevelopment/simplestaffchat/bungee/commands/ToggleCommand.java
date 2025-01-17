package me.refracdevelopment.simplestaffchat.bungee.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import me.refracdevelopment.simplestaffchat.bungee.config.Config;
import me.refracdevelopment.simplestaffchat.bungee.utilities.chat.Color;
import me.refracdevelopment.simplestaffchat.shared.Permissions;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CommandAlias("staffchattoggle|sctoggle")
@CommandPermission(Permissions.STAFFCHAT_TOGGLE)
@Description("Allows you to toggle staffchat.")
public class ToggleCommand extends BaseCommand {

    public static List<UUID> insc = new ArrayList<>();

    @Default
    public void execute(CommandSender sender) {
        if (!(sender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (insc.contains(player.getUniqueId())) {
            insc.remove(player.getUniqueId());
            Color.sendMessage(player, Config.MESSAGES_TOGGLE_OFF.toString(), true);
        } else {
            insc.add(player.getUniqueId());
            Color.sendMessage(player, Config.MESSAGES_TOGGLE_ON.toString(), true);
        }
    }
}
package me.refracdevelopment.simplestaffchat.spigot.listeners;

import me.refracdevelopment.simplestaffchat.shared.Permissions;
import me.refracdevelopment.simplestaffchat.shared.Settings;
import me.refracdevelopment.simplestaffchat.spigot.SimpleStaffChat;
import me.refracdevelopment.simplestaffchat.spigot.manager.LocaleManager;
import me.refracdevelopment.simplestaffchat.spigot.utilities.chat.Color;
import me.refracdevelopment.simplestaffchat.spigot.utilities.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    private final SimpleStaffChat plugin;

    public JoinListener(SimpleStaffChat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        final LocaleManager locale = plugin.getManager(LocaleManager.class);

        if (Config.JOIN_ENABLED) {
            if (!player.hasPermission(Permissions.STAFFCHAT_JOIN)) return;

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission(Permissions.STAFFCHAT_SEE)) {
                    locale.sendCustomMessage(p, Color.translate(player, Config.JOIN_FORMAT));
                }
            }
            if (Config.VELOCITY) {
                plugin.getPluginMessage().sendMessage(Color.translate(player, Config.JOIN_FORMAT));
            }
            Color.log2(Color.translate(player, Config.JOIN_FORMAT));
        }

        if (player.getUniqueId().equals(Settings.getDevUUID)) {
            sendDevMessage(player);
        } else if (player.getUniqueId().equals(Settings.getDevUUID2)) {
            sendDevMessage(player);
        }

        if (Config.UPDATE_ON_JOIN) {
            if (!player.hasPermission(Permissions.UPDATE_ON_JOIN)) return;

            plugin.updateCheck(player, true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        final LocaleManager locale = SimpleStaffChat.getInstance().getManager(LocaleManager.class);

        if (!Config.JOIN_ENABLED) return;
        if (!player.hasPermission(Permissions.STAFFCHAT_QUIT)) return;

        for (Player p : plugin.getServer().getOnlinePlayers()) {
            if (p.hasPermission(Permissions.STAFFCHAT_SEE)) {
                locale.sendCustomMessage(p, Color.translate(player, Config.JOIN_QUIT_FORMAT));
            }
        }
        if (Config.VELOCITY) {
            plugin.getPluginMessage().sendMessage(Color.translate(player, Config.JOIN_QUIT_FORMAT));
        }
        Color.log2(Color.translate(player, Config.JOIN_QUIT_FORMAT));
    }

    private void sendDevMessage(Player player) {
        final LocaleManager locale = SimpleStaffChat.getInstance().getManager(LocaleManager.class);

        locale.sendCustomMessage(player, " ");
        locale.sendCustomMessage(player, "&aWelcome " + Settings.getName + " Developer!");
        locale.sendCustomMessage(player, "&aThis server is currently running " + Settings.getName + " &bv" + Settings.getVersion + "&a.");
        locale.sendCustomMessage(player, "&aPlugin name&7: &f" + Settings.getName);
        locale.sendCustomMessage(player, " ");
        locale.sendCustomMessage(player, "&aServer version&7: &f" + plugin.getServer().getVersion());
        locale.sendCustomMessage(player, " ");
    }
}
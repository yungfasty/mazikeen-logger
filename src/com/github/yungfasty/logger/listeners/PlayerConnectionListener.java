package com.github.yungfasty.logger.listeners;

import com.github.yungfasty.logger.LoggerPlugin;
import com.github.yungfasty.logger.utils.WritterUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Date;

public class PlayerConnectionListener implements Listener {

    private static String FORMAT = LoggerPlugin.INSTANCE.getConfig().getString("messages.connection-log");

    @EventHandler
    void onJoin(PlayerJoinEvent event) {

        String name = event.getPlayer().getName();

        WritterUtils.write(name, StringUtils.replaceEach(FORMAT,
                new String[]{"{player}", "{type}"},
                new String[]{name, "entrou"}
        ));

    }

    @EventHandler
    void onQuit(PlayerQuitEvent event) {

        String name = event.getPlayer().getName();

        WritterUtils.write(name, StringUtils.replaceEach(FORMAT,
                new String[]{"{player}", "{type}"},
                new String[]{name, "saiu"}
        ));
    }
}

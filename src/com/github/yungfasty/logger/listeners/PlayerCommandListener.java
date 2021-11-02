package com.github.yungfasty.logger.listeners;

import com.github.yungfasty.logger.LoggerPlugin;
import com.github.yungfasty.logger.utils.WritterUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Date;

public class PlayerCommandListener implements Listener {

    private static String FORMAT = LoggerPlugin.INSTANCE.getConfig().getString("messages.command-log");

    @EventHandler
    void onCommand(PlayerCommandPreprocessEvent event) {

        if (LoggerPlugin.hasToLog(event.getMessage().split(" ")[0].toLowerCase().replace("/", ""))) {

            Location location = event.getPlayer().getLocation();
            String name = event.getPlayer().getName(),
                    worldName = location.getWorld().getName();
            int x = (int) location.getX(),
                    y = (int) location.getY(),
                    z = (int) location.getZ();

            WritterUtils.write(name, StringUtils.replaceEach(FORMAT,
                    new String[]{"{player}", "{command}", "{world}", "{x}", "{y}", "{z}"},
                    new String[]{name, event.getMessage(), worldName, String.valueOf(x), String.valueOf(y), String.valueOf(z)}
            ));

        }
    }
}

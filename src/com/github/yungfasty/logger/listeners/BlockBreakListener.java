package com.github.yungfasty.logger.listeners;

import com.github.yungfasty.logger.LoggerPlugin;
import com.github.yungfasty.logger.utils.WritterUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Date;

public class BlockBreakListener implements Listener {

    private static String FORMAT = LoggerPlugin.INSTANCE.getConfig().getString("messages.break-log");

    @EventHandler
    void onBreak(BlockBreakEvent event) {

        if (LoggerPlugin.hasToLog(event.getBlock().getType())) {

            Location location = event.getBlock().getLocation();

            String name = event.getPlayer().getName(),
                    type = event.getBlock().getType().name(),
                    worldName = location.getWorld().getName();

            int x = (int) location.getX(),
                    y = (int) location.getY(),
                    z = (int) location.getZ();

            WritterUtils.write(name, StringUtils.replaceEach(LoggerPlugin.INSTANCE.getConfig().getString("messages.break-log"),
                    new String[]{"{player}", "{type}", "{world}", "{x}", "{y}", "{z}"},
                    new String[]{name, type, worldName, String.valueOf(x), String.valueOf(y), String.valueOf(z)}
            ));

        }
    }
}

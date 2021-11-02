package com.github.yungfasty.logger.listeners;

import com.github.yungfasty.logger.LoggerPlugin;
import com.github.yungfasty.logger.utils.WritterUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Date;

public class ItemDropListener implements Listener {

    private static String FORMAT = LoggerPlugin.INSTANCE.getConfig().getString("messages.drop-log");

    @EventHandler
    void onDrop(PlayerDropItemEvent event) {

        Location location = event.getItemDrop().getLocation();
        String name = event.getPlayer().getName(),
                type = event.getItemDrop().getItemStack().getType().name(),
                worldName = location.getWorld().getName();
        int amount = event.getItemDrop().getItemStack().getAmount(),
            x = (int) location.getX(),
            y = (int) location.getY(),
            z = (int) location.getZ();

        WritterUtils.write(name, StringUtils.replaceEach(FORMAT,
                new String[]{"{player}", "{amount}", "{type}", "{world}", "{x}", "{y}", "{z}"},
                new String[]{name, String.valueOf(amount), type, worldName, String.valueOf(x), String.valueOf(y), String.valueOf(z)}
        ));
    }
}

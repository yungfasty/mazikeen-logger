package com.github.yungfasty.logger.listeners;

import com.github.yungfasty.logger.LoggerPlugin;
import com.github.yungfasty.logger.utils.WritterUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Date;

public class PlayerInteractListener implements Listener {

    private static String FORMAT = LoggerPlugin.INSTANCE.getConfig().getString("messages.chest-log");

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    void onInteract(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.CHEST) {

            Location location = event.getClickedBlock().getLocation();
            String name = event.getPlayer().getName(),
                    worldName = location.getWorld().getName();
            int x = (int) location.getX(),
                    y = (int) location.getY(),
                    z = (int) location.getZ();

            WritterUtils.write(name, StringUtils.replaceEach(FORMAT,
                    new String[]{"{player}", "{world}", "{x}", "{y}", "{z}"},
                    new String[]{name, worldName, String.valueOf(x), String.valueOf(y), String.valueOf(z)}
            ));

        }
    }
}

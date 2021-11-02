package com.github.yungfasty.logger.managers;

import com.github.yungfasty.logger.LoggerPlugin;
import com.github.yungfasty.logger.listeners.*;
import org.bukkit.Bukkit;

public class ListenerManager {

    public ListenerManager(LoggerPlugin loggerPlugin) {

        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), loggerPlugin);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), loggerPlugin);
        Bukkit.getPluginManager().registerEvents(new ItemDropListener(), loggerPlugin);
        Bukkit.getPluginManager().registerEvents(new PlayerCommandListener(), loggerPlugin);
        Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(), loggerPlugin);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), loggerPlugin);

    }

}

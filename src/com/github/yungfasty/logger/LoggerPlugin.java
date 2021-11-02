package com.github.yungfasty.logger;

import com.github.yungfasty.logger.managers.ListenerManager;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class LoggerPlugin extends JavaPlugin {

    public static SimpleDateFormat DATE_FORMAT;
    public static LoggerPlugin INSTANCE;
    private static Collection<Material> BLOCKS = Lists.newLinkedList();
    private static Collection<String> COMMANDS = Lists.newLinkedList();

    public void onEnable() {

        new File("logs/users").mkdirs();
        INSTANCE = this;
        saveDefaultConfig();

        getConfig().getStringList("settings.blocks-blacklist").forEach(each -> BLOCKS.add(Material.valueOf(each)));
        COMMANDS.addAll(getConfig().getStringList("settings.commands-blacklist"));

        DATE_FORMAT = new SimpleDateFormat(getConfig().getString("messages.time-format"));

        new ListenerManager(this);

    }

    public void onDisable() {

    }

    public static boolean hasToLog(Material material) { return BLOCKS.contains(material); }
    public static boolean hasToLog(String command) { return !COMMANDS.contains(command); }

}

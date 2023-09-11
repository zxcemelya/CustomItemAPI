package me.krutikov.customitems;

import lombok.Getter;
import lombok.Setter;
import me.krutikov.customitems.api.CustomItemListener;
import me.krutikov.customitems.api.service.CustomItemService;
import me.krutikov.customitems.api.service.CustomItemServiceImpl;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public final class CustomItems {

    @Getter
    @Setter
    private static Plugin plugin;

    @Getter
    private static CustomItemService customItemService;

    public CustomItems(Plugin plugin) {
        setPlugin(plugin);
        customItemService = new CustomItemServiceImpl();
        Bukkit.getPluginManager().registerEvents(new CustomItemListener(), plugin);
    }
}

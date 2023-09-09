package me.krutikov.customitems;

import lombok.Getter;
import me.krutikov.customitems.api.CustomItemListener;
import me.krutikov.customitems.api.command.BaseCommand;
import me.krutikov.customitems.api.service.CustomItemService;
import me.krutikov.customitems.api.service.CustomItemServiceImpl;
import me.krutikov.customitems.command.GiveItemCommand;
import me.krutikov.customitems.test.TestItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomItems extends JavaPlugin {

    @Getter
    private static CustomItems instance;

    @Getter
    private static CustomItemService customItemService;


    @Override
    public void onEnable() {
        instance = this;
        init(this);

        customItemService.registerItem(new TestItem());
        BaseCommand.register(this, new GiveItemCommand());
    }

    @Override
    public void onDisable() {

    }
    public void init(Plugin plugin) {
        customItemService = new CustomItemServiceImpl();
        Bukkit.getPluginManager().registerEvents(new CustomItemListener(), plugin);
    }
}

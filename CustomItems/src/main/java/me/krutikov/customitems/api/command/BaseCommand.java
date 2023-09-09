package me.krutikov.customitems.api.command;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public abstract class BaseCommand
        implements CommandExecutor {
    private final String name;
    private final String[] usages;

    @Getter
    private final String description;

    @Getter
    @Setter
    private boolean onlyPlayers;

    public BaseCommand(String name, String description, String... aliases) {
        this.name = name;
        this.description = description;
        this.usages = aliases;
        this.onlyPlayers = false;
    }

    public String[] getAliases() {
        return this.usages;
    }

    public static void register(Plugin plugin, BaseCommand... executors) {
        for (BaseCommand executor : executors) {
            List<String> list = new ArrayList<>();
            Collections.addAll(list, executor.getAliases());
            list.add(executor.getName());
            CMDRegister.reg(plugin, executor.getCommandExecutor(), list, "same api for commands", executor.getName());
        }
    }

    public CommandExecutor getCommandExecutor() {
        return this;
    }

    public abstract void execute(CommandSender user, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            if (onlyPlayers && sender instanceof ConsoleCommandSender) {
                Bukkit.getLogger().info("Эта команда может быть использована только с игры");
                return true;
            }
            this.execute(sender, args);
        }
        catch (ClassCastException e) {
            sender.sendMessage("NonNull");
        }
        return false;
    }

}
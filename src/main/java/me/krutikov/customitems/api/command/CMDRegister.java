package me.krutikov.customitems.api.command;

import lombok.Getter;
import me.krutikov.customitems.CustomItems;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.List;

public class CMDRegister extends Command
        implements PluginIdentifiableCommand {
    @Getter
    private final Plugin plugin;
    private final CommandExecutor owner;
    private static CommandMap commandMap;

    public CMDRegister(List<String> aliases, String desc, String usage, CommandExecutor owner, Plugin plugin) {
        super(aliases.get(0), desc, usage, aliases);
        this.owner = owner;
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        return this.owner.onCommand(sender, this, label, args);
    }

    public static void reg(Plugin plugin, CommandExecutor executor, List<String> aliases, String desc, String usage) {
        try {
            CMDRegister reg = new CMDRegister(aliases, desc, usage, executor, plugin);
            if (commandMap == null) {
                String version = CustomItems.getInstance().getServer().getClass().getPackage().getName().split("\\.")[3];
                Class<?> craftServerClass = Class.forName("org.bukkit.craftbukkit." + version + ".CraftServer");
                Object craftServerObject = craftServerClass.cast(CustomItems.getInstance().getServer());
                Field commandMapField = craftServerClass.getDeclaredField("commandMap");
                commandMapField.setAccessible(true);
                commandMap = (SimpleCommandMap) commandMapField.get(craftServerObject);
            }
            commandMap.register(plugin.getDescription().getName(), reg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

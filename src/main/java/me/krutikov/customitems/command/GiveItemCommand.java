package me.krutikov.customitems.command;

import me.krutikov.customitems.CustomItems;
import me.krutikov.customitems.api.CustomItem;
import me.krutikov.customitems.api.command.BaseCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveItemCommand extends BaseCommand {

    public GiveItemCommand() {
        super("customitem", "выдача кастомных предметов", "ci");
        setOnlyPlayers(true);
    }

    @Override
    public void execute(CommandSender user, String[] args) {
        Player player = (Player) user;

        if (args.length < 2) {
            player.sendMessage("Ошибка, используйте - /customitem give <предмет>");
            return;
        }
        CustomItem customItem = CustomItems.getCustomItemService().findItem(args[1]);
        if (customItem == null) {
            player.sendMessage("Предмет не найден!");
            return;
        }
        player.sendMessage("Предмет выдан!");
        player.getInventory().addItem(customItem.getItemStack());
    }
}

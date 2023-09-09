package me.krutikov.customitems.api;

import lombok.RequiredArgsConstructor;
import me.krutikov.customitems.CustomItems;
import me.krutikov.customitems.api.service.CustomItemService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CustomItemListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL) {
            return;
        }

        Player player = e.getPlayer();
        ItemStack usableItem = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = usableItem.getItemMeta();
        if (itemMeta == null) {
            return;
        }
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        Action clickType = e.getAction();

        for (CustomItem customItem : CustomItems.getCustomItemService().getItems()) {
            if (container.has(CustomItems.getCustomItemService().customNameSpacedKey(), PersistentDataType.STRING)) {
                String itemName = container.get(CustomItems.getCustomItemService().customNameSpacedKey(), PersistentDataType.STRING);
                if (itemName == null) continue;
                String[] item = itemName.split(":");
                if (item.length > 1) itemName = item[0];

                if (itemName.equals(customItem.getName())) {
                    customItem.click(player, clickType, usableItem);
                    e.setCancelled(true);
                }
                break;
            }
        }
    }
}

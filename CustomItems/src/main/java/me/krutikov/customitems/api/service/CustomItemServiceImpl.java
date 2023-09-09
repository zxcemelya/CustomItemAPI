package me.krutikov.customitems.api.service;

import lombok.Getter;
import me.krutikov.customitems.CustomItems;
import me.krutikov.customitems.api.CustomItem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomItemServiceImpl implements CustomItemService{

    @Getter
    private static final List<CustomItem> customItems = new ArrayList<>();

    @Override
    public List<CustomItem> getItems() {
        return customItems;
    }

    @Override
    public NamespacedKey customNameSpacedKey() {
        return new NamespacedKey(CustomItems.getInstance(), "customItem");
    }

    @Override
    public void registerItem(CustomItem customItem) {
        customItems.add(customItem);
        CustomItems.getInstance().getLogger().info("Предмет " + customItem.getName() + " зарегистрирован");
    }

    @Override
    public CustomItem findItem(String item) {
        CustomItem targetItem = null;
        for (CustomItem customItem : customItems) {
            if (customItem.getName().equalsIgnoreCase(item)) {
                targetItem = customItem;
                break;
            }
        }
        return targetItem;
    }
}

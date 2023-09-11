package me.krutikov.customitems.api.service;

import lombok.Getter;
import me.krutikov.customitems.CustomItems;
import me.krutikov.customitems.api.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.List;

public class CustomItemServiceImpl implements CustomItemService{

    @Getter
    private static final List<CustomItem> customItems = new ArrayList<>();

    @Override
    public List<CustomItem> getItems() {
        return customItems;
    }

    @Override
    public NamespacedKey customNameSpacedKey() {
        return new NamespacedKey(CustomItems.getPlugin(), "customItem");
    }

    @Override
    public void registerItem(CustomItem customItem) {
        customItems.add(customItem);
        Bukkit.getLogger().info("Предмет " + customItem.getName() + " зарегистрирован");
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

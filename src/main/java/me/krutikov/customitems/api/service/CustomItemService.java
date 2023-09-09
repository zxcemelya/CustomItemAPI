package me.krutikov.customitems.api.service;

import me.krutikov.customitems.api.CustomItem;
import org.bukkit.NamespacedKey;

import java.util.List;

public interface CustomItemService {

    List<CustomItem> getItems();

    NamespacedKey customNameSpacedKey();

    void registerItem(CustomItem customItem);

    CustomItem findItem(String item);
}

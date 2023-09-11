package me.krutikov.customitems.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.krutikov.customitems.CustomItems;
import me.krutikov.customitems.api.util.PlayerCooldownUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class CustomItem {

    private final String name;

    private final long delay;

    private final boolean stackable;

    @SuppressWarnings(value = "Not for getting an item stack!")
    private ItemStack baseStack;


    public void click(ItemUseSource itemUseData) {
        Player player = itemUseData.getPlayer();

        String cooldownName = "use-item:" + name;
        if (PlayerCooldownUtil.hasCooldown(cooldownName, player)) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            return;
        }
        use(itemUseData);
        PlayerCooldownUtil.putCooldown(cooldownName, player, delay);

    }
    public abstract void use(ItemUseSource data);

    public ItemStack getItemStack() {
        ItemStack itemStack = getBaseStack();
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(CustomItems.getCustomItemService().customNameSpacedKey(), PersistentDataType.STRING, isStackable() ? name : name + ":" + UUID.randomUUID());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}

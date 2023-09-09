package me.krutikov.customitems.test;

import me.krutikov.customitems.CustomItems;
import me.krutikov.customitems.api.CustomItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class TestItem extends CustomItem {
    public TestItem() {
        super("mega-tnt", 1000, false);

        ItemStack itemStack = new ItemStack(Material.TNT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.displayName(Component.text("Мега-ТНТ"));
        itemStack.setItemMeta(itemMeta);

        setBaseStack(itemStack);
    }


    @Override
    protected void use(Player player, Action clickType, ItemStack itemStack) {
        if (clickType != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        final long[] currentMillis = {5000};
        Location targetLocation = player.getLocation();
        TNTPrimed tntPrimed = (TNTPrimed) targetLocation.getWorld().spawnEntity(targetLocation, EntityType.PRIMED_TNT);
        tntPrimed.setFuseTicks(100);
        tntPrimed.setGlowing(true);
        tntPrimed.setCustomNameVisible(true);

        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (currentMillis[0] <= 0) {
                    cancel();
                    tntPrimed.getLocation().createExplosion(5);
                }
                currentMillis[0] = currentMillis[0] - 1000;
                tntPrimed.setCustomName(currentMillis[0] / 1000 + " сек.");
            }
        }.runTaskTimer(CustomItems.getInstance(), 0, 20);
    }

}

package me.krutikov.customitems.api;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemUseSource {

    Player player;

    Action action;

    ItemStack itemStack;

    Block clickedBlock;

    Location interactionPoint;

}

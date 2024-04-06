package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.OuiPvp;
import fr.ziberty.ouipvp.helpers.InventoryHelper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomLoot {

    private final Location chestLoc;
    private final String arenaName;
    private ItemStack randomItem;
    private Inventory configInventory;

    public RandomLoot(Location chestLoc, String arenaName) {
        this.chestLoc = chestLoc;
        this.arenaName = arenaName;
        setConfigInventory();
    }

    public void setConfigInventory() {
        String base64Config = OuiPvp.config.getString("arena." + arenaName + ".random_loot");
        if (base64Config != null) {
            try {
                this.configInventory = InventoryHelper.inventoryFromBase64(base64Config, "Loot Random - " + arenaName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public RandomLoot pickRandomItem() {
        List<ItemStack> items = Arrays
                .stream(configInventory.getContents())
                .filter(Objects::nonNull)
                .filter(item -> !item.getType().equals(Material.BLACK_CONCRETE))
                .filter(item -> !item.getType().equals(Material.BARRIER)).toList();
        Random random = new Random();
        randomItem = items.get(random.nextInt(items.size()));
        return this;
    }

    public void setChestItem() {
        Block chest = chestLoc.getBlock();
        BlockState state = chest.getState();
        if (state instanceof Container container) {
            Inventory inventory = container.getInventory();
            inventory.clear();
            inventory.setItem(13, randomItem);
        }
    }

    public ItemStack getRandomItem() {
        return this.randomItem;
    }

}

package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.OuiPvp;
import fr.ziberty.ouipvp.helpers.InventoryHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class Equipment {

    private Inventory configInventory;
    private final Player player;
    private final String arenaName;

    public Equipment(Player player, String arenaName) {
        this.player = player;
        this.arenaName = arenaName;
        setConfigInventory();
    }

    public void equipPlayer() {
        ItemStack helmet = configInventory.getItem(0);
        ItemStack chestplate = configInventory.getItem(1);
        ItemStack leggings = configInventory.getItem(2);
        ItemStack boots = configInventory.getItem(3);
        ItemStack handItem = configInventory.getItem(4);
        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
        for (int i = 0; i < player.getInventory().getStorageContents().length; i++) {
            if (configInventory.getItem(i + 18) == null) continue;
            player.getInventory().setItem(i, configInventory.getItem(i + 18));
        }
    }

    public void setConfigInventory() {
        String base64Config = OuiPvp.config.getString("arena." + arenaName + ".equipment");
        if (base64Config != null) {
            try {
                this.configInventory = InventoryHelper.inventoryFromBase64(base64Config, "Équipement - " + arenaName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package fr.ziberty.ouipvp.menus;

import fr.ziberty.ouipvp.OuiPvp;
import fr.ziberty.ouipvp.helpers.InventoryHelper;
import fr.ziberty.ouipvp.helpers.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.List;

public class RandomLootMenu {

    private Inventory menu;
    private String arenaName;

    public Inventory getMenu() {
        return this.menu;
    }

    public RandomLootMenu(String arenaName) {
        this.arenaName = arenaName;
        buildMenu(Bukkit.createInventory(null, 54, "Loot Random - " + arenaName));
    }

    private RandomLootMenu buildMenu(Inventory menu) {
        String base64Config = OuiPvp.config.getString("arena." + arenaName + ".random_loot");
        if (base64Config != null) {
            try {
                this.menu = InventoryHelper.inventoryFromBase64(base64Config, "Loot Random - " + arenaName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ItemStack filledSlot = ItemHelper.getItem(Material.BLACK_CONCRETE, 1, " ");
            ItemStack retour = ItemHelper.getItem(Material.BARRIER, 1, "§cRetour");
            menu.setItem(49, retour);

            List<Integer> filledSlots = List.of(45, 46, 47, 48, 50, 51, 52, 53);
            for (int i : filledSlots) {
                menu.setItem(i, filledSlot);
            }
            this.menu = menu;
        }
        return this;
    }

}
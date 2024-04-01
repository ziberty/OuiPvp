package fr.ziberty.ouipvp.menus;

import fr.ziberty.ouipvp.helpers.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
        ItemStack filledSlot = ItemHelper.getItem(Material.BLACK_CONCRETE, 1, " ");
        ItemStack retour = ItemHelper.getItem(Material.BARRIER, 1, "§cRetour");
        menu.setItem(49, retour);

        List<Integer> filledSlots = List.of(45, 46, 47, 48, 50, 51, 52, 53);
        for (int i : filledSlots) {
            menu.setItem(i, filledSlot);
        }
        this.menu = menu;
        return this;
    }

}

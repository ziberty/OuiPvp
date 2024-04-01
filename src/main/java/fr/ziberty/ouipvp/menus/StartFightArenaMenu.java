package fr.ziberty.ouipvp.menus;

import fr.ziberty.ouipvp.helpers.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StartFightArenaMenu {

    public static Inventory menu;

    public StartFightArenaMenu() {
        buildMenu(Bukkit.createInventory(null, 27, "Commencer un combat"));
    }

    private void buildMenu(Inventory menu) {
        ItemStack flat = ItemHelper.getItem(Material.GRASS_BLOCK, 1, "§eFlat");
        ItemStack relief = ItemHelper.getItem(Material.COBBLED_DEEPSLATE_SLAB, 1, "§eRelief");
        ItemStack aqua = ItemHelper.getItem(Material.TRIDENT, 1, "§eAqua");
        ItemStack fly = ItemHelper.getItem(Material.ELYTRA, 1, "§eFly");
        ItemStack retour = ItemHelper.getItem(Material.BARRIER, 1, "§cRetour");
        menu.setItem(10, flat);
        menu.setItem(12, relief);
        menu.setItem(14, aqua);
        menu.setItem(16, fly);
        menu.setItem(22, retour);
        StartFightArenaMenu.menu = menu;
    }

}

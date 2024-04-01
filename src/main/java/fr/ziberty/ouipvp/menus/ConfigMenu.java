package fr.ziberty.ouipvp.menus;

import fr.ziberty.ouipvp.helpers.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ConfigMenu {

    public static Inventory menu;

    public ConfigMenu() {
        buildMenu(Bukkit.createInventory(null, 27, "Configuration"));
    }

    private void buildMenu(Inventory menu) {
        ItemStack arenaConfig = ItemHelper.getItem(Material.IRON_SWORD, 1, "§eConfiguration des arènes");
        ItemStack startFight = ItemHelper.getItem(Material.GREEN_CONCRETE, 1, "§eCommencer un combat");
        menu.setItem(11, arenaConfig);
        menu.setItem(15, startFight);
        ConfigMenu.menu = menu;
    }

}

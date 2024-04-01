package fr.ziberty.ouipvp.menus;

import fr.ziberty.ouipvp.helpers.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StartFightMenu {

    private Inventory menu;

    public Inventory getMenu() {
        return this.menu;
    }

    public StartFightMenu() {
        buildMenu(Bukkit.createInventory(null, 27, "Commencer le combat"));
    }

    private StartFightMenu buildMenu(Inventory menu) {
        ItemStack startFight = ItemHelper.getItem(Material.GREEN_CONCRETE, 1, "§eCommencer le combat");
        ItemStack retour = ItemHelper.getItem(Material.BARRIER, 1, "§cRetour");
        menu.setItem(11, startFight);
        menu.setItem(15, retour);

        this.menu = menu;
        return this;
    }

}

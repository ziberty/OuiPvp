package fr.ziberty.ouipvp.menus;

import fr.ziberty.ouipvp.helpers.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ArenaMenu {

    private Inventory menu;
    private String arenaName;
    private boolean toggled;

    public Inventory getMenu() {
        return this.menu;
    }

    public ArenaMenu(String arenaName, boolean toggled) {
        this.arenaName = arenaName;
        this.toggled = toggled;
        buildMenu(Bukkit.createInventory(null, 27, "Arène " + arenaName));
    }

    private ArenaMenu buildMenu(Inventory menu) {
        ItemStack toggleArena = toggled ? ItemHelper.getItem(Material.CLOCK, 1, "§2Activée") : ItemHelper.getItem(Material.CLOCK, 1, "§4Désactivée");
        ItemStack equipment = ItemHelper.getItem(Material.GOLDEN_APPLE, 1, "§eÉquipement");
        ItemStack randomLoot = ItemHelper.getItem(Material.CHEST, 1, "§eLoot random");
        ItemStack retour = ItemHelper.getItem(Material.BARRIER, 1, "§cRetour");
        menu.setItem(10, toggleArena);
        menu.setItem(13, equipment);
        menu.setItem(16, randomLoot);
        menu.setItem(22, retour);
        this.menu = menu;
        return this;
    }

}

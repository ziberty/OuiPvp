package fr.ziberty.ouipvp.menus;

import fr.ziberty.ouipvp.helpers.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class ChooseFirstPlayerMenu {

    private Inventory menu;

    public Inventory getMenu() {
        return this.menu;
    }

    public ChooseFirstPlayerMenu() {
        buildMenu(Bukkit.createInventory(null, 54, "Choisir le premier joueur"));
    }

    private ChooseFirstPlayerMenu buildMenu(Inventory menu) {
        ItemStack retour = ItemHelper.getItem(Material.BARRIER, 1, "§cRetour");
        menu.setItem(49, retour);

        int i = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (i >= 45) break;
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
            meta.setOwningPlayer(player);
            meta.setDisplayName("§e" + player.getName());
            playerHead.setItemMeta(meta);
            menu.setItem(i, playerHead);
            i++;
        }

        this.menu = menu;
        return this;
    }

}

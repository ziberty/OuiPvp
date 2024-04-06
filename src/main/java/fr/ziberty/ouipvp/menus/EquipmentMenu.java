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

public class EquipmentMenu {

    private Inventory menu;
    private String arenaName;

    public Inventory getMenu() {
        return this.menu;
    }

    public EquipmentMenu(String arenaName) {
        this.arenaName = arenaName;
        buildMenu(Bukkit.createInventory(null, 54, "Équipement - " + arenaName));
    }

    private EquipmentMenu buildMenu(Inventory menu) {
        String base64Config = OuiPvp.config.getString("arena." + arenaName + ".equipment");
        if (base64Config != null) {
            try {
                this.menu = InventoryHelper.inventoryFromBase64(base64Config, "Équipement - " + arenaName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ItemStack helmetIndicator = ItemHelper.getItem(Material.BEDROCK, 1, "↑ Casque");
            ItemStack chestplateIndicator = ItemHelper.getItem(Material.BEDROCK, 1, "↑ Plastron");
            ItemStack leggingsIndicator = ItemHelper.getItem(Material.BEDROCK, 1, "↑ Jambières");
            ItemStack bootsIndicator = ItemHelper.getItem(Material.BEDROCK, 1, "↑ Bottes");
            ItemStack secondHandIndicator = ItemHelper.getItem(Material.BEDROCK, 1, "↑ 2e main");
            ItemStack filledSlot = ItemHelper.getItem(Material.BLACK_CONCRETE, 1, " ");
            ItemStack retour = ItemHelper.getItem(Material.BARRIER, 1, "§cRetour");
            menu.setItem(9, helmetIndicator);
            menu.setItem(10, chestplateIndicator);
            menu.setItem(11, leggingsIndicator);
            menu.setItem(12, bootsIndicator);
            menu.setItem(13, secondHandIndicator);
            menu.setItem(17, retour);

            List<Integer> filledSlots = List.of(5, 6, 7, 8, 14, 15 ,16);
            for (int i : filledSlots) {
                menu.setItem(i, filledSlot);
            }
            this.menu = menu;
        }
        return this;
    }

}

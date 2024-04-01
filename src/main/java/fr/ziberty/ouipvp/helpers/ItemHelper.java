package fr.ziberty.ouipvp.helpers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemHelper {

    public static ItemStack getItem(Material material, int number, String name) {
        return getItem(material, number, name, null);
    }

    public static ItemStack getItem(Material material, int number, String name, List<String> description) {
        ItemStack item = new ItemStack(material, number);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if (description != null && !description.isEmpty()) {
            meta.setLore(description);
        }
        item.setItemMeta(meta);
        return item;
    }

}

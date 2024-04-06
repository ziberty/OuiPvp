package fr.ziberty.ouipvp.listeners;

import fr.ziberty.ouipvp.Fight;
import fr.ziberty.ouipvp.OuiPvp;
import fr.ziberty.ouipvp.arenas.*;
import fr.ziberty.ouipvp.helpers.InventoryHelper;
import fr.ziberty.ouipvp.menus.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class MenusListener implements Listener {

    public static Map<UUID, List<Inventory>> playerPreviousMenu = new HashMap<>();
    private final List<Integer> equipmentSlots = List.of(5,6,7,8,9,10,11,12,13,14,15,16,17);
    private final List<Integer> randomLootSlots = List.of(45, 46, 47, 48, 50, 51, 52, 53);

    @EventHandler
    public void onPlayerCloseMenu(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        String menuTitle = event.getView().getTitle();
        if (menuTitle.startsWith("Équipement - ")) {
            saveEquipmentConfig(menuTitle.split("Équipement - ")[1], player);
        }
        if (menuTitle.startsWith("Loot Random - ")) {
            saveRandomLootConfig(menuTitle.split("Loot Random - ")[1], player);
        }
    }

    @EventHandler
    public void onPlayerMenuInteraction(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        ClickType clickType = event.getClick();
        String menuTitle = event.getView().getTitle();
        Inventory menu = event.getInventory();

        if (itemStack == null) return;
        ItemMeta itemMeta = itemStack.getItemMeta();
        String itemName = null;

        if (itemStack.hasItemMeta() && itemMeta.hasDisplayName()) itemName = itemMeta.getDisplayName();

        if (itemName != null) {
            List<Inventory> menus = playerPreviousMenu.get(player.getUniqueId());

            if (menuTitle.startsWith("Équipement - ")) {
                if (equipmentSlots.contains(event.getSlot())) event.setCancelled(true);
            }
            if (menuTitle.startsWith("Loot Random - ")) {
                if (randomLootSlots.contains(event.getSlot())) event.setCancelled(true);
            }

            switch (menuTitle) {
                case "Configuration" -> {
                    event.setCancelled(true);
                    switch (itemName) {
                        case "§eConfiguration des arènes" -> switchMenu(player, menu, ArenaConfigurationMenu.menu);
                        case "§eCommencer un combat" -> switchMenu(player, menu, StartFightArenaMenu.menu);
                    }
                }
                case "Configuration des arènes" -> {
                    event.setCancelled(true);
                    switch (itemName) {
                        case "§eFlat" -> switchMenu(player, menu, new ArenaMenu("Flat", Flat.toggled).getMenu());
                        case "§eRelief" -> switchMenu(player, menu, new ArenaMenu("Relief", Relief.toggled).getMenu());
                        case "§eAqua" -> switchMenu(player, menu, new ArenaMenu("Aqua", Aqua.toggled).getMenu());
                        case "§eFly" -> switchMenu(player, menu, new ArenaMenu("Fly", Fly.toggled).getMenu());
                    }
                }
                case "Arène Flat" -> {
                    event.setCancelled(true);
                    if (itemStack.getType().equals(Material.CLOCK)) {
                        Flat.toggled = !Flat.toggled;
                        player.openInventory(new ArenaMenu("Flat", Flat.toggled).getMenu());
                    }
                    switch (itemName) {
                        case "§eÉquipement" -> switchMenu(player, menu, new EquipmentMenu("Flat").getMenu());
                        case "§eLoot random" -> switchMenu(player, menu, new RandomLootMenu("Flat").getMenu());
                    }
                }
                case "Arène Relief" -> {
                    event.setCancelled(true);
                    if (itemStack.getType().equals(Material.CLOCK)) {
                        Relief.toggled = !Relief.toggled;
                        player.openInventory(new ArenaMenu("Relief", Relief.toggled).getMenu());
                    }
                    switch (itemName) {
                        case "§eÉquipement" -> switchMenu(player, menu, new EquipmentMenu("Relief").getMenu());
                        case "§eLoot random" -> switchMenu(player,  menu, new RandomLootMenu("Relief").getMenu());
                    }
                }
                case "Arène Aqua" -> {
                    event.setCancelled(true);
                    if (itemStack.getType().equals(Material.CLOCK)) {
                        Aqua.toggled = !Aqua.toggled;
                        player.openInventory(new ArenaMenu("Aqua", Aqua.toggled).getMenu());
                    }
                    switch (itemName) {
                        case "§eÉquipement" -> switchMenu(player, menu, new EquipmentMenu("Aqua").getMenu());
                        case "§eLoot random" -> switchMenu(player, menu, new RandomLootMenu("Aqua").getMenu());
                    }
                }
                case "Arène Fly" -> {
                    event.setCancelled(true);
                    if (itemStack.getType().equals(Material.CLOCK)) {
                        Fly.toggled = !Fly.toggled;
                        player.openInventory(new ArenaMenu("Fly", Fly.toggled).getMenu());
                    }
                    switch (itemName) {
                        case "§eÉquipement" -> switchMenu(player, menu, new EquipmentMenu("Fly").getMenu());
                        case "§eLoot random" -> switchMenu(player, menu, new RandomLootMenu("Fly").getMenu());
                    }
                }
                case "Équipement - Flat" -> {
                    if (itemName.equalsIgnoreCase("§cRetour")) {
                        event.setCancelled(true);
                        saveEquipmentConfig("Flat", player);
                    }
                }
                case "Équipement - Relief" -> {
                    if (itemName.equalsIgnoreCase("§cRetour")) {
                        event.setCancelled(true);
                        saveEquipmentConfig("Relief", player);
                    }
                }
                case "Équipement - Aqua" -> {
                    if (itemName.equalsIgnoreCase("§cRetour")) {
                        event.setCancelled(true);
                        saveEquipmentConfig("Aqua", player);
                    }
                }
                case "Équipement - Fly" -> {
                    if (itemName.equalsIgnoreCase("§cRetour")) {
                        event.setCancelled(true);
                        saveEquipmentConfig("Fly", player);
                    }
                }
                case "Loot Random - Flat" -> {
                    if (itemName.equalsIgnoreCase("§cRetour")) {
                        event.setCancelled(true);
                        saveRandomLootConfig("Flat", player);
                    }
                }
                case "Loot Random - Relief" -> {
                    if (itemName.equalsIgnoreCase("§cRetour")) {
                        event.setCancelled(true);
                        saveRandomLootConfig("Relief", player);
                    }
                }
                case "Loot Random - Aqua" -> {
                    if (itemName.equalsIgnoreCase("§cRetour")) {
                        event.setCancelled(true);
                        saveRandomLootConfig("Aqua", player);
                    }
                }
                case "Loot Random - Fly" -> {
                    if (itemName.equalsIgnoreCase("§cRetour")) {
                        event.setCancelled(true);
                        saveRandomLootConfig("Fly", player);
                    }
                }
                case "Commencer un combat" -> {
                    event.setCancelled(true);
                    Fight.arena = null;
                    switch (itemName) {
                        case "§eFlat" -> Fight.arena = new Flat();
                        case "§eRelief" -> Fight.arena = new Relief();
                        case "§eAqua" -> Fight.arena = new Aqua();
                        case "§eFly" -> Fight.arena = new Fly();
                        case "§eAléatoire" -> {
                            List<Arena> arenas = new ArrayList<>();
                            if (Flat.toggled) arenas.add(new Flat());
                            if (Relief.toggled) arenas.add(new Relief());
                            if (Aqua.toggled) arenas.add(new Aqua());
                            if (Fly.toggled) arenas.add(new Fly());
                            if (arenas.isEmpty()) {
                                player.sendMessage("§cAucune arène active !");
                            } else {
                                Random random = new Random();
                                int index = random.nextInt(arenas.size());
                                Fight.arena = arenas.get(index);
                            }
                        }
                    }
                    if (Fight.arena != null) switchMenu(player, menu, new ChooseFirstPlayerMenu().getMenu());
                }
                case "Choisir le premier joueur" -> {
                    event.setCancelled(true);
                    if (itemMeta instanceof SkullMeta skullMeta) {
                        Fight.player1 = skullMeta.getOwningPlayer().getUniqueId();
                        switchMenu(player, menu, new ChooseSecondPlayerMenu().getMenu());
                    }
                }
                case "Choisir le deuxième joueur" -> {
                    event.setCancelled(true);
                    if (itemMeta instanceof SkullMeta skullMeta) {
                        Fight.player2 = skullMeta.getOwningPlayer().getUniqueId();
                        switchMenu(player, menu, new StartFightMenu().getMenu());
                    }
                }
                case "Commencer le combat" -> {
                    event.setCancelled(true);
                    if (itemName.equalsIgnoreCase("§eCommencer le combat")) {
                        Fight.startFight();
                        player.closeInventory();
                    }
                }
            }
            if (itemName.equalsIgnoreCase("§cRetour")) {
                player.openInventory(menus.get(menus.size() - 1));
                playerPreviousMenu.get(player.getUniqueId()).remove(menus.get(menus.size() - 1));
            }
        }
    }

    private void switchMenu(Player player, Inventory oldMenu, Inventory newMenu) {
        if (!playerPreviousMenu.containsKey(player.getUniqueId())) {
            playerPreviousMenu.put(player.getUniqueId(), new ArrayList<>());
        }
        playerPreviousMenu.get(player.getUniqueId()).add(oldMenu);
        player.openInventory(newMenu);
    }

    private void saveEquipmentConfig(String arena, Player player) {
        Inventory menu = player.getOpenInventory().getTopInventory();
        String base64Menu = InventoryHelper.inventoryToBase64(menu);
        OuiPvp.config.set("arena." + arena + ".equipment", base64Menu);
        OuiPvp.instance.saveConfig();
    }

    private void saveRandomLootConfig(String arena, Player player) {
        Inventory menu = player.getOpenInventory().getTopInventory();
        String base64Menu = InventoryHelper.inventoryToBase64(menu);
        OuiPvp.config.set("arena." + arena + ".random_loot", base64Menu);
        OuiPvp.instance.saveConfig();
    }

}

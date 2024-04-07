package fr.ziberty.ouipvp;

import fr.ziberty.ouipvp.arenas.Arena;
import fr.ziberty.ouipvp.arenas.Equipment;
import fr.ziberty.ouipvp.arenas.RandomLoot;
import fr.ziberty.ouipvp.tasks.PreFightTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Fight {

    public static UUID player1;
    public static UUID player2;
    public static Arena arena;
    public static boolean inFight;
    public static Map<UUID, Fight> playerFight = new HashMap<>();

    private static final List<Class> entitiesToKill = List.of(Horse.class, Wolf.class, Pig.class, IronGolem.class, Dolphin.class, PufferFish.class, Chicken.class);

    public static void startFight() {
        if (!inFight) {
            inFight = true;
            RandomLoot randomLoot = new RandomLoot(arena.getChestLoc(), arena.getName());
            randomLoot.pickRandomItem().setChestItem();
            new PreFightTask().runTaskTimer(OuiPvp.instance, 0, 20);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getUniqueId().equals(player1)) {
                    player.teleport(arena.getSpawnP1());
                    equipPlayer(player);
                } else if (player.getUniqueId().equals(player2)) {
                    player.teleport(arena.getSpawnP2());
                    equipPlayer(player);
                } else {
                    player.teleport(arena.getSpawnSpec());
                    player.setGameMode(GameMode.SPECTATOR);
                }
            }
            Bukkit.broadcastMessage("§6Début du combat : §c" + Bukkit.getPlayer(player1).getName() + " §6vs §c" + Bukkit.getPlayer(player2).getName());
            Bukkit.broadcastMessage("§6Arène choisie : §c" + arena.getName());
            Bukkit.broadcastMessage("§6Item aléatoire choisi : §c" + randomLoot.getRandomItem().getType().name() + "x" + randomLoot.getRandomItem().getAmount());
            arena.spawnEvents();
        }
    }

    public static void endFight(Player looser) {
        inFight = false;
        playerFight.clear();
        Player winner = looser.getUniqueId().equals(player1) ? Bukkit.getPlayer(player2) : Bukkit.getPlayer(player1);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle("§a" + winner.getName(), "§aVainqueur du combat", 10, 100, 10);
            player.playSound(player.getLocation(), Sound.ENTITY_ALLAY_DEATH, 1, .1f);
        }
        Bukkit.getScheduler().runTaskLater(OuiPvp.instance, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getUniqueId().equals(looser.getUniqueId())) continue;
                player.setGameMode(GameMode.ADVENTURE);
                player.getInventory().clear();
                player.teleport(OuiPvp.spawn);
                player.setHealth(20);
                player.setFoodLevel(20);
                player.setLevel(0);
                player.setExp(0);
                player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
            }
            arena.reset();
            player1 = null;
            player2 = null;
            arena = null;
        }, 100);
        for (Entity entity : OuiPvp.world.getEntities()) {
            if (entity instanceof Damageable mob) {
                if (entitiesToKill.stream().anyMatch(c -> c.isInstance(mob))) {
                    mob.setHealth(0);
                }
            }
        }
    }

    public static void spawnEvent(String event) {
        Player p1 = Bukkit.getPlayer(player1);
        Player p2 = Bukkit.getPlayer(player2);
        switch (event) {
            case "tnt" -> {
                p1.getInventory().addItem(new ItemStack(Material.TNT, 5));
                p1.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL, 1));
                p2.getInventory().addItem(new ItemStack(Material.TNT, 5));
                p2.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL, 1));
                Bukkit.broadcastMessage("§dRound spécial : TNT");
            }
            case "wolf" -> {
                p1.getInventory().addItem(new ItemStack(Material.WOLF_SPAWN_EGG, 3));
                p2.getInventory().addItem(new ItemStack(Material.WOLF_SPAWN_EGG, 3));
                Bukkit.broadcastMessage("§dRound spécial : Loups");
            }
            case "horse" -> {
                p1.getInventory().addItem(new ItemStack(Material.HORSE_SPAWN_EGG, 1));
                p2.getInventory().addItem(new ItemStack(Material.HORSE_SPAWN_EGG, 1));
                Bukkit.broadcastMessage("§dRound spécial : Chevaux");
            }
        }
    }

    public static boolean playerInFight(Player player) {
        return (player1 != null && player2 != null) && (player1.equals(player.getUniqueId()) || player2.equals(player.getUniqueId()));
    }

    private static void equipPlayer(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.setFoodLevel(20);
        player.setHealth(20);
        new Equipment(player, arena.getName()).equipPlayer();
    }



}

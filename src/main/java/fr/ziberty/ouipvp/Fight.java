package fr.ziberty.ouipvp;

import fr.ziberty.ouipvp.arenas.Arena;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Fight {

    public static UUID player1;
    public static UUID player2;
    public static Arena arena;
    private static boolean inFight;
    public static Map<UUID, Fight> playerFight = new HashMap<>();

    public static void startFight() {
        if (!inFight) {
            inFight = true;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getUniqueId().equals(player1)) {
                    player.teleport(arena.getSpawnP1());
                    player.setGameMode(GameMode.SURVIVAL);
                    equipPlayer(player);
                } else if (player.getUniqueId().equals(player2)) {
                    player.teleport(arena.getSpawnP2());
                    player.setGameMode(GameMode.SURVIVAL);
                    equipPlayer(player);
                } else {
                    player.teleport(arena.getSpawnSpec());
                    player.setGameMode(GameMode.SPECTATOR);
                }
            }
        }
    }

    public void endFight() {
        inFight = false;
        playerFight.remove(player1);
        playerFight.remove(player2);
    }

    public static boolean playerInFight(Player player) {
        return (player1 != null && player2 != null) && (player1.equals(player.getUniqueId()) || player2.equals(player.getUniqueId()));
    }

    private static void equipPlayer(Player player) {

    }

}

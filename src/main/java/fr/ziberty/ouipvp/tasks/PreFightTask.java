package fr.ziberty.ouipvp.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PreFightTask extends BukkitRunnable {

    private int second = 0;
    public static boolean canMove = true;

    public PreFightTask() {
        canMove = false;
    }

    @Override
    public void run() {
        if (second < 3) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle("§c" + (3 - second), "");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            }
            second++;
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle("§cGO", "");
                player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_EMERGE, 1, 2);
            }
            canMove = true;
            cancel();
        }
    }
}

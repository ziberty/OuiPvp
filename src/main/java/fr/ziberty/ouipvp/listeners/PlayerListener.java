package fr.ziberty.ouipvp.listeners;

import fr.ziberty.ouipvp.Fight;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerListener implements Listener {

    private final List<Material> allowedBlocks = List.of(Material.OAK_DOOR, Material.TNT, Material.LIGHT_BLUE_TERRACOTTA);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!Fight.playerInFight(player)) {
            Location spawn = new Location(player.getWorld(), 0, 100, 0);
            player.teleport(spawn);
            player.setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            if (!Fight.playerInFight(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerFoodLevelChange(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();
        if (!Fight.playerInFight(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        Material material = event.getBlock().getType();
        if (!allowedBlocks.contains(material)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Material material = event.getBlock().getType();
        if (!allowedBlocks.contains(material)) {
            event.setCancelled(true);
        }
    }

}

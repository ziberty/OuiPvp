package fr.ziberty.ouipvp.listeners;

import fr.ziberty.ouipvp.Fight;
import fr.ziberty.ouipvp.OuiPvp;
import fr.ziberty.ouipvp.tasks.PreFightTask;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;

import java.util.List;

public class PlayerListener implements Listener {

    private final List<Material> allowedBlocks = List.of(Material.OAK_DOOR, Material.TNT, Material.LIGHT_BLUE_TERRACOTTA, Material.CAKE);

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
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        Material material = event.getBlock().getType();
        if (!allowedBlocks.contains(material)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        Material material = event.getBlock().getType();
        if (!allowedBlocks.contains(material)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        event.setKeepInventory(true);
        if (Fight.playerInFight(player)) {
            Fight.endFight(player);
            player.getInventory().clear();
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (Fight.playerInFight(player) && !PreFightTask.canMove) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerEnterBed(PlayerSpawnChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack itemStack = event.getItem();
        if (itemStack == null) return;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) && itemStack.getType().equals(Material.WOLF_SPAWN_EGG)) {
            event.setCancelled(true);
            Location loc = new Location(player.getWorld(), event.getClickedBlock().getX(), event.getClickedBlock().getY() + 1, event.getClickedBlock().getZ());
            Wolf wolf = (Wolf) player.getWorld().spawnEntity(loc, EntityType.WOLF);
            wolf.setOwner(player);
            event.getItem().setAmount(itemStack.getAmount() - 1);
        }
    }

}

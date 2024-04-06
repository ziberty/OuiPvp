package fr.ziberty.ouipvp.listeners;

import fr.ziberty.ouipvp.Fight;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        if (!Fight.inFight) {
            event.getDrops().clear();
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Horse horse) {
            horse.setTamed(true);
            horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
            horse.getInventory().setArmor(new ItemStack(Material.IRON_HORSE_ARMOR));
        }
    }

}

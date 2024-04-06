package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.Fight;
import fr.ziberty.ouipvp.OuiPvp;
import fr.ziberty.ouipvp.helpers.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class Relief extends Arena {
    public static boolean toggled = true;
    private final List<String> randomEvents = List.of("n", "n", "n", "n", "n", "tnt", "wolf");
    public Relief() {
        super(
                "Relief",
                new Location(OuiPvp.world, 99.5, 101, -20.5, 0, 0),
                new Location(OuiPvp.world, 98.5, 101, 21.5, -180, 0),
                new Location(OuiPvp.world, 99, 105, 0),
                new Location(OuiPvp.world, 101, 131, -3),
                Material.COBBLED_DEEPSLATE_STAIRS,
                new Location(OuiPvp.world, 126, 121, 33)
        );
    }

    @Override
    public boolean isToggled() {
        return toggled;
    }

    @Override
    public void spawnEvents() {
        OuiPvp.world.spawnEntity(new Location(OuiPvp.world, 87, 98, -13), EntityType.IRON_GOLEM);
        Random random = new Random();
        int index = random.nextInt(randomEvents.size());
        Fight.spawnEvent(randomEvents.get(index));
    }
}

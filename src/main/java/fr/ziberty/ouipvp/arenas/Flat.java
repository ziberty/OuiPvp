package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.Fight;
import fr.ziberty.ouipvp.OuiPvp;
import fr.ziberty.ouipvp.helpers.SchematicHelper;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;
import java.util.Random;

public class Flat extends Arena {
    public static boolean toggled = true;

    private final List<String> randomEvents = List.of("n", "n", "n", "n", "n", "tnt", "wolf", "horse");

    public Flat() {
        super(
                "Flat",
                new Location(OuiPvp.world, -19.5, 100.5, -95.5, -90, 0),
                new Location(OuiPvp.world, 20.5, 100.5, -95.5, 90, 0),
                new Location(OuiPvp.world, 0, 99, -117),
                new Location(OuiPvp.world, 0, 125, -100),
                Material.GRASS_BLOCK,
                new Location(OuiPvp.world, 31, 107, -128)
        );
    }

    @Override
    public boolean isToggled() {
        return toggled;
    }

    @Override
    public void spawnEvents() {
        Random random = new Random();
        int index = random.nextInt(randomEvents.size());
        Fight.spawnEvent(randomEvents.get(index));
    }
}

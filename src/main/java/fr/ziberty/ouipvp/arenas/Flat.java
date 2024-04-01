package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.OuiPvp;
import org.bukkit.Location;
import org.bukkit.Material;

public class Flat extends Arena {
    public static boolean toggled = true;

    public Flat() {
        super(
                "Flat",
                new Location(OuiPvp.world, 20, 100, -96, 90, 0),
                new Location(OuiPvp.world, -20, 100, -97, -90, 0),
                new Location(OuiPvp.world, 0, 99, -117),
                new Location(OuiPvp.world, 0, 125, -100),
                Material.GRASS_BLOCK
        );
    }

    @Override
    public boolean isToggled() {
        return toggled;
    }
}

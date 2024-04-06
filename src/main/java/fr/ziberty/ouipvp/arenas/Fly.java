package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.OuiPvp;
import org.bukkit.Location;
import org.bukkit.Material;

public class Fly extends Arena {
    public static boolean toggled;
    public Fly() {
        super(
                "Fly",
                new Location(OuiPvp.world, -84.5, 100, 22.5, 180, 0),
                new Location(OuiPvp.world, -84.5, 100, -21.5, 0, 0),
                new Location(OuiPvp.world, -94, 106, 0),
                new Location(OuiPvp.world, -59, 112, -14),
                Material.ELYTRA,
                new Location(OuiPvp.world, -117, 121, -35)
        );
    }

    @Override
    public boolean isToggled() {
        return toggled;
    }

    @Override
    public void spawnEvents() {

    }
}

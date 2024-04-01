package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.OuiPvp;
import org.bukkit.Location;
import org.bukkit.Material;

public class Fly extends Arena {
    public static boolean toggled;
    public Fly() {
        super(
                "Fly",
                new Location(OuiPvp.world, -85, 100, 22, 180, 0),
                new Location(OuiPvp.world, -85, 100, -22, 0, 0),
                new Location(OuiPvp.world, -94, 106, 0),
                new Location(OuiPvp.world, -59, 112, -14),
                Material.ELYTRA
        );
    }

    @Override
    public boolean isToggled() {
        return toggled;
    }
}

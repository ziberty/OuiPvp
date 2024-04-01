package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.OuiPvp;
import org.bukkit.Location;
import org.bukkit.Material;

public class Aqua extends Arena {
    public static boolean toggled = true;
    public Aqua() {
        super(
                "Aqua",
                new Location(OuiPvp.world, 20, 102, 95, 90, 0),
                new Location(OuiPvp.world, -20, 102, 95, -90, 0),
                new Location(OuiPvp.world, 0, 81, 116),
                new Location(OuiPvp.world, -3, 127, 95),
                Material.TRIDENT
        );
    }

    @Override
    public boolean isToggled() {
        return toggled;
    }
}

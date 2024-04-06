package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.OuiPvp;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class Aqua extends Arena {
    public static boolean toggled = true;
    public Aqua() {
        super(
                "Aqua",
                new Location(OuiPvp.world, 20.5, 102, 95.5, 90, 0),
                new Location(OuiPvp.world, -19.5, 102, 95.5, -90, 0),
                new Location(OuiPvp.world, 0, 81, 116),
                new Location(OuiPvp.world, -3, 127, 95),
                Material.TRIDENT,
                new Location(OuiPvp.world, -28, 144, 130)
        );
    }

    @Override
    public boolean isToggled() {
        return toggled;
    }

    @Override
    public void spawnEvents() {
        for (int i = 0; i <= 5; i++) {
            OuiPvp.world.spawnEntity(new Location(OuiPvp.world, 2, 88, 93), EntityType.DOLPHIN);
        }
    }
}

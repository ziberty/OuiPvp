package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.OuiPvp;
import org.bukkit.Location;
import org.bukkit.Material;

public class Relief extends Arena {
    public static boolean toggled = true;
    public Relief() {
        super(
                "Relief",
                new Location(OuiPvp.world, 99, 101, -21, 0, 0),
                new Location(OuiPvp.world, 98, 101, 21, -180, 0),
                new Location(OuiPvp.world, 99, 105, 0),
                new Location(OuiPvp.world, 101, 131, -3),
                Material.COBBLED_DEEPSLATE_STAIRS
        );
    }

    @Override
    public boolean isToggled() {
        return toggled;
    }
}

package fr.ziberty.ouipvp.arenas;

import fr.ziberty.ouipvp.helpers.SchematicHelper;
import org.bukkit.Location;
import org.bukkit.Material;

import java.io.File;

public abstract class Arena {

    private String name;
    private Location spawnP1;
    private Location spawnP2;
    private Location chestLoc;
    private Location spawnSpec;
    private Material arenaIcon;
    private Location resetLoc;
    private final File schematicFile;

    public Arena(String name, Location spawnP1, Location spawnP2, Location chestLoc, Location spawnSpec, Material arenaIcon, Location resetLoc) {
        this.name = name;
        this.spawnP1 = spawnP1;
        this.spawnP2 = spawnP2;
        this.chestLoc = chestLoc;
        this.spawnSpec = spawnSpec;
        this.arenaIcon = arenaIcon;
        this.resetLoc = resetLoc;
        this.schematicFile = new File("plugins/FastAsyncWorldEdit/schematics/" + name + ".schem");
    }

    public abstract boolean isToggled();

    public abstract void spawnEvents();

    public void reset() {
        SchematicHelper.paste(resetLoc, schematicFile);
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getSpawnP1() {
        return spawnP1;
    }

    public void setSpawnP1(Location spawnP1) {
        this.spawnP1 = spawnP1;
    }

    public Location getSpawnP2() {
        return spawnP2;
    }

    public void setSpawnP2(Location spawnP2) {
        this.spawnP2 = spawnP2;
    }

    public Location getChestLoc() {
        return chestLoc;
    }

    public void setChestLoc(Location chestLoc) {
        this.chestLoc = chestLoc;
    }

    public Location getSpawnSpec() {
        return spawnSpec;
    }

    public void setSpawnSpec(Location spawnSpec) {
        this.spawnSpec = spawnSpec;
    }

    public Material getArenaIcon() {
        return arenaIcon;
    }

    public void setArenaIcon(Material arenaIcon) {
        this.arenaIcon = arenaIcon;
    }

    public Location getResetLoc() {
        return resetLoc;
    }

    public void setResetLoc(Location resetLoc) {
        this.resetLoc = resetLoc;
    }
}

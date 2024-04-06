package fr.ziberty.ouipvp;

import fr.ziberty.ouipvp.commands.ConfigCommand;
import fr.ziberty.ouipvp.listeners.EntityListener;
import fr.ziberty.ouipvp.listeners.GeneralListener;
import fr.ziberty.ouipvp.listeners.PlayerListener;
import fr.ziberty.ouipvp.menus.ArenaConfigurationMenu;
import fr.ziberty.ouipvp.menus.ConfigMenu;
import fr.ziberty.ouipvp.listeners.MenusListener;
import fr.ziberty.ouipvp.menus.StartFightArenaMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public final class OuiPvp extends JavaPlugin {

    public static OuiPvp instance;
    public static FileConfiguration config;
    public static World world = Bukkit.getWorld("world");
    public static Location spawn = new Location(world, 0, 100, 0);
    @Override
    public void onEnable() {
        instance = this;
        config = getConfig();
        registerListeners();
        registerCommands();
        createMenus();
        Bukkit.broadcastMessage("Plugin Oui PvP correctement initialisé");
    }

    @Override
    public void onDisable() {
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new GeneralListener(), this);
        getServer().getPluginManager().registerEvents(new MenusListener(), this);
        getServer().getPluginManager().registerEvents(new EntityListener(), this);
    }

    private void registerCommands() {
        getCommand("config").setExecutor(new ConfigCommand());
    }

    private void createMenus() {
        new ConfigMenu();
        new ArenaConfigurationMenu();
        new StartFightArenaMenu();
    }
}

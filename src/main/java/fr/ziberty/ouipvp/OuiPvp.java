package fr.ziberty.ouipvp;

import fr.ziberty.ouipvp.arenas.Arena;
import fr.ziberty.ouipvp.commands.ConfigCommand;
import fr.ziberty.ouipvp.listeners.GeneralListener;
import fr.ziberty.ouipvp.listeners.PlayerListener;
import fr.ziberty.ouipvp.menus.ArenaConfigurationMenu;
import fr.ziberty.ouipvp.menus.ConfigMenu;
import fr.ziberty.ouipvp.menus.MenusListener;
import fr.ziberty.ouipvp.menus.StartFightArenaMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class OuiPvp extends JavaPlugin {

    private static OuiPvp instance;
    public static World world = Bukkit.getWorld("world");

    @Override
    public void onEnable() {
        instance = this;
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

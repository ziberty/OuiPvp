package fr.ziberty.ouipvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class GeneralListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

}

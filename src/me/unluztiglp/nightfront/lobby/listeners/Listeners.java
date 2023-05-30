package me.unluztiglp.nightfront.lobby.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Listeners implements Listener {
    public void foodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
  event.setCancelled(true);
    
    }
 }
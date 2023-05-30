package me.unluztiglp.nightfront.lobby.listeners;

import me.unluztiglp.nightfront.lobby.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class onItemMove implements Listener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (!Main.build.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        if (!Main.build.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
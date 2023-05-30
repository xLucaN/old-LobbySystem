package me.unluztiglp.nightfront.lobby.listeners;

import me.unluztiglp.nightfront.lobby.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class onBuild implements Listener {
    @EventHandler
    public void onBuild(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!Main.build.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!Main.build.contains(player)) {
            event.setCancelled(true);
        }
    }
}

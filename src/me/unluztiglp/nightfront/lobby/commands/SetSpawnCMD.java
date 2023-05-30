package me.unluztiglp.nightfront.lobby.commands;

import me.unluztiglp.nightfront.lobby.main.Main;
import me.unluztiglp.nightfront.lobby.main.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetSpawnCMD implements CommandExecutor {
    Main main;
    public SetSpawnCMD(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!player.hasPermission("nightfront.setspawn")) {
                player.sendMessage(Variables.noprems);
                return true;
            }
            main.setSpawn(player.getLocation());
            /*FileConfiguration cfg = main.getConfig();
            cfg.set("Config.spawnLocWorld", player.getLocation().getWorld());
            cfg.set("Config.spawnLocX", player.getLocation().getX());
            cfg.set("Config.spawnLocY", player.getLocation().getY());
            cfg.set("Config.spawnLocZ", player.getLocation().getZ());
            cfg.set("Config.spawnLocPitch", player.getLocation().getPitch());
            cfg.set("Config.spawnLocYaw", player.getLocation().getYaw());*/
            player.sendMessage(Variables.prefix + "Du hast erfolgreich den Spawn gesetzt.");
        }
        return true;
    }
}

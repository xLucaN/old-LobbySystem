package me.unluztiglp.nightfront.lobby.commands;

import me.unluztiglp.nightfront.lobby.main.Main;
import me.unluztiglp.nightfront.lobby.main.Variables;
import me.unluztiglp.nightfront.lobby.utils.Spawn;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SpawnCMD implements CommandExecutor {
    Main main;

    public SpawnCMD(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            player.teleport(Spawn.spawn);
            player.sendMessage(Variables.prefix + "Du wurdest zum Spawn teleportiert.");
        }
        return false;
    }
}

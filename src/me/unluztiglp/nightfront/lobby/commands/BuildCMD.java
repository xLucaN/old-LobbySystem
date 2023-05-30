package me.unluztiglp.nightfront.lobby.commands;

import me.unluztiglp.nightfront.lobby.main.Main;
import me.unluztiglp.nightfront.lobby.main.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("nightfront.build")) {
                if (Main.build.contains(player)) {
                    Main.build.remove(player);
                    player.sendMessage(Variables.prefix + "Du kannst nun nicht mehr bauen.");
                } else {
                    Main.build.add(player);
                    player.sendMessage(Variables.prefix + "Du kannst nun bauen.");
                }
            }
        }
        return true;
    }
}

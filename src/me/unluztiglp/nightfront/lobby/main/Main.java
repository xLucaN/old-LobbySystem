package me.unluztiglp.nightfront.lobby.main;

import me.unluztiglp.nightfront.lobby.commands.BuildCMD;
import me.unluztiglp.nightfront.lobby.commands.KopfCMD;
import me.unluztiglp.nightfront.lobby.commands.SetSpawnCMD;
import me.unluztiglp.nightfront.lobby.commands.SpawnCMD;
import me.unluztiglp.nightfront.lobby.listeners.Listeners;
import me.unluztiglp.nightfront.lobby.listeners.onBuild;
import me.unluztiglp.nightfront.lobby.listeners.onItemMove;
import me.unluztiglp.nightfront.lobby.utils.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {
    public static File file;
    public static FileConfiguration cfg;
    public static ArrayList<Player> build = new ArrayList<>();

    @Override
    public void onEnable() {
        //saveDefaultConfig();
        loadConfig();

        Main.file = new File("plugins/Lobby", "config.yml");
        Main.cfg = YamlConfiguration.loadConfiguration(Main.file);

        getSpawnLoc();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(this, this);
        pluginManager.registerEvents(new onBuild(), this);
        pluginManager.registerEvents(new onItemMove(), this);
        pluginManager.registerEvents(new Listeners(), this);

        getCommand("spawn").setExecutor(new SpawnCMD(this));
        getCommand("setspawn").setExecutor(new SetSpawnCMD(this));

        getCommand("build").setExecutor(new BuildCMD());
        
        getCommand("kopf").setExecutor(new KopfCMD());

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(Spawn.spawn);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        ItemStack hardcorePvP = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta hardcorePvPMeta = hardcorePvP.getItemMeta();
        hardcorePvPMeta.setDisplayName("§8Hard§5Core§8PvP");
        hardcorePvP.setItemMeta(hardcorePvPMeta);

        ItemStack kitPvP = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta KitPvPMeta = kitPvP.getItemMeta();
        KitPvPMeta.setDisplayName("§8Kit§5PvP");
        kitPvP.setItemMeta(KitPvPMeta);

        player.getInventory().setItem(2, hardcorePvP);
        player.getInventory().setItem(6, kitPvP);
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();


        ItemStack hardcorePvP = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta hardcorePvPMeta = hardcorePvP.getItemMeta();
        hardcorePvPMeta.setDisplayName("§8Hard§5Core§8PvP");
        hardcorePvP.setItemMeta(hardcorePvPMeta);

        ItemStack kitPvP = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta KitPvPMeta = kitPvP.getItemMeta();
        KitPvPMeta.setDisplayName("§8Kit§5PvP");
        kitPvP.setItemMeta(KitPvPMeta);


        if (!player.getItemInHand().getItemMeta().getDisplayName().startsWith("§8")) {
            //event.setCancelled(true);
        } else {

            if (player.getItemInHand().equals(hardcorePvP)) {
                player.sendMessage("HardCorePvP");


                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                try {
                    out.writeUTF("Connect");
                    out.writeUTF("hardcorepvp");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(Variables.prefix + "Verbinde zu HardCorePvP...");

                player.sendPluginMessage(this, "BungeeCord", b.toByteArray());

                event.setCancelled(true);
            } else if (player.getItemInHand().equals(kitPvP)) {

                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                try {
                    out.writeUTF("Connect");
                    out.writeUTF("kitpvp");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(Variables.prefix + "Verbinde zu KitPvP...");

                player.sendPluginMessage(this, "BungeeCord", b.toByteArray());

                event.setCancelled(true);
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onItemMoveEvent(InventoryMoveItemEvent event) {
        event.setCancelled(true);
    }

    public void send(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void getSpawnLoc() {
        FileConfiguration cfg = getConfig();
        World world = Bukkit.getWorld(Main.cfg.getString("Config.spawnLocWorld"));
        double x = Main.cfg.getDouble("Config.spawnLocX");
        double y = Main.cfg.getDouble("Config.spawnLocY");
        double z = Main.cfg.getDouble("Config.spawnLocZ");
        float pitch = (float) Main.cfg.getDouble("Config.spawnLocPitch");
        float yaw = (float) Main.cfg.getDouble("Config.spawnLocYaw");
        Spawn.spawn = new Location(world, x, y, z, yaw, pitch);

    }



    public void setSpawn(Location spawn) {
        //FileConfiguration cfg = getConfig();
        Spawn.spawn = spawn;

        Main.cfg.set("Config.spawnLocWorld", spawn.getWorld().getName());
        Main.cfg.set("Config.spawnLocX", spawn.getX());
        Main.cfg.set("Config.spawnLocY", spawn.getY());
        Main.cfg.set("Config.spawnLocZ", spawn.getZ());
        Main.cfg.set("Config.spawnLocPitch", spawn.getPitch());
        Main.cfg.set("Config.spawnLocYaw", spawn.getPitch());
        try {
            Main.cfg.save(Main.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

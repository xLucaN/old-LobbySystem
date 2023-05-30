package me.unluztiglp.nightfront.lobby.commands;

/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.SkullMeta;


/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KopfCMD
/*    */   implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 26 */     if ((sender instanceof Player)) {
/* 27 */       Player p = (Player)sender;
/* 28 */       if ((p.hasPermission("nf.kopf")) || (p.hasPermission("nf.skull"))) {
/* 29 */         if (args.length == 1) {
/* 30 */           ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
/* 31 */           SkullMeta im = (SkullMeta)is.getItemMeta();
/* 32 */           im.setOwner(args[0]);
/* 33 */           im.setDisplayName("§7Kopf von: §c" + args[0]);
/*    */           
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 39 */           is.setItemMeta(im);
/* 40 */           p.getInventory().addItem(new ItemStack[] { is });
/* 41 */           p.updateInventory();
/* 42 */           p.sendMessage("§8§l[§6§lAlbiSys§8§l] §7Du hast den Kopf von §c" + args[0] + "§7 in dein Inventar gelegt bekommen.");
/*    */         } else {
/* 44 */           p.sendMessage("§8§l[§6§lAlbiSys§8§l] §7/skull <Spieler>");
/*    */         }
/*    */       } else {
/* 47 */         p.sendMessage("§8§l[§6§lAlbiSys§8§l] §7Dazu hast du keine Rechte!");
/*    */       }
/*    */     } else {
/* 50 */       System.out.println("dddd");
/*    */     }
/* 52 */     return false;
/*    */   }
/*    */ }

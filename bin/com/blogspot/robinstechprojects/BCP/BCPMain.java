/*     */ package com.blogspot.robinstechprojects.BCP;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.FileConfigurationOptions;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ 
/*     */ public class BCPMain extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener
/*     */ {
/*     */   public void onEnable()
/*     */   {
/*  22 */     initConfig();
/*  23 */     Bukkit.getServer().getPluginManager().registerEvents(this, this);
/*  24 */     System.out.println(getConfig().getString("outputs.stop"));
/*     */   }
/*     */   
/*     */   public void initConfig()
/*     */   {
/*  29 */     reloadConfig();
/*     */     
/*  31 */     getConfig().options().header("Better Commands Plugin - Configuration -");
/*     */     
/*     */ 
/*  34 */     getConfig().addDefault("outputs.start", "[BCP] wurde gestartet !");
/*  35 */     getConfig().addDefault("outputs.stop", "[BCP] wurde gestopppt");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  40 */     getConfig().addDefault("commands.messages.gamemode set to Survival", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Survival geändert");
/*  41 */     getConfig().addDefault("commands.messages.gamemode set to Creative", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Creative geändert ");
/*  42 */     getConfig().addDefault("commands.messages.gamemode set to Adventure", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Adventure geändert");
/*  43 */     getConfig().addDefault("commands.messages.gamemode set to Spectator", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Spectator geändert");
/*     */     
/*  45 */     getConfig().addDefault("commands.broadcasts.afk.settrue", ChatColor.DARK_GREEN + " ist mal kurz abwesend!");
/*  46 */     getConfig().addDefault("commands.broadcasts.afk.setfalse", ChatColor.DARK_GREEN + " ist nun wieder da !");
/*  47 */     getConfig().addDefault("commands.broadcasts.time.day", ChatColor.DARK_GREEN + " hat die Zeit zu Tag geändert");
/*  48 */     getConfig().addDefault("commands.broadcasts.time.night", ChatColor.DARK_GREEN + " hat die Zeit zu Nacht geändert");
/*     */     
/*  50 */     getConfig().addDefault("commands.messages.player got healed", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Du wurdest geheilt !");
/*     */     
/*  52 */     getConfig().addDefault("commands.messages.player´s food filled", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Deine Foodbar wurde aufgefuellt");
/*     */     
/*  54 */     getConfig().addDefault("bcp.commands.messages.selfclear.cleared", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Inventar wurde gelöscht !");
/*     */     
/*  56 */     getConfig().options().copyDefaults(true);
/*  57 */     saveConfig();
/*  58 */     System.out.println(getConfig().getString("outputs.start"));
/*     */   }
/*     */   
/*     */   public void onDisable()
/*     */   {
/*  63 */     System.out.println(getConfig().getString("outputs.stop"));
/*     */   }
/*     */   
/*  66 */   ArrayList<Player> afk = new ArrayList();
/*     */   
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*     */   {
/*  71 */     if (!(sender instanceof Player)) {
/*  72 */       System.out.println("Only players can use commands!");
/*     */     }
/*     */     
/*  75 */     Player p = (Player)sender;
/*  76 */     Player target = Bukkit.getServer().getPlayer(args[0]);
/*  77 */     if ((cmd.getName().equalsIgnoreCase("night")) && (p.hasPermission("bcp.time.night"))) {
/*  78 */       Bukkit.broadcastMessage(ChatColor.RED + "[BCP] " + p.getDisplayName() + getConfig().getString("commands.broadcasts.time.night"));
/*  79 */       p.getWorld().setTime(18000L);
/*     */     }
/*  81 */     if (cmd.getName().equalsIgnoreCase("day")) {
/*  82 */       if ((cmd.getName().equalsIgnoreCase("day")) && (p.hasPermission("bcp.time.day")))
/*  83 */         Bukkit.broadcastMessage(ChatColor.RED + "[BCP] " + p.getDisplayName() + getConfig().getString("commands.broadcasts.time.day"));
/*  84 */       p.getWorld().setTime(0L);
/*     */     }
/*  86 */     if ((cmd.getName().equalsIgnoreCase("gm0" + target)) && (p.hasPermission("bcp.gm0")))
/*     */     {
/*  88 */       p.setGameMode(GameMode.SURVIVAL);
/*  89 */       p.sendMessage(getConfig().getString("commands.messages.gamemode set to Survival"));
/*     */     }
/*     */     else {
/*  92 */       p.setGameMode(GameMode.SURVIVAL);
/*  93 */       p.sendMessage(getConfig().getString("commands.messages.gamemode set to Survival"));
/*     */     }
/*  95 */     if ((cmd.getName().equalsIgnoreCase("gm1")) && (p.hasPermission("bcp.gm1")))
/*     */     {
/*  97 */       p.setGameMode(GameMode.CREATIVE);
/*  98 */       p.sendMessage(getConfig().getString("commands.messages.gamemode set to Creative"));
/*     */     }
/* 100 */     if ((cmd.getName().equalsIgnoreCase("gm2")) && (p.hasPermission("bcp.gm2")))
/*     */     {
/* 102 */       p.setGameMode(GameMode.ADVENTURE);
/* 103 */       p.sendMessage(getConfig().getString("commands.messages.gamemode set to Adventure"));
/*     */     }
/* 105 */     if ((cmd.getName().equalsIgnoreCase("gm3")) && (p.hasPermission("bcp.gm3")))
/*     */     {
/* 107 */       p.setGameMode(GameMode.SPECTATOR);
/* 108 */       p.sendMessage(getConfig().getString("commands.messages.gamemode set to Spectator"));
/*     */     }
/* 110 */     if ((cmd.getName().equalsIgnoreCase("heal")) && (p.hasPermission("bcp.heal")) && 
/* 111 */       (p.hasPermission("bcp.heal")))
/*     */     {
/* 113 */       p.setHealth(20.0D);
/* 114 */       p.sendMessage(getConfig().getString("commands.messages.player got healed"));
/*     */     }
/* 116 */     if ((cmd.getName().equalsIgnoreCase("food")) && (p.hasPermission("bcp.food")))
/*     */     {
/* 118 */       p.setFoodLevel(20);
/* 119 */       p.sendMessage(getConfig().getString("commands.messages.player´s food filled"));
/*     */     }
/* 121 */     if ((cmd.getName().equalsIgnoreCase("bio")) && (p.hasPermission("bcp.bio"))) {
/* 122 */       p.sendMessage(ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + "Du befindest dich in : " + p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).name());
/*     */     }
/* 124 */     if (cmd.getName().equalsIgnoreCase("bettercommands")) {
/* 125 */       p.sendMessage("You are running BetterCommands V 1.0 !");
/*     */     }
/* 127 */     if (cmd.getName().equalsIgnoreCase("selfclear"))
/*     */     {
/* 129 */       p.getInventory().clear();
/* 130 */       p.sendMessage(getConfig().getString("bcp.commands.messages.selfclear.cleared"));
/*     */     }
/* 132 */     if ((cmd.getName().equalsIgnoreCase("afk")) && 
/* 133 */       (p.hasPermission("bcp.afk"))) {
/* 134 */       if (args.length == 0)
/*     */       {
/* 136 */         if (this.afk.contains(p))
/*     */         {
/* 138 */           this.afk.remove(p);
/* 139 */           Bukkit.broadcastMessage(ChatColor.RED + "[BCP] " + p.getDisplayName() + getConfig().getString("commands.broadcasts.afk.setfalse"));
/*     */         }
/*     */         else
/*     */         {
/* 143 */           this.afk.add(p);
/* 144 */           Bukkit.broadcastMessage(ChatColor.RED + "[BCP] " + p.getDisplayName() + getConfig().getString("commands.broadcasts.afk.settrue"));
/*     */         }
/*     */       }
/*     */       else {
/* 148 */         p.sendMessage("Zu viele Argumente ! Versuche /afk ");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 154 */     return true;
/*     */   }
/*     */   
/*     */   @org.bukkit.event.EventHandler
/* 158 */   public void PlayerMove(PlayerMoveEvent e) { Player p2 = e.getPlayer();
/*     */     
/* 160 */     if (this.afk.contains(p2)) {
/* 161 */       this.afk.remove(p2);
/* 162 */       Bukkit.broadcastMessage(ChatColor.RED + "[BCP] " + p2.getDisplayName() + getConfig().getString("commands.broadcasts.afk.setfalse"));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/robin/Schreibtisch/BCP V1.1 Build #004.jar!/com/blogspot/robinstechprojects/BCP/BCPMain.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
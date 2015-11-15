package com.blogspot.robinstechprojects.BCP;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BCPMain
  extends JavaPlugin
{
  public void onEnable()
  {
    initConfig();
  }
  
  public void initConfig()
  {
    reloadConfig();
    getConfig().options().header("Better Commands Plugin - Config -");
    getConfig().addDefault("bcp.commands.gm0.messages.Gamemode Changed to Surivival", "Spielmodus zu Überlebensmodus geändert");
    getConfig().addDefault("bcp.commands.food.Food Filled !", "Essen aufgefuellt");
    
    getConfig().addDefault("bcp.commands.h.healed", "Du wurdest geheilt!");
    getConfig().addDefault("bcp.commands.gm1.messages.Gamemode Changed to Creative", "Spielmodus zu Kreativmodus geändert");
    getConfig().addDefault("bcp.commands.gm2.messages.Gamemode Changed to Adventure", "Spielmodus zu Abenteuermodus geändert");
    getConfig().addDefault("bcp.commands.stopp", "[BCP gestoppt]");
    getConfig().addDefault("bcp.commands.start", "[BCP gestartet]");
    getConfig().addDefault("bcp.commands.bio.You are in", "Du befindest dich in dem Biom:");
    getConfig().addDefault("bcp.commands.selfclear.cleared", "Dein Inventar wurde gereinigt");
    getConfig().options().copyDefaults(true);
    saveConfig();
    System.out.println(getConfig().getString("bcp.commands.start"));
  }
  
  public void onDisable()
  {
    System.out.println(getConfig().getString("bcp.commands.stopp"));
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!(sender instanceof Player)) {
      System.out.println("Only players can use commands!");
    }
    Player p = (Player)sender;
    GameMode gm = null;
    GameMode gm1 = gm;
    if ((cmd.getName().equalsIgnoreCase("gm0")) && (p.hasPermission("bcp.gm0")))
    {
      p.setGameMode(GameMode.SURVIVAL);
      p.sendMessage(getConfig().getString("bcp.commands.gm0.messages.Gamemode Changed to Surivival"));
    }
    if ((cmd.getName().equalsIgnoreCase("gm1")) && (p.hasPermission("bcp.gm1")))
    {
      p.setGameMode(GameMode.CREATIVE);
      p.sendMessage(getConfig().getString("bcp.commands.gm1.messages.Gamemode Changed to Creative"));
    }
    if ((cmd.getName().equalsIgnoreCase("gm2")) && (p.hasPermission("bcp.gm2")))
    {
      p.setGameMode(GameMode.ADVENTURE);
      p.sendMessage(getConfig().getString("bcp.commands.gm2.messages.Gamemode Changed to Adventure"));
    }
    if ((cmd.getName().equalsIgnoreCase("heal")) && (p.hasPermission("bcp.heal")) && 
      (p.hasPermission("bcp.heal")))
    {
      p.setHealth(20.0D);
      p.sendMessage(getConfig().getString("bcp.commands.h.healed"));
    }
    if ((cmd.getName().equalsIgnoreCase("food")) && (p.hasPermission("bcp.food")))
    {
      p.setFoodLevel(20);
      p.sendMessage(getConfig().getString("bcp.commands.food.Food Filled !"));
    }
    if ((cmd.getName().equalsIgnoreCase("bio")) && (p.hasPermission("bcp.bio"))) {
      p.sendMessage(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).name());
    }
    if (cmd.getName().equalsIgnoreCase("bettercommands")) {
      p.sendMessage("BetterCommands Plugin works perfect!");
    }
    if (cmd.getName().equalsIgnoreCase("selfclear"))
    {
      p.getInventory().clear();
      p.sendMessage("bcp.commands.selfclear.DEIN Inventar wurde gereinigt!");
    }
    if (cmd.getName().equalsIgnoreCase("afk"))
    {
      p.sendMessage("Du bist nun AFK Tippe /re ein um deinen AFK Status aufzuheben");
      gm = p.getGameMode();
      p.setGameMode(GameMode.SPECTATOR);
      Server s = Bukkit.getServer();
      s.broadcastMessage(p.getDisplayName() + "Ist nun AFK");
    }
    if (cmd.getName().equalsIgnoreCase("re"))
    {
      Server s = Bukkit.getServer();
      p.setGameMode(gm1);
      s.broadcastMessage(p.getDisplayName() + "& Ist nun nicht mehr AFK");
    }
    return true;
  }
}

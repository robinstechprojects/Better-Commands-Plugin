package eu.techup.bcp;

import java.io.PrintStream;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
{
  public void onEnable()
  {
    initConfig();
  }
  
  public void initConfig()
  {
    reloadConfig();
    
    getConfig().options().header("Better Commands Plugin - Configuration -");
    
    getConfig().addDefault("outputs.start", "[BCP] wurde gestartet !");
    getConfig().addDefault("outputs.stop", "[BCP] wurde gestopppt");
    
    getConfig().addDefault("commands.messages.gamemode set to Survival", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Survival ge�ndert");
    getConfig().addDefault("commands.messages.gamemode set to Creative", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Creative ge�ndert ");
    getConfig().addDefault("commands.messages.gamemode set to Adventure", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Adventure ge�ndert");
    getConfig().addDefault("commands.messages.gamemode set to Spectator", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Spectator ge�ndert");
    
    getConfig().addDefault("commands.messages.player got healed", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Du wurdest geheilt !");
    
    getConfig().addDefault("commands.messages.player�s food filled", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Deine Foodbar wurde aufgefuellt");
    
    getConfig().options().copyDefaults(true);
    saveConfig();
    System.out.println(getConfig().getString("outputs.start"));
  }
  
  public void onDisable()
  {
    System.out.println(getConfig().getString("outputs.stop"));
  }
  
  ArrayList<Player> afk = new ArrayList();
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!(sender instanceof Player)) {
      System.out.println("Only players can use commands!");
    }
    Player p = (Player)sender;
    if ((cmd.getName().equalsIgnoreCase("gm0")) && (p.hasPermission("bcp.gm0")))
    {
      p.setGameMode(GameMode.SURVIVAL);
      p.sendMessage(getConfig().getString("commands.messages.gamemode set to Survival"));
    }
    if ((cmd.getName().equalsIgnoreCase("gm1")) && (p.hasPermission("bcp.gm1")))
    {
      p.setGameMode(GameMode.CREATIVE);
      p.sendMessage(getConfig().getString("commands.messages.gamemode set to Creative"));
    }
    if ((cmd.getName().equalsIgnoreCase("gm2")) && (p.hasPermission("bcp.gm2")))
    {
      p.setGameMode(GameMode.ADVENTURE);
      p.sendMessage(getConfig().getString("commands.messages.gamemode set to Adventure"));
    }
    if ((cmd.getName().equalsIgnoreCase("gm3")) && (p.hasPermission("bcp.gm3")))
    {
      p.setGameMode(GameMode.SPECTATOR);
      p.sendMessage(getConfig().getString("commands.messages.gamemode set to Spectator"));
    }
    if ((cmd.getName().equalsIgnoreCase("heal")) && (p.hasPermission("bcp.heal")) && 
      (p.hasPermission("bcp.heal")))
    {
      p.setHealth(20.0D);
      p.sendMessage(getConfig().getString("commands.messages.player got healed"));
    }
    if ((cmd.getName().equalsIgnoreCase("food")) && (p.hasPermission("bcp.food")))
    {
      p.setFoodLevel(20);
      p.sendMessage(getConfig().getString("commands.messages.player�s food filled"));
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
      p.sendMessage("bcp.commands.selfclear.cleared");
    }
    if (cmd.getName().equalsIgnoreCase("afk")) {
      if (args.length == 0)
      {
        if (this.afk.contains(p))
        {
          this.afk.remove(p);
          Bukkit.broadcastMessage(p.getName() + " ist nun wieder da!");
        }
        else
        {
          this.afk.add(p);
          Bukkit.broadcastMessage(p.getName() + " ist nun abwesend!");
        }
      }
      else {
        p.sendMessage("Falscher Befehl ! Versuche /afk ");
      }
    }
    return true;
  }
}

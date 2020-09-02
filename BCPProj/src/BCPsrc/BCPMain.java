package BCPsrc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BCPMain
  extends JavaPlugin
  implements Listener
{
  public void onEnable()
  {
    //initConfig();
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    //System.out.println(getConfig().getString("outputs.stop"));
  }
  /*
  public void initConfig()
  {
    initConfig();
    
    getConfig().options().header("Better Commands Plugin - Configuration -");
    
    getConfig().addDefault("outputs.start", "[BCP] wurde gestartet !");
    getConfig().addDefault("outputs.stop", "[BCP] wurde gestopppt");
    
    getConfig().addDefault("commands.messages.gamemode set to Survival", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Survival ge��ndert");
    getConfig().addDefault("commands.messages.gamemode set to Creative", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Creative ge��ndert ");
    getConfig().addDefault("commands.messages.gamemode set to Adventure", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Adventure ge��ndert");
    getConfig().addDefault("commands.messages.gamemode set to Spectator", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Spielmodus wurde zu Spectator ge��ndert");
    
    getConfig().addDefault("commands.broadcasts.afk.settrue", ChatColor.DARK_GREEN + " ist mal kurz abwesend!");
    getConfig().addDefault("commands.broadcasts.afk.setfalse", ChatColor.DARK_GREEN + " ist nun wieder da !");
    getConfig().addDefault("commands.broadcasts.time.day", ChatColor.DARK_GREEN + " hat die Zeit zu Tag ge��ndert");
    getConfig().addDefault("commands.broadcasts.time.night", ChatColor.DARK_GREEN + " hat die Zeit zu Nacht ge��ndert");
    
    getConfig().addDefault("commands.messages.player got healed", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Du wurdest geheilt !");
    
    getConfig().addDefault("commands.messages.player��s food filled", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Deine Foodbar wurde aufgefuellt");
    
    getConfig().addDefault("bcp.commands.messages.selfclear.cleared", ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + " Dein Inventar wurde gel��scht !");
    
    getConfig().options().copyDefaults(true);
    saveConfig();
    System.out.println(getConfig().getString("outputs.start"));
  }
  */
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
    Player target = Bukkit.getServer().getPlayer(args[0]);
    if ((cmd.getName().equalsIgnoreCase("night")) && (p.hasPermission("bcp.time.night")))
    {
      Bukkit.broadcastMessage(ChatColor.RED + "[BCP] " + p.getDisplayName() + getConfig().getString("commands.broadcasts.time.night"));
      p.getWorld().setTime(18000L);
    }
    if (cmd.getName().equalsIgnoreCase("day"))
    {
      if ((cmd.getName().equalsIgnoreCase("day")) && (p.hasPermission("bcp.time.day"))) {
        Bukkit.broadcastMessage(ChatColor.RED + "[BCP] " + p.getDisplayName() + getConfig().getString("commands.broadcasts.time.day"));
      }
      p.getWorld().setTime(0L);
    }
    if ((cmd.getName().equalsIgnoreCase("gm0" + target)) && (p.hasPermission("bcp.gm0")))
    {
      p.setGameMode(GameMode.SURVIVAL);
      p.sendMessage(getConfig().getString("commands.messages.gamemode set to Survival"));
    }
    else
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
      p.sendMessage(getConfig().getString("commands.messages.player��s food filled"));
    }
    if ((cmd.getName().equalsIgnoreCase("bio")) && (p.hasPermission("bcp.bio"))) {
      p.sendMessage(ChatColor.RED + "[BCP]" + ChatColor.DARK_GREEN + "Du befindest dich in : " + p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).name());
    }
    if (cmd.getName().equalsIgnoreCase("bettercommands")) {
      p.sendMessage("You are running BetterCommands V 1.0 !");
    }
    if (cmd.getName().equalsIgnoreCase("selfclear"))
    {
      p.getInventory().clear();
      p.sendMessage(getConfig().getString("bcp.commands.messages.selfclear.cleared"));
    }
    if ((cmd.getName().equalsIgnoreCase("afk")) && 
      (p.hasPermission("bcp.afk"))) {
      if (args.length == 0)
      {
        if (this.afk.contains(p))
        {
          this.afk.remove(p);
          Bukkit.broadcastMessage(ChatColor.RED + "[BCP] " + p.getDisplayName() + getConfig().getString("commands.broadcasts.afk.setfalse"));
        }
        else
        {
          this.afk.add(p);
          Bukkit.broadcastMessage(ChatColor.RED + "[BCP] " + p.getDisplayName() + getConfig().getString("commands.broadcasts.afk.settrue"));
        }
      }
      else {
        p.sendMessage("Zu viele Argumente ! Versuche /afk ");
      }
    }
    return true;
  }
  
  @EventHandler
  public void PlayerMove(PlayerMoveEvent e)
  {
    Player p2 = e.getPlayer();
    if (this.afk.contains(p2))
    {
      this.afk.remove(p2);
      Bukkit.broadcastMessage(ChatColor.RED + "[BCP] " + p2.getDisplayName() + getConfig().getString("commands.broadcasts.afk.setfalse"));
    }
  }
}

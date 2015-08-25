package com.blogspot.robinstechprojects.BCP;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BCPMain extends JavaPlugin {

	@Override
	public void onEnable() {
		initConfig();
		
	}

	
		
		
	

	//Conf
	public void initConfig() {
		this.reloadConfig();
		this.getConfig().options().header("Better Commands Plugin - Config -");
		this.getConfig().addDefault("bcp.commands.gm0.messages.Gamemode Changed to Surivival" , "Spielmodus zu Überlebensmodus geändert");
		this.getConfig().addDefault("bcp.commands.food.Food Filled !" , "Essen aufgefuellt");
		this.getConfig().addDefault("bcp.commands.xpf.XP Filled" , "XP Aufgefuellt");
		this.getConfig().addDefault("bcp.commands.h.healed" , "Du wurdest geheilt!");
		this.getConfig().addDefault("bcp.commands.gm1.messages.Gamemode Changed to Creative" , "Spielmodus zu Kreativmodus geändert");
		this.getConfig().addDefault("bcp.commands.gm2.messages.Gamemode Changed to Adventure" , "Spielmodus zu Abenteuermodus geändert");
		this.getConfig().addDefault("bcp.commands.stopp" , "[BCP gestoppt]");
		this.getConfig().addDefault("bcp.commands.start" , "[BCP gestartet]");
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		System.out.println(this.getConfig().getString("bcp.commands.start"));
	}

	public void onDisable() {
		System.out.println(this.getConfig().getString("bcp.commands.stopp"));
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (!(sender instanceof Player)) {
			System.out.println("Only players can use commands!");
			return true;
		}

		Player p = (Player) sender;

		//gm0
		if (cmd.getName().equalsIgnoreCase("gm0") && p.hasPermission("bcp.gm0")) {
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage(this.getConfig().getString("bcp.commands.gm0.messages.Gamemode Changed to Survival"));
		}

		//gm1
		if (cmd.getName().equalsIgnoreCase("gm1") && p.hasPermission("bcp.gm1")) {
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage(this.getConfig().getString("bcp.commands.gm1.messages.Gamemode Changed to Creative"));
		}

		//gm2
		if (cmd.getName().equalsIgnoreCase("gm2") && p.hasPermission("bcp.gm2")) {
			p.setGameMode(GameMode.ADVENTURE);
			p.sendMessage(this.getConfig().getString("bcp.commands.gm2.messages.Gamemode Changed to Adventure"));
		}

		//h
		if (cmd.getName().equalsIgnoreCase("h") && p.hasPermission("bcp.heal")) {
			p.setHealth(20F);
			p.sendMessage(this.getConfig().getString("bcp.commands.h.healed"));
		}

		//xp
		if (cmd.getName().equalsIgnoreCase("xpf") && p.hasPermission("bcp.xp")) {
			p.setExp(p.getExp() + 100);
			p.sendMessage(this.getConfig().getString("bcp.commands.xpf.XP Filled"));
		}

		//food
		if (cmd.getName().equalsIgnoreCase("food") && p.hasPermission("bcp.food")) {
			p.setFoodLevel(20);
			p.sendMessage(this.getConfig().getString("bcp.commands.food.Food Filled !"));
		}

		//Biome
		if (cmd.getName().equalsIgnoreCase("bio") && p.hasPermission("bcp.bio"))
			p.sendMessage(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).name());

		//BCP
		if (cmd.getName().equalsIgnoreCase("bcp"))
			p.sendMessage("BetterCommands works perfect !");

		//BetterCommands
		if(cmd.getName().equalsIgnoreCase("bettercommands"))
			p.sendMessage("BetterCommands Plugin works perfect!");
		
	  
	       
	   
		
		return true;
		
	
			
		

}
	//Home Methoden
	public static void save(Object obj, String path) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	public static Object load(String path) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		Object result = ois.readObject();
		ois.close();
		return result;
	}


}

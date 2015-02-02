package bcp;
	  
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BCP extends JavaPlugin {
	
	@Override
	public void onEnable() {
		initConfig();
	}
	
	private void initConfig() {
		this.reloadConfig();
		this.getConfig().options().header("Keep Antention");
		this.getConfig().addDefault("bcp.commands.gm0.messages.Gamemode Changed to Surivival" , "Spielmodus zu Überlebensmodus geändert");
		this.getConfig().addDefault("bcp.commands.gm1.messages.Gamemode Changed to Creative" , "Spielmodus zu Kreativmodus geändert");
		this.getConfig().addDefault("bcp.commands.gm2.messages.Gamemode Changed to Adventure" , "Spielmodus zu Abenteuermodus geändert");
		this.getConfig().addDefault("bcp.commands.stopp" , "[BCP gestoppt]");
		this.getConfig().addDefault("bcp.commands.start" , "[BCP gestartet]");
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		System.out.println(this.getConfig().getString("bcp.commands.start"));
	}
	
	@Override
	public void onDisable() {
		System.out.println(this.getConfig().getString("bcp.commands.stopp"));
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (!(sender instanceof Player)) {
			System.out.println("Only players can use commands!");
			return true;
		}
		
		Player p = (Player)sender;
	
		//gm0
		if(cmd.getName().equalsIgnoreCase("gm0")) {
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage(this.getConfig().getString("bcp.commands.gm0.messages.Gamemode Changed to Survival"));
		}
		
		//gm1  
		if(cmd.getName().equalsIgnoreCase("gm1")) {
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage(this.getConfig().getString("bcp.commands.gm1.messages.Gamemode Changed to Creative"));
		}
		
		//gm2
		if(cmd.getName().equalsIgnoreCase("gm2")) {
			p.setGameMode(GameMode.ADVENTURE);
			p.sendMessage(this.getConfig().getString("bcp.commands.gm2.messages.Gamemode Changed to Adventure"));
		}
		
		//h
		if(cmd.getName().equalsIgnoreCase("h"))
			if(p.hasPermission("bcp.heal"))
				p.setHealthScale(20);
		//xp 
		if(cmd.getName().equalsIgnoreCase("xpf"))
			if(p.hasPermission("bcp.xp"))
				p.setExp(1000);
		//food	
		if(cmd.getName().equalsIgnoreCase("food"))
			if(p.hasPermission("bcp.food"))
				p.setFoodLevel(20);
			 
		//Biome
		if(cmd.getName().equalsIgnoreCase("bio"))
			if (p.hasPermission("bcp.bio"))
					p.sendMessage(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).name());
						 
		 	return true;
	}
}

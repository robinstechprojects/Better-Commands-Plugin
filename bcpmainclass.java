	

    package bcp;
     
    import org.bukkit.GameMode;
    import org.bukkit.command.Command;
    import org.bukkit.command.CommandSender;
    import org.bukkit.entity.Player;
    import org.bukkit.plugin.java.JavaPlugin;
    //Server Boot Message
    public class bcpmain extends JavaPlugin {
            public void onEnable()  {
                    System.out.println("[bcp] geladen");
            }
            public void onDisable()  {
                    System.out.println("[bcp] gestoppt");
            }
            //gm0
                    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
                    Player p = (Player)sender;
                    if(cmd.getName().equalsIgnoreCase("gm0"))
                            if(p.hasPermission("bcp.gm0"))
                            p.setGameMode(GameMode.SURVIVAL);
                           
                    //gm1  
                   
                    if(cmd.getName().equalsIgnoreCase("gm1"))
                            if(p.hasPermission("bcp.gm1"))
                                    p.setGameMode(GameMode.CREATIVE);
           
                   
                    //gm2
                    if(cmd.getName().equalsIgnoreCase("gm2"))
                            if(p.hasPermission("bcp.gm2"))
                                    p.setGameMode(GameMode.ADVENTURE);
                   
                    //h
                    if(cmd.getName().equalsIgnoreCase("h"))
                            if(p.hasPermission("bcp.heal"))
                                    p.setHealthScale(20);
                    //xp 
		if(cmd.getName().equalsIgnoreCase("xpf"))
			if(p.hasPermission("bcp.xp"))
	        	p.setExp(1000);
		   		
		    //food
		    if(cmd.getName.equalsIgnoreCase("food"))
		    if(p.hasPermission("bcp.food"))
		    p.setFoodLevel(20)
			
                   
                    return true;


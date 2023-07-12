package fr.teamkiwi.powerrush.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.commands.CommandStart;

public class WhenPlayerJoins implements Listener {

	
	Main plugin;
    
    public WhenPlayerJoins(Main plugin) {
		this.plugin = plugin;
	}
	
	
	
	
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
    	
    	Player player = event.getPlayer();
    	
    	
    	if(! (CommandStart.isStarted)) {
    		
    		event.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + "] " + ChatColor.YELLOW + player.getName() + ChatColor.AQUA + " a rejoint la partie !");
    	
	    	//if game is started set gamemode of player to spectator
	    	
	    		
		    //else kick player
		    if (Bukkit.getOnlinePlayers().size() > plugin.getConfig().getInt("config.maxPlayers") && plugin.getConfig().getInt("config.maxPlayers") > 0) {
		
		    	player.kickPlayer("La partie est complete, il y a deja " + (Bukkit.getOnlinePlayers().size() - 1) + " / " + plugin.getConfig().getInt("config.maxPlayers") + " joueurs connectes !");
		        event.setJoinMessage(OnClickInventory.consoleSender + player.getName() + ChatColor.AQUA + " essaie de rejoindre la partie");
		        
		    }
	    		
	    	
    	}else {
    		
    		//if player is not IG set to spectator mode
    		if(! CommandStart.allPlayersInGame.contains(player.getUniqueId())) {
    			
    			player.setGameMode(GameMode.SPECTATOR);
        		player.sendMessage(OnClickInventory.consoleSender + ChatColor.RED + "La partie a deja commence, " + ChatColor.AQUA + "vous avez ete mis en mode spectateur");
        		event.setJoinMessage(OnClickInventory.consoleSender + player.getName() + ChatColor.AQUA + " a rejoint en tant que spectateur !");
    			
    			
    		}else {
	    		//else comme back
	    		event.setJoinMessage(OnClickInventory.consoleSender + player.getName() + ChatColor.AQUA + " est revenu en jeu !");
    			
    		}
    		
    		
    	}
    	
    	
    	
    }

}

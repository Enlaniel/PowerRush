package fr.teamkiwi.powerrush.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.teamkiwi.powerrush.commands.CommandStart;

public class WhenPlayerJoins implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
    	
    	Player player = event.getPlayer();
    	
    	
    	if(! (CommandStart.isStarted)) {
    		
    		event.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + "] " + ChatColor.YELLOW + player.getName() + ChatColor.AQUA + " a rejoint la partie !");
    	
	    	//if game is started set gamemode of player to spectator
	    	
	    		
		    //else kick player
		    if (Bukkit.getOnlinePlayers().size() > OnClickInventory.maxPlayerConnected && OnClickInventory.maxPlayerConnected > 0) {
		
		    	player.kickPlayer("La partie est complete, il y a deja " + (Bukkit.getOnlinePlayers().size() - 1) + " / " + OnClickInventory.maxPlayerConnected + " joueurs connectes !");
		        event.setJoinMessage(OnClickInventory.consoleSender + player.getName() + ChatColor.AQUA + " essaie de rejoindre la partie");
		            
		    }
	    		
	    	
    	}else {
    		
    		if(! CommandStart.allPlayersInGame.contains(player.getUniqueId())) {
    			
    			player.setGameMode(GameMode.SPECTATOR);
        		player.sendMessage(OnClickInventory.consoleSender + ChatColor.RED + "La partie a deja commence, " + ChatColor.AQUA + "vous avez ete mis en mode spectateur");
        		event.setJoinMessage(OnClickInventory.consoleSender + player.getName() + ChatColor.AQUA + " a rejoint en tant que spectateur !");
    			
    			
    		}else {
	    		
	    		event.setJoinMessage(OnClickInventory.consoleSender + player.getName() + ChatColor.AQUA + " est revenu en jeu !");
    			
    		}
    		
    		
    	}
    	
    	
    	
    }

}

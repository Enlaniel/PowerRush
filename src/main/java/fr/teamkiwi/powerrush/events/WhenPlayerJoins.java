package fr.teamkiwi.powerrush.events;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class WhenPlayerJoins implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
    	
    	Player player = event.getPlayer();

		Game game = Game.getPlayerGame(player);
		if(game == null) {
			//if player is not IG set to spectator mode
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage(OnClickInventory.consoleSender + ChatColor.RED + "La partie a deja commence, " + ChatColor.AQUA + "vous avez ete mis en mode spectateur");
			event.setJoinMessage(OnClickInventory.consoleSender + player.getName() + ChatColor.AQUA + " a rejoint en tant que spectateur !");
			return;
		}
    	
    	
    	if(! game.hasStarted()) {
    		
    		event.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + "] " + ChatColor.YELLOW + player.getName() + ChatColor.AQUA + " a rejoint la partie !");

    	} else {
			//else comme back
			event.setJoinMessage(OnClickInventory.consoleSender + player.getName() + ChatColor.AQUA + " est revenu en jeu !");
    	}
    	
    }

}

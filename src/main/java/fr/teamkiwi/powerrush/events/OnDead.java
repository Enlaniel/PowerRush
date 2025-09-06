package fr.teamkiwi.powerrush.events;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.teamkiwi.powerrush.utils.Kit;
import fr.teamkiwi.powerrush.Main;

public class OnDead implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		
		Player deadPlayer = event.getEntity();

		Game game = Game.getPlayerGame(deadPlayer);

		if(game == null) {
			return;
		}
		
		if(! game.playerHasKit(deadPlayer, Kit.Kits.AVENIR)) {
			killPlayer(event);
		}
		
	}
	
	
	
	public void killPlayer(PlayerDeathEvent event) {
		
		Player player = event.getEntity();

		Game game = Game.getPlayerGame(player);
		if(game == null) {
			return;
		}
		if(! game.hasStarted()) {
			return;
		}
		
		
		String deadPlayerKits = "";

		//TODO remove player
		//game.removePlayer()
		//CommandStart.allPlayersInGame.remove(player.getUniqueId());
		
		for(Kit aKit : game.getPlayerKits(player)) {
			deadPlayerKits = deadPlayerKits + ChatColor.GOLD +  "- " + aKit.getType().getName() + "\n";
		}
		
		event.setDeathMessage(ChatColor.BOLD + player.getName() + ChatColor.RESET + ChatColor.RED + " est mort, il avait les kits:\n" + deadPlayerKits);
		
		Bukkit.getWorld("world").playSound(player.getLocation(), Sound.WITHER_DEATH, 1, 0);
		
		player.setGameMode(GameMode.SPECTATOR);
		
		
		if(game.getAllPlayers().size() == 1) {
			game.stop();
		}
		
		
	}
	

}

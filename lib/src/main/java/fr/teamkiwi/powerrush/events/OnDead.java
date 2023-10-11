package fr.teamkiwi.powerrush.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.teamkiwi.powerrush.CommandInitServer;
import fr.teamkiwi.powerrush.Kit;
import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.Stop;
import fr.teamkiwi.powerrush.commands.CommandStart;

public class OnDead implements Listener {
	
	Main plugin;
	
	public OnDead(Main main) {
		plugin = main;
	}

	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		
		Player deadPlayer = event.getEntity();
		
		
		killPlayer(event);
		
		
		
	}
	
	
	
	public void killPlayer(PlayerDeathEvent event) {
		
		Player player = event.getEntity();
		
		String deadPlayerKits = "";
		
		CommandStart.allPlayersInGame.remove(player.getUniqueId());
		
		for(Kit aKit : CommandInitServer.allKits) {
			if(plugin.getConfig().getList("kits." + aKit.getName().toLowerCase()).contains(player.getName())){
				deadPlayerKits.concat(ChatColor.GOLD + aKit.getName() + "\n");
			}
		}
		
		event.setDeathMessage(ChatColor.BOLD + player.getName() + ChatColor.RESET + ChatColor.RED + " est mort, il avait les kits:\n" + deadPlayerKits);
		
		Bukkit.getWorld("world").playSound(player.getLocation(), Sound.WITHER_DEATH, 1, 0);
		
		player.setGameMode(GameMode.SPECTATOR);
		
		
		if(CommandStart.allPlayersInGame.size() == 1) {
			
			new Stop().stop();
			
		}
		
		
	}
	

}

package fr.teamkiwi.powerrush.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.teamkiwi.powerrush.CommandInitServer;
import fr.teamkiwi.powerrush.Kit;
import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.commands.CommandStart;

public class OnDead implements Listener {
	
	Main plugin;
	
	public OnDead(Main main) {
		plugin = main;
	}

	
	
	public void onDead(PlayerDeathEvent event) {
		
		Player deadPlayer = event.getEntity();
		String deadPlayerKits = "";
		
		CommandStart.allPlayersInGame.remove(deadPlayer.getUniqueId());
		
		for(Kit aKit : CommandInitServer.allKits) {
			if(plugin.getConfig().getList("kits." + aKit.getName().toLowerCase()).contains(deadPlayer.getName())){
				deadPlayerKits.concat(ChatColor.GOLD + aKit.getName() + "\n");
			}
		}
		
		event.setDeathMessage(ChatColor.BOLD + deadPlayer.getName() + ChatColor.RESET + ChatColor.RED + " est mort, il avait les kits:\n" + deadPlayerKits);
		
		deadPlayer.setGameMode(GameMode.SPECTATOR);
		
		
		if(CommandStart.allPlayersInGame.size() == 1) {
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "forcestop");
			
		}
		
	}

}

package fr.teamkiwi.powerrush.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.teamkiwi.powerrush.commands.CommandStart;

public class OnRespawn implements Listener {
	
	public void onRespawn(PlayerRespawnEvent event) {
		
		Player player = event.getPlayer();
		Location spawn = new Location(Bukkit.getWorld("world"), 0, 101, 0);
		
		if(! CommandStart.isStarted) {
			
			player.teleport(spawn);
			
		}
		
	}

}

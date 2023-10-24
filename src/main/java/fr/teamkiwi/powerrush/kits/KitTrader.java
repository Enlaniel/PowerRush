package fr.teamkiwi.powerrush.kits;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.teamkiwi.powerrush.utils.Targeter;

public class KitTrader implements Listener {
	
	public void kitTrader(PlayerInteractEvent event) {
		
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		Location playerLoc = player.getLocation();
		
		Entity target = Targeter.getTargetEntity(player);
		
		if(target != null && block == null) {
			player.teleport(target.getLocation());
			target.teleport(playerLoc);
		} else {
			player.sendMessage(ChatColor.RED + "Vous ne ciblez aucune entité");
		}
		
	}

}

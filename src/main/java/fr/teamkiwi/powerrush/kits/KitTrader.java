package fr.teamkiwi.powerrush.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Objective;

import fr.teamkiwi.powerrush.CommandInitServer;
import fr.teamkiwi.powerrush.utils.Kit;
import fr.teamkiwi.powerrush.utils.Targeter;

public class KitTrader implements Listener {
	
	@SuppressWarnings("deprecation")
	public void kitTrader(PlayerInteractEvent event) {
		
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		Location playerLoc = player.getLocation();
		Objective objectiveTrader = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Trader");
		Entity target = Targeter.getTargetEntity(player);
		Kit trader = null;
		
		for(Kit aKit : CommandInitServer.allKits) {
			if(aKit.getName().equals("Doppage")) {
				trader = aKit;
			}
		}
		
		if(objectiveTrader.getScore(player).getScore() >= trader.getCooldown()) {
			
			if(target != null && block == null) {
				player.teleport(target.getLocation());
				target.teleport(playerLoc);
				
				player.sendMessage(ChatColor.AQUA + "Vous avez echange votre position avec " + ChatColor.GOLD + target.getName());
				target.sendMessage(ChatColor.AQUA + "Vous avez echange votre position avec " + ChatColor.GOLD + player.getName());
				
				objectiveTrader.getScore(player).setScore(0);
				
			} else {
				player.sendMessage(ChatColor.RED + "Vous ne ciblez aucune entite");
			}
			
		}else {
			player.sendMessage(ChatColor.AQUA + "Kit Trader pret dans " + ChatColor.RED + (trader.getCooldown() - objectiveTrader.getScore(player).getScore()) + ChatColor.AQUA + " s");
		}
		
		
		
	}

}

package fr.teamkiwi.powerrush.events;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

import fr.teamkiwi.powerrush.CommandInitServer;
import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.commands.CommandStart;
import fr.teamkiwi.powerrush.utils.Kit;

public class OnSecond implements Runnable {

	private Main plugin = Main.getPlugin(Main.class);
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public void run() {

		Collection<? extends Player> allPlayers = Bukkit.getOnlinePlayers();
		
		if(CommandStart.isStarted) {
		
			// add one to all kits timer
			for(Kit aKit : CommandInitServer.allKits) {
				
				if(aKit.hasCooldown()) {
					
					Objective kitObj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(aKit.getName());
					List<String> kitConfig = (List<String>) plugin.getConfig().getList("kits." + aKit.getName().toLowerCase());
					
					for(Player aPlayer : allPlayers) {
						if(kitConfig.contains(aPlayer.getName())) {
							
							kitObj.getScore(aPlayer).setScore(kitObj.getScore(aPlayer).getScore() + 1);
							
							if(kitObj.getScore(aPlayer).getScore() == aKit.getCooldown()) {
								aPlayer.sendMessage(ChatColor.AQUA + "Capacite " + ChatColor.GOLD + aKit.getName() + ChatColor.AQUA + " rechargee");
							}
							
						}
						
					}
				}
				
			}
			
			// for special kits
			Objective objectiveAnge = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Ange");
			List<String> kitAnge = (List<String>) plugin.getConfig().getList("kits.ange");
		
			for(Player aPlayer : allPlayers) {

				if(kitAnge.contains(aPlayer.getName())) {
				
					if(aPlayer.isSneaking()) {
					
						objectiveAnge.getScore(aPlayer).setScore(objectiveAnge.getScore(aPlayer).getScore() + 1);
						
						if(objectiveAnge.getScore(aPlayer).getScore() == 3) {
							aPlayer.sendMessage(ChatColor.AQUA + "Capacite " + ChatColor.GOLD + "Ange" + ChatColor.AQUA + " rechargee");
						}
					
					}else {
						objectiveAnge.getScore(aPlayer).setScore(0);
					}
			
				}
			}
		
		// if game is not started
		}else {
			for(Player aPlayer : allPlayers) {
				aPlayer.setFoodLevel(20);
			}
		}
		
	}

}

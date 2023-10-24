package fr.teamkiwi.powerrush.events;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.commands.CommandStart;

public class OnSecond implements Runnable {

	private Main plugin = Main.getPlugin(Main.class);
	
	
	@SuppressWarnings("unchecked")
	List<String> kitAnge = (List<String>) plugin.getConfig().getList("kits.ange");
	@SuppressWarnings("unchecked")
	List<String> kitDoppage = (List<String>) plugin.getConfig().getList("kits.doppage");
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {

		Collection<? extends Player> allPlayers = Bukkit.getOnlinePlayers();
		
		Objective objectiveAnge = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Ange");
		Objective objectiveDoppage = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Doppage");
		
		
		for(Player aPlayer : allPlayers) {
			
			if(! CommandStart.isStarted) {
				aPlayer.setFoodLevel(20);
			}

			if(kitAnge.contains(aPlayer.getName())) {
				
				if(aPlayer.isSneaking()) {
					
					if(objectiveAnge.getScore(aPlayer).getScore() == 2) {
						aPlayer.sendMessage("Capacite Ange rechargee");
					}
					
					objectiveAnge.getScore(aPlayer).setScore(objectiveAnge.getScore(aPlayer).getScore() + 1);
					
				}else {
					objectiveAnge.getScore(aPlayer).setScore(0);
				}
				
			}if(kitDoppage.contains(aPlayer.getName())) {
				
				if(objectiveDoppage.getScore(aPlayer).getScore() == 120) {
					aPlayer.sendMessage("Capacite Doppage rechargee");
				}
				
				objectiveDoppage.getScore(aPlayer).setScore(objectiveDoppage.getScore(aPlayer).getScore() + 1);
				
			}
			
		}
		
	}

}

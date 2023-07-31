package fr.teamkiwi.powerrush.kits;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Objective;

public class KitDoppage {
	
	
	@SuppressWarnings("deprecation")
	public void kitDoppage(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		Objective objectiveDoppage = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Doppage");
		
		if(objectiveDoppage.getScore(player).getScore() >= 120) {
			
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3, 4, false, false));
			
		}else {
			player.sendMessage("Kit Doppage pret dans " + objectiveDoppage.getScore(player).getScore() + " s");
		}
		
		
		
	}
	

}

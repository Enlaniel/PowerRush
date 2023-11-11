package fr.teamkiwi.powerrush.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Objective;

import fr.teamkiwi.powerrush.CommandInitServer;
import fr.teamkiwi.powerrush.utils.Kit;

public class KitDoppage {
	
	
	@SuppressWarnings("deprecation")
	public void kitDoppage(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		Objective objectiveDoppage = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Doppage");
		PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 20*15, 4, true, false);
		Kit doppage = null;
		
		for(Kit aKit : CommandInitServer.allKits) {
			if(aKit.getName().equals("Doppage")) {
				doppage = aKit;
			}
		}
		
		if(objectiveDoppage.getScore(player).getScore() >= doppage.getCooldown()) {
			
			player.addPotionEffect(speed);
			
			objectiveDoppage.getScore(player).setScore(0);
			
		}else {
			player.sendMessage(ChatColor.AQUA + "Kit Trader pret dans " + ChatColor.RED + (doppage.getCooldown() - objectiveDoppage.getScore(player).getScore()) + "s");
		}
		
		
		
	}
	

}

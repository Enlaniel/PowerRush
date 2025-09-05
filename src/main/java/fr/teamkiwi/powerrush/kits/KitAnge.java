package fr.teamkiwi.powerrush.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Objective;

public class KitAnge {

	@SuppressWarnings("deprecation")
	public void kitAnge(PlayerItemConsumeEvent event) {

		Player player = event.getPlayer();
		
		Objective objectiveAnge = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Ange");
		
		//prevent error
		if(event.getItem() != null) {
			
			Material eventMat = event.getItem().getType();
			
			if(eventMat.equals(Material.GOLDEN_APPLE)) {
				
				for(PotionEffect anEffect : player.getActivePotionEffects()) {
					player.removePotionEffect(anEffect.getType());
				}
				
				if(objectiveAnge.getScore(player).getScore() >= 2) {
					
					PotionEffect potionEffectAbso = new PotionEffect(PotionEffectType.ABSORPTION, 2*60*20, 1, false, false);
					
					player.addPotionEffect(potionEffectAbso);
					
					objectiveAnge.getScore(player).setScore(0);
				}
				
				PotionEffect potionEffectRegen = new PotionEffect(PotionEffectType.REGENERATION, 2*20+10, 2, false, false);
				
				player.addPotionEffect(potionEffectRegen);

			}
			
		}
		
		
	}

}

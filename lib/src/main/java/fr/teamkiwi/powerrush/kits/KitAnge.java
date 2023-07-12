package fr.teamkiwi.powerrush.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class KitAnge {

	public void kitAnge(PlayerItemConsumeEvent event) {

		//prevent error
		if(event.getItem() != null) {
			
			Material eventMat = event.getItem().getType();
			
			if(eventMat.equals(Material.GOLDEN_APPLE)) {
				
				Player player = event.getPlayer();
				
				PotionEffect potionEffect = new PotionEffect(PotionEffectType.REGENERATION, 4, 2, false, false);
				
				player.addPotionEffect(potionEffect);
				
			}
			
		}
		
		
	}

}

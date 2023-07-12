package fr.teamkiwi.powerrush.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class KitAnge {

	public void kitAnge(PlayerItemConsumeEvent event) {

		Player player = event.getPlayer();
		
		//DEBUG
		player.sendMessage("Kit Check");
		
		//prevent error
		if(event.getItem() != null) {
			
			//DEBUG
			player.sendMessage("Error Check");
			
			Material eventMat = event.getItem().getType();
			
			if(eventMat.equals(Material.GOLDEN_APPLE)) {
				
				//DEBUG
				player.sendMessage("Material Check");
				
				
				PotionEffect potionEffect = new PotionEffect(PotionEffectType.REGENERATION, 4, 2, false, false);
				
				player.addPotionEffect(potionEffect);
				
			}
			
		}
		
		
	}

}

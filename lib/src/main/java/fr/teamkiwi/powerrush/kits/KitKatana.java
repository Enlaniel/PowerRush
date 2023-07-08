package fr.teamkiwi.powerrush.kits;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

public class KitKatana {
	
	/*
	 * Katana kit:
	 * If the player have a sword in hand, break block 
	 * 
	 * 
	 * 
	 */
	
	public void kitKatana(PlayerInteractEvent event) {
		
		//prevent error
		if(event.getClickedBlock() != null && event.getItem() != null) {
			
			Material eventMat = event.getItem().getType();
			
			if(eventMat.equals(Material.DIAMOND_SWORD) || eventMat.equals(Material.IRON_SWORD)) {
				
				event.getClickedBlock().setType(Material.AIR);
				
			}
			
		}
		
		
	}
	

}

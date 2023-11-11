package fr.teamkiwi.powerrush.kits;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.md_5.bungee.api.ChatColor;

public class KitSangsue implements Listener {
	
	public void kitSangsue(EntityDamageByEntityEvent event) {
		
		Entity damager = event.getDamager();
		
		if(damager instanceof Player player) {
			
			if(new Random().nextInt(1, 100) <= 20) {
				
				int regen = 0;
				
				if(player.getHealth() + event.getFinalDamage() > player.getMaxHealth()) {
					regen = (int) (player.getMaxHealth() - player.getHealth());
					
					player.setHealth(player.getMaxHealth());
				
				}else {
					player.setHealth(player.getHealth() + event.getFinalDamage());
					
					regen = (int) event.getFinalDamage();
				
				}
				
				player.sendMessage(ChatColor.GREEN + "Vous avez ete regenere de " + regen + " par le kit " + ChatColor.GOLD + " Sangsue");
				
			}
		}
		
	}

}

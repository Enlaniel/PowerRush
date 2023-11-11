package fr.teamkiwi.powerrush.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.kits.KitSangsue;

public class OnInteractAtEntity implements Listener {

	Main plugin;
	
	public OnInteractAtEntity(Main main) {
		plugin = main;
	}
	
	
	@EventHandler
	public void onInteractAtEntity(EntityDamageByEntityEvent event) {
		
		Entity entity = event.getDamager();
		
		if(entity instanceof Player) {
			
			if(plugin.getConfig().getList("kits.sangsue").contains(entity.getName())) {
				
				new KitSangsue().kitSangsue(event);
				
			}
		}
		
		
		
	}
	

}

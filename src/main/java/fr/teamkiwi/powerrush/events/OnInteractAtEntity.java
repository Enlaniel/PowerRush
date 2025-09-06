package fr.teamkiwi.powerrush.events;

import fr.teamkiwi.powerrush.Game;
import fr.teamkiwi.powerrush.utils.Kit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.kits.KitSangsue;

public class OnInteractAtEntity implements Listener {

	@EventHandler
	public void onInteractAtEntity(EntityDamageByEntityEvent event) {
		
		Entity entity = event.getDamager();
		
		if(entity instanceof Player) {

			Game game = Game.getPlayerGame((Player) entity);

			if(game == null) {
				return;
			}
			
			if(game.playerHasKit((Player) entity, Kit.Kits.SANGSUE)) {
				new KitSangsue().kitSangsue(event);
				
			}
		}
		
		
		
	}
	

}

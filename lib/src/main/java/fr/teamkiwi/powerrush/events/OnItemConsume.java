package fr.teamkiwi.powerrush.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.kits.KitAnge;

public class OnItemConsume implements Listener {
	
	Main plugin;
	
	public OnItemConsume(Main plugin) {
		this.plugin = plugin;
	}

	
	@EventHandler
	public void onPlayerConsume(PlayerItemConsumeEvent event) {
		
		Player player = event.getPlayer();
		
		//DEBUG
		player.sendMessage("Event Check");
		
		if(plugin.getConfig().getList("kits.ange").contains(player.getName())) {
			
			//DEBUG
			player.sendMessage("Kit Check");
			
			new KitAnge().kitAnge(event);
			
		}
		
	}
	
}

package fr.teamkiwi.powerrush.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.kits.KitDoppage;
import fr.teamkiwi.powerrush.kits.KitKatana;

public class OnClick implements Listener {
	
	Main plugin;
	
	
	public OnClick(Main plugin) {

		this.plugin = plugin;

	}
	
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		ItemStack clickedItem = event.getItem();
		
		
		//test if player has kit katana
		if(! (plugin.getConfig().getList("kits.katana") == null)) {
			if(plugin.getConfig().getList("kits.katana").contains(player.getName())) {
				
				new KitKatana().kitKatana(event);
			}
		
		}
		if(clickedItem != null) {
			
			if(! (plugin.getConfig().getList("kits.doppage") == null)) {
				if(plugin.getConfig().getList("kits.doppage").contains(player.getName())) {
					
					if(clickedItem.getType() == Material.SUGAR) {
						
						new KitDoppage().kitDoppage(event);
					}
						
				}
			}
		}
		
		
	}
	
}

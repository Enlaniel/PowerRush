package fr.teamkiwi.powerrush.events;

import fr.teamkiwi.powerrush.Game;
import fr.teamkiwi.powerrush.utils.Kit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.kits.KitDoppage;
import fr.teamkiwi.powerrush.kits.KitKatana;
import fr.teamkiwi.powerrush.kits.KitTrader;

public class OnClick implements Listener {

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		ItemStack clickedItem = event.getItem();

		Game game = Game.getPlayerGame(player);
		if(game == null) {
			return;
		}
		if(! game.hasStarted()) {
			return;
		}

		
		//test if player has kit katana
		if(game.playerHasKit(player, Kit.Kits.KATANA)) {
			new KitKatana().kitKatana(event);
		
		}

		if(clickedItem != null) {

			if(game.playerHasKit(player, Kit.Kits.DOPPAGE)) {
				if(clickedItem.getType() == Material.SUGAR) {

					new KitDoppage().kitDoppage(event);
				}

			}

			if(game.playerHasKit(player, Kit.Kits.TRADER)) {
				if(clickedItem.getType() == Material.EMERALD) {

					new KitTrader().kitTrader(event);
				}

			}
			
		}
		
		
	}
	
}

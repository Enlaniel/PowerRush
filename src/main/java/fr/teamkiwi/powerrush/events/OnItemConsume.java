package fr.teamkiwi.powerrush.events;

import fr.teamkiwi.powerrush.Game;
import fr.teamkiwi.powerrush.utils.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.kits.KitAnge;

public class OnItemConsume implements Listener {

	@EventHandler
	public void onPlayerConsume(PlayerItemConsumeEvent event) {
		
		Player player = event.getPlayer();

		Game game = Game.getPlayerGame(player);

		if(game == null) {
			return;
		}
		
		if(game.playerHasKit(player, Kit.Kits.ANGE)) {
			new KitAnge().kitAnge(event);
			
		}
		
	}
	
}

package fr.teamkiwi.powerrush.kits;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.teamkiwi.powerrush.utils.Kit;
import fr.teamkiwi.powerrush.utils.Targeter;

public class KitTrader implements Listener {

	public void kitTrader(PlayerInteractEvent event) {
		
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		Location playerLoc = player.getLocation();
		Entity target = Targeter.getTargetEntity(player);

		Game game = Game.getPlayerGame(player);

		if(game == null) {
			return;
		}

		if(! game.playerHasKit(player, Kit.Kits.TRADER)) {
			return;
		}

		Kit trader = game.getPlayerKitByType(player, Kit.Kits.TRADER);

		if(! trader.inCooldown()) {
			
			if(target != null && block == null) {
				player.teleport(target.getLocation());
				target.teleport(playerLoc);
				
				player.sendMessage(ChatColor.AQUA + "Vous avez echange votre position avec " + ChatColor.GOLD + target.getName());
				target.sendMessage(ChatColor.AQUA + "Vous avez echange votre position avec " + ChatColor.GOLD + player.getName());
				
				trader.resetCooldown();
				
			} else {
				player.sendMessage(ChatColor.RED + "Vous ne ciblez aucune entite");
			}
			
		}else {
			player.sendMessage(ChatColor.AQUA + "Kit Trader pret dans " + ChatColor.RED + trader.getCooldown() + "s");
		}
		
		
		
	}

}

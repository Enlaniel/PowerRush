package fr.teamkiwi.powerrush.kits;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.teamkiwi.powerrush.utils.Kit;

public class KitDoppage {
	

	public void kitDoppage(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		//Objective objectiveDoppage = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Doppage");
		PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 20*15, 4, true, false);

		Game game = Game.getPlayerGame(player);

		if(game == null) {
			return;
		}

		if(! game.playerHasKit(player, Kit.Kits.DOPPAGE)) {
			return;
		}

		Kit doppage = game.getPlayerKitByType(player, Kit.Kits.DOPPAGE);


		if(! doppage.inCooldown()) {
			
			player.addPotionEffect(speed);
			doppage.resetCooldown();
			
		} else {
			player.sendMessage(ChatColor.AQUA + "Kit Trader pret dans " + ChatColor.RED + doppage.getCooldown() + "s");
		}
		
		
		
	}
	

}

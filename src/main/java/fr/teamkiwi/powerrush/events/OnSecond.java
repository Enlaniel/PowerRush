package fr.teamkiwi.powerrush.events;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import fr.teamkiwi.powerrush.utils.Kit;

public class OnSecond implements Runnable {


	@Override
	public void run() {
		for (Game game : Game.getAllGames()) {

			if (game.hasStarted()) {

				for (Player player : game.getAllPlayers()) {

					// add one to all kits timer
					for (Kit aKit : game.getPlayerKits(player)) {

						if (aKit.getType().hasCooldown()) {

							//special case
							if (aKit.getType().equals(Kit.Kits.ANGE)) {
								if (player.isSneaking()) {
									aKit.minusCooldown();
								} else {
									aKit.resetCooldown();
								}


							} else {
								aKit.minusCooldown();
							}

							if (aKit.getCooldown() == 0) {
								Bukkit.getLogger().info(""+aKit.getCooldown());
								player.sendMessage(ChatColor.AQUA + "Capacite " + ChatColor.GOLD + aKit.getType().getName() + ChatColor.AQUA + " rechargee");
							}

						}
					}

				}

				// if game is not started
			} else {
				for (Player aPlayer : Bukkit.getOnlinePlayers()) {
					aPlayer.setFoodLevel(20);
				}
			}

		}
	}

}

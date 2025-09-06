package fr.teamkiwi.powerrush.commands;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStart implements CommandExecutor {

	public static boolean isStarted = false;
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//TODO: dipatch player and give inv, deop, give kits item

		if (sender instanceof Player) {

			Game game = Game.getPlayerGame(((Player) sender).getPlayer());
			if (game == null) {
				sender.sendMessage("Vous n'etes pas dans une partie !");
			} else {
				game.start();
			}
		}
		return false;
	}



}

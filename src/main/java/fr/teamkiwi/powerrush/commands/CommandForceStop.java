package fr.teamkiwi.powerrush.commands;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public class CommandForceStop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//search by id
		if(args.length == 1) {
			try {
				int id = Integer.parseInt(args[0]);
				Game game = Game.getGameByID(id);
				if(game == null) {
					sender.sendMessage("La partie que vous cherchez n'existe pas");
				} else {
					game.stop();
				}

			} catch (NumberFormatException e) {
				sender.sendMessage("Veuillez mettre un id de partie en argument");
			}

		} else {
			//search by player
			if (sender instanceof Player) {

				Game game = Game.getPlayerGame(((Player) sender).getPlayer());
				if (game == null) {
					sender.sendMessage("Vous n'etes pas dans une partie !");
				} else {
					game.stop();
				}
			}
		}

		return false;
	}

}

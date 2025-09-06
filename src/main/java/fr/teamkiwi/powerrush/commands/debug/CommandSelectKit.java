package fr.teamkiwi.powerrush.commands.debug;

import java.util.List;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.utils.Kit;

public class CommandSelectKit implements CommandExecutor {

	Main plugin;
	
	public CommandSelectKit(Main plugin) {
		this.plugin = plugin;
	}
	
	
	final String separation = " " + ChatColor.DARK_AQUA + "|" + ChatColor.RESET + " ";
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//TODO compléter toutes les possibilité de choix de kit
		List<Kit.Kits> allKits = Kit.Kits.getAsList();
		
		//check if sender is a player
		if(sender instanceof Player) {
			Player player = (Player) sender;

			Game game = Game.getPlayerGame(player);
			if(game == null) {
				sender.sendMessage("Vous n'etes pas dans une partie !");
				return false;
			}
			
			//create string message that will be send if error
			String message = "";
			for(Kit.Kits aKit : allKits) {
				message = message + ChatColor.AQUA + aKit.getName() + separation;
			}
			player.sendMessage(message);
			
			//check if wrong parameter
			if(args.length == 0 || args.length > 1) {
				player.sendMessage(ChatColor.RED + "Usage: /selectkit <kit | help>");
				
			//case help
			} else if(args[0].equals("help")){
				player.sendMessage(ChatColor.GOLD + "Voici tous les kits que vous pouvez choisir:");
				player.sendMessage(message);
				
				
			}
			//check argument if args has an argument
			if(args.length == 1 && !(args[0].equals("help"))) {
				
				Kit.Kits choosenKit = null;
				
				for(Kit.Kits aKit : allKits) {
					
					if(aKit.getName().equalsIgnoreCase(args[0])) {
						choosenKit = aKit;
					}
				}
				
				//check if a kit is choosen
				if(choosenKit != null) {

					//if player has kit, remove it. else add kit to player
					if(game.playerHasKit(player, choosenKit)) {
						game.removeKitToPlayer(player, choosenKit);
						player.sendMessage(ChatColor.RED + "Vous venez de vous enlever le kit " + ChatColor.AQUA + choosenKit);
					} else {
						game.addPlayerKit(new Kit(choosenKit, player));
						player.sendMessage(ChatColor.GREEN + "Vous venez de vous rajouter le kit " + ChatColor.AQUA + choosenKit);

					}
					
				} else {
				
					player.sendMessage(ChatColor.GOLD + "Voici tous les kits que vous pouvez choisir:");
					player.sendMessage(ChatColor.AQUA + message);
				}
						
			}
			
		}
		
		return false;
	}

}

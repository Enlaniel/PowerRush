package fr.teamkiwi.powerrush.commands;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CommandShowInv implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//check if sender is in game
		if(sender instanceof Player) {
			Player player = (Player) sender;

			Game game = Game.getPlayerGame(player);
			if(game == null) {
				sender.sendMessage("Vous n'etes pas dans une partie");
				return false;
			}
			
			//create showinv
			Inventory showInv = Bukkit.createInventory(null, 9*5, ChatColor.DARK_GRAY + "Inventaire de Depart");
			
			//set up the content of the show inv
			showInv.setContents(game.getInventoryOnStartContent());
			if(game.getInventoryOnStartArmor() != null) {
				showInv.setItem(9*4, game.getInventoryOnStartArmor()[0]);
				showInv.setItem(9*4 + 1, game.getInventoryOnStartArmor()[1]);
				showInv.setItem(9*4 + 2, game.getInventoryOnStartArmor()[2]);
				showInv.setItem(9*4 + 3, game.getInventoryOnStartArmor()[3]);
			}
			
			
			//open inv
			player.openInventory(showInv);
			
			
		}
		
		
		
		return false;
	}

}

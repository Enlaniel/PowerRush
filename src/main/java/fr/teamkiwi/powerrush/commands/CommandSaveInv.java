package fr.teamkiwi.powerrush.commands;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.teamkiwi.powerrush.events.OnClickInventory;

public class CommandSaveInv implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//check if the sender is a Player
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Game game = Game.getPlayerGame(player);
			if(game == null) {
				sender.sendMessage("Vous n'etes pas dans une partie");
				return false;
			}
			
			//check if game is started
			if(!game.hasStarted() && player.getInventory().getContents()[0] != null && args.length == 0) {

				game.setStartInventory(player.getInventory().getContents(), player.getInventory().getArmorContents());
				
				player.sendMessage(OnClickInventory.consoleSender + ChatColor.AQUA + "Votre inventaire a bien ete sauvegarde !");
				
				
				player.getInventory().clear();
				player.getInventory().setArmorContents(null);
				
			} else {
				player.sendMessage(OnClickInventory.consoleSender + ChatColor.AQUA + "L'inventaire par defaut a ete reset");
				
				game.setDefaultInventory();
			}
			
		}
		
		return false;
	}
	

	

}

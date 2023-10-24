package fr.teamkiwi.powerrush.commands;

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
		if(sender instanceof Player player) {
			
			//create showinv
			Inventory showInv = Bukkit.createInventory(null, 9*5, ChatColor.DARK_GRAY + "Inventaire de Depart");
			
			//set up the content of the show inv
			showInv.setContents(CommandSaveInv.inventoryOnStartContent);
			if(CommandSaveInv.inventoryOnStartArmor != null) {
				showInv.setItem(9*4, CommandSaveInv.inventoryOnStartArmor[0]);
				showInv.setItem(9*4 + 1, CommandSaveInv.inventoryOnStartArmor[1]);
				showInv.setItem(9*4 + 2, CommandSaveInv.inventoryOnStartArmor[2]);
				showInv.setItem(9*4 + 3, CommandSaveInv.inventoryOnStartArmor[3]);
			}
			
			
			//open inv
			player.openInventory(showInv);
			
			
		}
		
		
		
		return false;
	}

}

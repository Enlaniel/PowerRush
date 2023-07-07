package fr.teamkiwi.powerrush.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandSaveInv implements CommandExecutor {

	
	static ItemStack[] inventoryOnStartContent;
	static ItemStack[] inventoryOnStartArmor;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player player) {
			
			inventoryOnStartContent = player.getInventory().getContents();
			inventoryOnStartArmor = player.getInventory().getArmorContents();
			
		}
		
		
		
		return false;
	}

}

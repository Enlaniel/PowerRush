package fr.teamkiwi.powerrush.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.teamkiwi.powerrush.events.OnClickInventory;

public class CommandSaveInv implements CommandExecutor {

	
	static ItemStack[] inventoryOnStartContent;
	static ItemStack[] inventoryOnStartArmor;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player player) {
			
			inventoryOnStartContent = player.getInventory().getContents();
			inventoryOnStartArmor = player.getInventory().getArmorContents();
			
			player.sendMessage(OnClickInventory.consoleSender + ChatColor.AQUA + "Votre inventaire a bien ete sauvegarde !");
			
			player.getInventory().clear();
			player.getInventory().setArmorContents(null);
			
		}
		
		
		
		return false;
	}

}

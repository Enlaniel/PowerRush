package fr.teamkiwi.powerrush.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class CommandConfig implements CommandExecutor {

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player player) {

			Inventory config = Bukkit.createInventory(null, 9*5);
			ItemStack[] configList = {};

			ItemStack glassPane = new ItemStack(Material.MAGMA_CREAM);

			for (int i = 1; i <= 9; i++){
				configList[i] = glassPane;
			}

			config.setContents(configList);

			player.openInventory(config);

		}


		return false;
	}

}

//
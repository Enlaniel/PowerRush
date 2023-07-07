package fr.teamkiwi.powerrush.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CommandConfig implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player player) {

			Inventory config = Bukkit.createInventory(null, 9*5);
			List<ItemStack> configList = new ArrayList<ItemStack>();

			ItemStack glassPane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);

			for (int i = 0; i <= 9; i++){
				configList.add(glassPane);
			}

			config.setContents((ItemStack[]) configList.toArray());

			player.openInventory(config);

		}


		return false;
	}

}

//
package fr.teamkiwi.powerrush.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class CommandConfig implements CommandExecutor {

	/**
	 * Open Config Menu
	 * 
	 * @author Enlaniel
	 * 
	 */

	//create inventory
	public Inventory config = Bukkit.createInventory(null, 9*5, ChatColor.DARK_PURPLE + "Config Menu");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//check if it is a player who send command
		if(sender instanceof Player player) {

			//create itemStack list
			ItemStack[] configList = new ItemStack[9*5];
			
			
			//create all materials needed
			ItemStack glassPane = new ItemStack(Material.STAINED_GLASS_PANE);
			ItemStack barrierBlock = new ItemStack(Material.BARRIER);
			ItemStack grassBlock = new ItemStack(Material.GRASS);
			ItemStack commandBlock = new ItemStack(Material.COMMAND);
			ItemStack armor_stand = new ItemStack(Material.ARMOR_STAND);
			ItemStack bookAndQuill = new ItemStack(Material.WRITTEN_BOOK);
			
			ItemMeta name = barrierBlock.getItemMeta();
			name.setDisplayName("Bordure");
			barrierBlock.setItemMeta(name);
			
			name = grassBlock.getItemMeta();
			name.setDisplayName("Map");
			grassBlock.setItemMeta(name);
			
			name = commandBlock.getItemMeta();
			name.setDisplayName("Mode de jeu");
			commandBlock.setItemMeta(name);
			
			name = armor_stand.getItemMeta();
			name.setDisplayName("Max Joueur");
			armor_stand.setItemMeta(name);
			
			name = bookAndQuill.getItemMeta();
			name.setDisplayName("On/Off Kits");
			bookAndQuill.setItemMeta(name);
			
			//set in the correct order items
			configList[(9*1)+2] = barrierBlock;
			configList[(9*1)+6] = grassBlock;
			configList[(9*2)+4] = commandBlock;
			configList[(9*3)+6] = armor_stand;
			configList[(9*3)+2] = bookAndQuill;
			configList[9*1] = glassPane;
			configList[9*2] = glassPane;
			configList[9*3] = glassPane;
			configList[(9*2)-1] = glassPane;
			configList[(9*3)-1] = glassPane;
			
			
			//set glass pane easely
			for (int i = 0; i <= 9; i++){
				configList[i] = glassPane;
			}
			//set glass pane easely
			for (int i = (9*4)-1; i <= (9*5)-1; i++){
				configList[i] = glassPane;
			}
			
			//set contents
			config.setContents(configList);
			//open the inventory of a player
			player.openInventory(config);

		}


		return false;
	}

}

//cool
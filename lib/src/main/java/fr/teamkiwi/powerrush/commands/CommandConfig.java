package fr.teamkiwi.powerrush.commands;

import java.util.ArrayList;
import java.util.List;

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

import fr.teamkiwi.powerrush.Main;


public class CommandConfig implements CommandExecutor {

	/**
	 * Open Config Menu
	 * 
	 * @author Enlaniel
	 * 
	 */

	
	Main plugin;
    
    public CommandConfig(Main plugin) {
		this.plugin = plugin;
	}
	
	
	
	
	//create inventory
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//check if it is a player who send command
		if(sender instanceof Player player) {
			
			//check if game is started
			if(! CommandStart.isStarted) {
				
				Inventory config = Bukkit.createInventory(null, 9*5, ChatColor.DARK_PURPLE + "Config Menu");
				//create itemStack list
				ItemStack[] configList = new ItemStack[9*5];
				
				
				//create all materials needed
				ItemStack glassPane = new ItemStack(Material.STAINED_GLASS_PANE);
				ItemStack barrierBlock = new ItemStack(Material.BARRIER);
				ItemStack grassBlock = new ItemStack(Material.GRASS);
				ItemStack commandBlock = new ItemStack(Material.COMMAND);
				ItemStack armor_stand = new ItemStack(Material.ARMOR_STAND);
				ItemStack bookAndQuill = new ItemStack(Material.WRITTEN_BOOK);
				
				//set all meta data
				ItemMeta name = barrierBlock.getItemMeta();
				List<String> lore = new ArrayList<>();
				name.setDisplayName("Bordure");
				barrierBlock.setItemMeta(name);
				
				name.setDisplayName("Map");
				grassBlock.setItemMeta(name);
				
				name.setDisplayName("Mode de jeu");
				lore.clear();
				lore.add(ChatColor.AQUA + plugin.getConfig().getString("config.modedejeu"));
				lore.add(ChatColor.AQUA + String.valueOf(plugin.getConfig().getInt("config." + plugin.getConfig().getString("config.modedejeu").toLowerCase())));
				name.setLore(lore);
				commandBlock.setItemMeta(name);
				
				lore.clear();
				lore.add(ChatColor.AQUA + "Max Joueurs: " + plugin.getConfig().getInt("config.maxPlayers"));
				name.setLore(lore);
				name.setDisplayName("Max Joueurs");
				armor_stand.setItemMeta(name);
				
				
				name.setDisplayName("On/Off Kits");
				lore.clear();
				lore.add("Nombre de kits ban:" + 5);
				name.setLore(lore);
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
			
		}


		return false;
	}

}

//cool
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
				ItemStack bookAndQuill = new ItemStack(Material.BOOK_AND_QUILL);
				
				//set all meta data
				ItemMeta name = barrierBlock.getItemMeta();
				List<String> lore = new ArrayList<>();
				
				name.setDisplayName(ChatColor.DARK_PURPLE + "Bordure");
				lore.clear();
				lore.add(ChatColor.AQUA + "WorldBorder: " + ChatColor.GOLD + (Bukkit.getWorld("world").getWorldBorder().getSize()/2));
				name.setLore(lore);
				barrierBlock.setItemMeta(name);
				
				
				name.setDisplayName(ChatColor.DARK_PURPLE + "Map");
				lore.clear();
				name.setLore(lore);
				grassBlock.setItemMeta(name);
				
				
				name.setDisplayName(ChatColor.DARK_PURPLE + "Mode de jeu");
				lore.clear();
				lore.add(ChatColor.AQUA + plugin.getConfig().getString("config.modedejeu"));
				lore.add(ChatColor.GOLD + String.valueOf(plugin.getConfig().getInt("config." + plugin.getConfig().getString("config.modedejeu").toLowerCase())));
				name.setLore(lore);
				commandBlock.setItemMeta(name);
				
				
				name.setDisplayName(ChatColor.DARK_PURPLE + "Max Joueurs");
				lore.clear();
				lore.add(ChatColor.AQUA + "Max Joueurs: " + ChatColor.GOLD + plugin.getConfig().getInt("config.maxPlayers"));
				name.setLore(lore);
				armor_stand.setItemMeta(name);
				
				
				name.setDisplayName(ChatColor.DARK_PURPLE + "On/Off Kits");
				lore.clear();
				lore.add(ChatColor.AQUA + "Nombre de kits ban: " + ChatColor.GOLD + plugin.getConfig().getList("config.bannedKits").size());
				name.setLore(lore);
				bookAndQuill.setItemMeta(name);
				
				name.setDisplayName(" ");
				lore.clear();
				name.setLore(lore);
				glassPane.setItemMeta(name);
				
				
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
				
				
				//set glass pane easily
				for (int i = 0; i <= 9; i++){
					configList[i] = glassPane;
				}
				//set glass pane easily
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

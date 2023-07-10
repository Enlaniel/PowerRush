package fr.teamkiwi.powerrush.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.teamkiwi.powerrush.Main;

public class CommandStart implements CommandExecutor {

	public static boolean isStarted = false;
	public static List<UUID> allPlayersInGame = new ArrayList<>(); 
	
	Main plugin;
	
	public CommandStart(Main plugin) {
		this.plugin = plugin;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//TODO: dipatch player and give inv, deop, open kits choice ...
		
		isStarted = true;
		
		//get all online players (spec exeption ??)
		for(Player aPlayer : Bukkit.getOnlinePlayers()) {
			
			//si l'host n'a pas fait /saveinv, donner un inventaire default
			if(CommandSaveInv.inventoryOnStartContent == null) {
				
				new CommandSaveInv().setDefaultInventory();
			}
			
			//add player to allPlayersInGame
			allPlayersInGame.add(aPlayer.getUniqueId());
			
			//set inventory
			aPlayer.getInventory().setArmorContents(CommandSaveInv.inventoryOnStartArmor);
			aPlayer.getInventory().setContents(CommandSaveInv.inventoryOnStartContent);
			
			
			//check mode de jeu
			switch(plugin.getConfig().getString("config.modedejeu")) {
			
			case "Random":
				
				setRandom(aPlayer);
				break;
				
				
			case "Classique":
				break;
				
				
			case "Apocalypse":
				break;
				
				
			default:
				break;
				
			
			}
			
			
			
		}
		
		
		return false;
	}

	
	/*
	 * Desciption:
	 * Set les kits dans le mode de jeu random 
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	private void setRandom(Player player) {
		
		Random random = new Random();
		List<String> allKits = (List<String>) plugin.getConfig().getList("kits.allkits");
		
		//if choosen random config is > than the number of kits
		if(allKits.size() < plugin.getConfig().getInt("config.random")) {
			plugin.getConfig().set("config.random", allKits.size());
		}
		
		for(int i = 0; i < plugin.getConfig().getInt("config.random"); i++ ) {
			
			String randomKit = allKits.get(random.nextInt(allKits.size()));
			List<String> randomKitList = (List<String>) plugin.getConfig().getList("kits." + randomKit.toLowerCase());
			
			//if player does not have the random kit, set it
			if(! randomKitList.contains(player.getName())){
				randomKitList.add(player.getName());
				plugin.getConfig().set("kits." + randomKit.toLowerCase(), randomKitList);
				
				player.sendMessage(ChatColor.AQUA + "Vous venez de recevoir le kit " + ChatColor.GOLD + randomKit);
			
			//else restart the for loop
			}else {
				i--;
			}
			
		}
		
		
	}

}

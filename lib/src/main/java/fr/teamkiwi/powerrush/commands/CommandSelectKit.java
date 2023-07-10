package fr.teamkiwi.powerrush.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.teamkiwi.powerrush.Main;

public class CommandSelectKit implements CommandExecutor {

	Main plugin;
	
	public CommandSelectKit(Main plugin) {
		this.plugin = plugin;
	}
	
	
	final String separation = " " + ChatColor.DARK_AQUA + "|" + ChatColor.RESET + " ";
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//TODO compléter toutes les possibilité de choix de kit
		
		
		//check if sender is a player
		if(sender instanceof Player player) {
			
			//check if wrong parameter
			if(args.length == 0 || args.length > 1) {
				player.sendMessage(ChatColor.RED + "Usage: /selectkit <kit | help>");
				
			//case help
			}else if(args[0].equals("help")){
				player.sendMessage(ChatColor.GOLD + "Voici tous les kits que vous pouvez choisir:");
				player.sendMessage(ChatColor.AQUA + "katana" + separation + "");
				
			}
			//check argument if args has an argument
			if(args.length == 1 && ! (args[0].equals("help"))) {
				
				String choosenKit = "";
				
				
				switch (args[0]) {
					
				case "katana":
					choosenKit = args[0];
					break;
				
					
				default:
					player.sendMessage(ChatColor.GOLD + "Voici tous les kits que vous pouvez choisir:");
					player.sendMessage(ChatColor.AQUA + "katana" + separation + "");
				
				}
				
				//check if a kit is choosen
				if(! choosenKit.equals("")) {
					
					//get config.yml
					@SuppressWarnings("unchecked")
					List<String> kitConfigList = (List<String>) plugin.getConfig().getList("kits." + choosenKit);
					
					//if player has kit, remove it. else add kit to player
					if(kitConfigList.contains(player.getName())) {
						kitConfigList.remove(player.getName());
						player.sendMessage(ChatColor.RED + "Vous venez de vous enlever le kit " + ChatColor.AQUA + choosenKit);
						
					}else {
						kitConfigList.add(player.getName());
						player.sendMessage(ChatColor.GREEN + "Vous venez de vous rajouter le kit " + ChatColor.AQUA + choosenKit);
					}
					//apply
					plugin.getConfig().set("kits." + choosenKit, kitConfigList);
					
				}
				
				
						
			}
			
		}
		
		return false;
	}

}

package fr.teamkiwi.powerrush.commands;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.teamkiwi.powerrush.Main;

public class CommandShowYML implements CommandExecutor {

	Main plugin;
	
	public CommandShowYML(Main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Map<String, Object> allValues = plugin.getConfig().getValues(true);
		
		sender.sendMessage(ChatColor.AQUA + "Toutes les valeurs du config.yml: " + ChatColor.GOLD + allValues.toString());
		
		
		return false;
	}

}

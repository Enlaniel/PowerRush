package fr.teamkiwi.powerrush.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CommandHelp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		sender.sendMessage(ChatColor.GOLD + "Voici toutes les commandes disponible:");
		sender.sendMessage("" + ChatColor.GOLD + ChatColor.ITALIC + "/config       " + ChatColor.AQUA + "Permet d'ouvrir le menu de configuration");
		sender.sendMessage("" + ChatColor.GOLD + ChatColor.ITALIC + "/saveinv     " + ChatColor.AQUA + "Permet de sauvegarder l'inventaire de depart");
		sender.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD +"/showinv  " + ChatColor.AQUA + "Permet de voir l'inventaire de depart");
		sender.sendMessage("" + ChatColor.GOLD + ChatColor.ITALIC + "/start        " + ChatColor.AQUA + "Permet de lancer la partie");
		
		
		return false;
	}

}

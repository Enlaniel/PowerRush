package fr.teamkiwi.powerrush.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStart implements CommandExecutor {

	static boolean isStarted = false;
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//TODO: dipatch player and give inv, deop, open kis choice ...
		
		//get all online players (spec exeption ??)
		for(Player aPlayer : Bukkit.getOnlinePlayers()) {
			
			//si l'host n'a pas fait /saveinv, donner un inventaire default
			if(CommandSaveInv.inventoryOnStartContent.length == 0) {
				
				new CommandSaveInv().setDefaultInventory();
				
			}
			
			//set inventory
			aPlayer.getInventory().setArmorContents(CommandSaveInv.inventoryOnStartArmor);
			aPlayer.getInventory().setContents(CommandSaveInv.inventoryOnStartContent);
			
		}
		
		
		return false;
	}

}

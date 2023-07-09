package fr.teamkiwi.powerrush.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStart implements CommandExecutor {

	public static boolean isStarted = false;
	public static List<UUID> allPlayersInGame = new ArrayList<>(); 
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//TODO: dipatch player and give inv, deop, open kis choice ...
		
		isStarted = true;
		
		//get all online players (spec exeption ??)
		for(Player aPlayer : Bukkit.getOnlinePlayers()) {
			
			//si l'host n'a pas fait /saveinv, donner un inventaire default
			if(CommandSaveInv.inventoryOnStartContent.length == 0) {
				
				new CommandSaveInv().setDefaultInventory();
			}
			
			//add player to allPlayersInGame
			allPlayersInGame.add(aPlayer.getUniqueId());
			
			//set inventory
			aPlayer.getInventory().setArmorContents(CommandSaveInv.inventoryOnStartArmor);
			aPlayer.getInventory().setContents(CommandSaveInv.inventoryOnStartContent);
			
			
			
		}
		
		
		return false;
	}

}

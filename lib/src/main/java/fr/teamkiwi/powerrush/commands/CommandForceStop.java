package fr.teamkiwi.powerrush.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.teamkiwi.powerrush.Main;
import fr.teamkiwi.powerrush.events.OnClickInventory;

public class CommandForceStop implements CommandExecutor {

	Main plugin;
	
	public CommandForceStop(Main plugin) {
		this.plugin = plugin;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//TODO: spawn with config
		
		
		//reset parameter
		CommandStart.allPlayersInGame.clear();
		CommandStart.isStarted = false;
		CommandSaveInv.inventoryOnStartArmor = null;
		CommandSaveInv.inventoryOnStartContent = null;
		
		
		
		//reset all kits
		@SuppressWarnings("unchecked")
		List<String> allKits = (List<String>) plugin.getConfig().getList("kits.allkits");
		
		for(String aKit : allKits) {
			plugin.getConfig().set("kits." + aKit.toLowerCase(), new ArrayList<String>());
		}
		
		
		
		for(Player aPlayer : Bukkit.getOnlinePlayers()) {
			
			//reset players
			aPlayer.getInventory().clear();
			aPlayer.getInventory().setArmorContents(null);
			aPlayer.teleport(new Location(Bukkit.getWorld("world"), 0, 65, 0));
			aPlayer.setGameMode(GameMode.SURVIVAL);
			
			aPlayer.sendMessage(OnClickInventory.consoleSender + " La partie a bien ete arretee");
			
		}
		
		
		return false;
	}

}

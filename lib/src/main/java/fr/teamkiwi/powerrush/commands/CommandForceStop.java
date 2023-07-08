package fr.teamkiwi.powerrush.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandForceStop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//TODO: spawn with config
		
		
		//reset parameter
		CommandStart.allPlayersInGame.clear();
		CommandStart.isStarted = false;
		
		
		for(Player aPlayer : Bukkit.getOnlinePlayers()) {
			
			//reset players
			aPlayer.getInventory().clear();
			aPlayer.getInventory().setArmorContents(null);
			aPlayer.teleport(new Location(Bukkit.getWorld("world"), 0, 65, 0));
			aPlayer.setGameMode(GameMode.SURVIVAL);
			
		}
		
		
		return false;
	}

}

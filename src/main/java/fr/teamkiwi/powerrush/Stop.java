package fr.teamkiwi.powerrush;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.teamkiwi.powerrush.commands.CommandStart;
import fr.teamkiwi.powerrush.events.OnClickInventory;
import fr.teamkiwi.powerrush.utils.Kit;

public class Stop {
	
	Main plugin = Main.getPlugin(Main.class);
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void stop() {
		
		
		//TODO: spawn with config
		
		
		//reset parameter
		CommandStart.allPlayersInGame.clear();
		CommandStart.isStarted = false;
		
		Location spawn = new Location(Bukkit.getWorld("world"), 0, 101, 0);
		
		
		//reset all kits
		List<Kit> allKits = CommandInitServer.allKits;
		List<String> debugList = new ArrayList<String>();
		
		for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			Bukkit.getScoreboardManager().getMainScoreboard().resetScores(player);
		}
		
		for(Kit aKit : allKits) {
			debugList = (List<String>) plugin.getConfig().getList("kits." + aKit.getName().toLowerCase());
			
			debugList.clear();
			plugin.getConfig().set("kits." + aKit.getName().toLowerCase(), debugList);
		}
		
		
		for(Player aPlayer : Bukkit.getOnlinePlayers()) {
			
			//reset players
			aPlayer.getInventory().clear();
			aPlayer.getInventory().setArmorContents(null);
			aPlayer.teleport(spawn);
			aPlayer.setGameMode(GameMode.SURVIVAL);
			aPlayer.setHealth(aPlayer.getMaxHealth());
			aPlayer.setFoodLevel(20);
			Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Points").getScore(aPlayer).setScore(plugin.getConfig().getInt("config.classique"));
			Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Round").getScore(aPlayer).setScore(0);
			
			aPlayer.sendMessage(OnClickInventory.consoleSender + "La partie a bien ete arretee");
			
		}
		
		
	}
	

}

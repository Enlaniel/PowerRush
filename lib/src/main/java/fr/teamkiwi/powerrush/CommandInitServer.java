package fr.teamkiwi.powerrush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandInitServer implements CommandExecutor {

	Main plugin;
	
	public CommandInitServer(Main plugin) {
		this.plugin = plugin;
	}
	
	
	public static Map<String, Material> allKitsMaterial = new HashMap<String, Material>();
	public static Map<String, Integer> allKitsCost = new HashMap<String, Integer>();
	public static List<String> allKits = new ArrayList<>();
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		plugin.getConfig().set("config.bannedKits", new ArrayList<>());
		plugin.getConfig().set("config.modedejeu", "Classique");
		plugin.getConfig().set("config.classique", 30);
		plugin.getConfig().set("config.random", 5);
		plugin.getConfig().set("config.maxPlayers", 30);
		
		
		if(Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Points") == null) {
			Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("Points", "dummy");
		}if(Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Round") == null) {
			Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("Round", "dummy");
		}if(Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Ange") == null) {
			Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("Ange", "dummy");
		}if(Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Doppage") == null) {
			Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("Doppage", "dummy");
		}
		
		sender.sendMessage("Le serveur a bien ete initialise");
		
		return false;
	}

	
	public void initKits() {
		
		allKitsMaterial.put("Katana", Material.DIAMOND_SWORD);
		allKitsCost.put("Katana", 4);
		allKits.add("Katana");
		allKitsMaterial.put("Ange", Material.GOLDEN_APPLE);
		allKitsCost.put("Ange", 5);
		allKits.add("Ange");
		allKitsMaterial.put("Doppage", Material.SUGAR);
		allKitsCost.put("Doppage", 3);
		allKits.add("Doppage");
		
		
		
	}

}

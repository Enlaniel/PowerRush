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
		
		Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("Points", "dummy");
		Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("Round", "dummy");
		
		sender.sendMessage("Le serveur a bien ete initialise");
		
		return false;
	}

	
	public void initKits() {
		
		allKitsMaterial.put("Katana", Material.DIAMOND_SWORD);
		allKitsCost.put("Katana", 3);
		allKits.add("Katana");
		
		
		
	}

}

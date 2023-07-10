package fr.teamkiwi.powerrush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandInitServer implements CommandExecutor {

	Main plugin;
	
	public CommandInitServer(Main plugin) {
		this.plugin = plugin;
	}
	
	
	public static Map<String, Material> allKits = new HashMap<String, Material>();
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> anEmptyList = new ArrayList<>();
		
		plugin.getConfig().set("config.bannedKits", anEmptyList);
		
		
		sender.sendMessage("Le serveur a bien ete initialise");
		
		return false;
	}

	
	public void initKits() {
		
		allKits.put("Katana", Material.DIAMOND_SWORD);
		
		
		
	}

}

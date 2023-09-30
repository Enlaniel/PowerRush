package fr.teamkiwi.powerrush;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<Kit> allKits = new ArrayList<>();
	

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
		
		Kit katana = new Kit("Katana", Material.DIAMOND_SWORD, 4, false);
		Kit ange = new Kit("Ange", Material.GOLDEN_APPLE, 5, false);
		Kit doppage = new Kit("Doppage", Material.SUGAR, 3, true);
		
		
		allKits.add(katana);
		allKits.add(ange);
		allKits.add(doppage);
		
		
	}

}

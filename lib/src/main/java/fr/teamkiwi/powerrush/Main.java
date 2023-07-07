package fr.teamkiwi.powerrush;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.teamkiwi.powerrush.commands.CommandConfig;
import fr.teamkiwi.powerrush.commands.CommandStart;


public class Main extends JavaPlugin {
	
	//Pour les logs
    public static final Logger LOGGER = Bukkit.getLogger();

    @Override
    public void onEnable() {
    	
    	LOGGER.info("Plugin lancé avec succès");
    	
    	//set up commandes
    	getCommand("config").setExecutor(new CommandConfig());
    	getCommand("start").setExecutor(new CommandStart());
    	
    }
	
    public void onDisable() {
    	
    	LOGGER.info("Plugin eteint :( ");
    	
    	
    }
    

}

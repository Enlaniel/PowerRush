package fr.teamkiwi.powerrush;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	//Pour les logs
    public static final Logger LOGGER = Bukkit.getLogger();

    @Override
    public void onEnable() {
    	
    	LOGGER.info("Plugin lancé avec succès");
    	
    }
	
    public void onDisable() {
    	
    	LOGGER.info("Plugin eteint :( ");
    	
    	
    }
    

}

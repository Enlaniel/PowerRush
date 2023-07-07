package fr.teamkiwi.powerrush;

import java.util.logging.Logger;

import fr.teamkiwi.powerrush.events.WhenPlayerJoins;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.teamkiwi.powerrush.commands.CommandConfig;
import fr.teamkiwi.powerrush.commands.CommandSaveInv;
import fr.teamkiwi.powerrush.commands.CommandStart;
import fr.teamkiwi.powerrush.events.OnClickInventory;


public class Main extends JavaPlugin {
	
	//Pour les logs
    public static final Logger LOGGER = Bukkit.getLogger();

    @Override
    public void onEnable() {
    	
    	LOGGER.info("Plugin lancé avec succès");
    	
    	//set up commandes
    	getCommand("config").setExecutor(new CommandConfig());
    	getCommand("start").setExecutor(new CommandStart());
    	getCommand("saveinventory").setExecutor(new CommandSaveInv());
    	
    	//register event
    	getServer().getPluginManager().registerEvents(new OnClickInventory(), this);
        getServer().getPluginManager().registerEvents(new WhenPlayerJoins(), this);
    	
    }
	
    public void onDisable() {
    	
    	LOGGER.info("Plugin eteint :( ");
    	
    	
    }
    

}

package fr.teamkiwi.powerrush;

import java.util.logging.Logger;

import fr.teamkiwi.powerrush.events.WhenPlayerJoins;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.teamkiwi.powerrush.commands.CommandConfig;
import fr.teamkiwi.powerrush.commands.CommandForceStop;
import fr.teamkiwi.powerrush.commands.CommandHelp;
import fr.teamkiwi.powerrush.commands.CommandSaveInv;
import fr.teamkiwi.powerrush.commands.CommandSelectKit;
import fr.teamkiwi.powerrush.commands.CommandShowInv;
import fr.teamkiwi.powerrush.commands.CommandStart;
import fr.teamkiwi.powerrush.events.OnClick;
import fr.teamkiwi.powerrush.events.OnClickInventory;


public class Main extends JavaPlugin {
	
	//Pour les logs
    public static final Logger LOGGER = Bukkit.getLogger();

    @Override
    public void onEnable() {
    	
    	//TODO: add variable spawn with config.yml in CommandForceStop
    	//TODO: dipatch player and give inv, deop, open kis choice ... in CommandStart
    	//TODO: no chat for spec in new onChat
    	//TODO: URGENT link OnClickInventory to config.yml
    	//TODO: continue mode de jeu choisses
    	
    	
    	LOGGER.info("Plugin lancé avec succès");
    	
    	//set up commandes
    	getCommand("config").setExecutor(new CommandConfig(this));
    	getCommand("start").setExecutor(new CommandStart());
    	getCommand("forcestop").setExecutor(new CommandForceStop());
    	getCommand("saveinventory").setExecutor(new CommandSaveInv());
    	getCommand("showinventory").setExecutor(new CommandShowInv());
    	getCommand("powerrushhelp").setExecutor(new CommandHelp());
    	
    	//setup debug commands
    	getCommand("selectkit").setExecutor(new CommandSelectKit(this));
    	
    	//register event
    	getServer().getPluginManager().registerEvents(new OnClickInventory(this), this);
        getServer().getPluginManager().registerEvents(new WhenPlayerJoins(this), this);
        getServer().getPluginManager().registerEvents(new OnClick(this), this);
        
    }
	
    public void onDisable() {
    	
    	LOGGER.info("Plugin eteint :( ");
    	
    	this.saveConfig();
    	
    	
    }
    

}

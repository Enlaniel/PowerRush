package fr.teamkiwi.powerrush;

import java.util.logging.Logger;

import fr.teamkiwi.powerrush.events.OnChat;
import fr.teamkiwi.powerrush.events.WhenPlayerJoins;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.teamkiwi.powerrush.commands.CommandConfig;
import fr.teamkiwi.powerrush.commands.CommandForceStop;
import fr.teamkiwi.powerrush.commands.CommandHelp;
import fr.teamkiwi.powerrush.commands.CommandSaveInv;
import fr.teamkiwi.powerrush.commands.CommandShowInv;
import fr.teamkiwi.powerrush.commands.CommandStart;
import fr.teamkiwi.powerrush.commands.debug.CommandSelectKit;
import fr.teamkiwi.powerrush.commands.debug.CommandShowYML;
import fr.teamkiwi.powerrush.events.OnClick;
import fr.teamkiwi.powerrush.events.OnClickInventory;
import fr.teamkiwi.powerrush.events.OnDead;
import fr.teamkiwi.powerrush.events.OnItemConsume;
import fr.teamkiwi.powerrush.events.OnSecond;


public class Main extends JavaPlugin {
	
	//Pour les logs
    public static final Logger LOGGER = Bukkit.getLogger();

	@Override
    public void onEnable() {
		
    	//TODO: dipatch player and give inv, deop, open kis choice ... in CommandStart
    	//TODO: create kits with help of PowerRush.txt
		//TODO: scoreboard with timer, player alive...
		//TODO: scenarios/possibility to add teams
		//TODO: setup initServer command
		
		//TODO: FIX CLASSIQUE TWO TIME SAME KIT
		//TODO: PORBLEM WITH SELECT KIT
		//TODO: BIG PROBLEM/MAIN PROBLEM WITH KITS GIVEN AND CONFIG
    	
    	LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	LOGGER.info("[PowerRush] Plugin lance avec succes");
    	LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	
    	//set up commandes
    	getCommand("config").setExecutor(new CommandConfig(this));
    	getCommand("start").setExecutor(new CommandStart(this));
    	getCommand("forcestop").setExecutor(new CommandForceStop());
    	getCommand("saveinventory").setExecutor(new CommandSaveInv());
    	getCommand("showinventory").setExecutor(new CommandShowInv());
    	getCommand("powerrushhelp").setExecutor(new CommandHelp());
    	
    	//init commande
    	getCommand("initserver").setExecutor(new CommandInitServer(this));
    	
    	//setup debug commands
    	getCommand("selectkit").setExecutor(new CommandSelectKit(this));
    	getCommand("showyml").setExecutor(new CommandShowYML(this));
    	
    	//register event
    	getServer().getPluginManager().registerEvents(new OnClickInventory(this), this);
        getServer().getPluginManager().registerEvents(new WhenPlayerJoins(this), this);
        getServer().getPluginManager().registerEvents(new OnClick(this), this);
		getServer().getPluginManager().registerEvents(new OnChat(), this);
		getServer().getPluginManager().registerEvents(new OnItemConsume(this), this);
		getServer().getPluginManager().registerEvents(new OnDead(this), this);
		getServer().getScheduler().runTaskTimer(this, new OnSecond(), 0, 1*20);
		
		new CommandInitServer(this).initKits();
        
    }
	
    public void onDisable() {
    	
    	LOGGER.info("Plugin eteint :( ");
    	
    	this.saveConfig();
    	
    	
    }
    

}

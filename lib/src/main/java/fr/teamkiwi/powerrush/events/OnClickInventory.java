package fr.teamkiwi.powerrush.events;

import fr.teamkiwi.powerrush.Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnClickInventory implements Listener {
	
    public static final String consoleSender = "" + ChatColor.LIGHT_PURPLE + ChatColor.ITALIC + "[" + ChatColor.DARK_GREEN + ChatColor.ITALIC + "POWER RUSH" + ChatColor.  LIGHT_PURPLE + ChatColor.ITALIC + "] " + ChatColor.RESET;
    public static String modeDeJeu = "Classique";



    Main plugin;
    
    public OnClickInventory(Main plugin) {
		this.plugin = plugin;
	}
    

	@EventHandler
    public void onClickInConfig(InventoryClickEvent event){
    	
        Inventory clickedInventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();
        HumanEntity player = event.getWhoClicked();
        
        if(clickedInventory != null) {
        	
	        ItemStack cobblestoneWall = new ItemStack(Material.COBBLE_WALL);
	        ItemStack acaciaFenceGate = new ItemStack(Material.ACACIA_FENCE_GATE);
	        ItemStack acaciaFence = new ItemStack(Material.ACACIA_FENCE);
	        ItemMeta name = acaciaFence.getItemMeta();
	        List<String> lore = new ArrayList<>();
	
	
	        ItemStack arrow = new ItemStack(Material.ARROW);
	        name.setDisplayName("Back");
	        arrow.setItemMeta(name);
	
	
	        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
	        wb.setCenter(0, 0);
	
	        
	        //in config menu
	        if (clickedInventory.getTitle().equals(ChatColor.DARK_PURPLE + "Config Menu")){
	
	            switch (clickedItem.getType()) {
	
	                //Config de bordure
	                case BARRIER :
	
	                    Inventory border = Bukkit.createInventory(null, 9*1, ChatColor.AQUA + "Border Menu");
	                    ItemStack[] borderList = new ItemStack[9*1];
	
	                    name.setDisplayName("Reduire de 50 blocs");
	                    acaciaFence.setItemMeta(name);
	                    
	                    name.setDisplayName("Reinitaliser a 500 blocs");
	                    cobblestoneWall.setItemMeta(name);
	                    
	                    name.setDisplayName("Augmenter de 50 blocs");
	                    acaciaFenceGate.setItemMeta(name);
	                    
	                    borderList[0] = arrow;
	                    borderList[2] = acaciaFence;
	                    borderList[4] = cobblestoneWall;
	                    borderList[6] = acaciaFenceGate;
	                    borderList[8] = arrow;
	
	                    border.setContents(borderList);
	
	                    player.openInventory(border);
	                    break;
	
	                    
	                    
	                //Config de map
	                case GRASS :
	                    break;
	
	                    
	                    
	                //Changement de mode de jeu
	                case COMMAND :
	                	
	                	Inventory modeDeJeuInv = Bukkit.createInventory(null, 9*1, ChatColor.DARK_PURPLE + "Choisissez un mode de jeu");
	                	ItemStack[] modeDeJeuList = new ItemStack[9*1];
	                	
	                	ItemStack bedrock = new ItemStack(Material.BEDROCK);
	                	ItemStack dirt = new ItemStack(Material.DIRT);
	                	ItemStack tnt = new ItemStack(Material.TNT);
	                	
	                	
	                	modeDeJeuList[0] = arrow;
	                	modeDeJeuList[2] = bedrock;
	                	modeDeJeuList[4] = dirt;
	                	modeDeJeuList[6] = tnt;
	                	modeDeJeuList[8] = arrow;
	                	
	                	name.setDisplayName("Random");
	                	lore.clear();
	                	lore.add(ChatColor.AQUA + "Permet de decouvrir les kits");
	                	name.setLore(lore);
	                	bedrock.setItemMeta(name);
	                	
	                	name.setDisplayName("Classique");
	                	lore.clear();
	                	lore.add(ChatColor.AQUA + "Choisissez entre 3 kits");
	                	name.setLore(lore);
	                	dirt.setItemMeta(name);
	                	
	                	name.setDisplayName("Apocalypse");
	                	lore.clear();
	                	lore.add(ChatColor.AQUA + "Aucune limite de kits");
	                	name.setLore(lore);
	                	tnt.setItemMeta(name);
	                	
	                	modeDeJeuInv.setContents(modeDeJeuList);
	                	
	                	player.openInventory(modeDeJeuInv);
	                	
	                    break;
	
	                    
	                    
	                //Config du nombre de joueurs
	                case ARMOR_STAND :
	
	                    Inventory maxPlayers = Bukkit.createInventory(null, 9*1, ChatColor. DARK_GREEN + "Max Players Count Menu");
	                    ItemStack[] maxPlayersList = new ItemStack[9*1];
	
	                    maxPlayersList[0] = arrow;
	                    maxPlayersList[2] = acaciaFence;
	                    maxPlayersList[4] = cobblestoneWall;
	                    maxPlayersList[6] = acaciaFenceGate;
	                    maxPlayersList[8] = arrow;
	
	                    name.setDisplayName("Augmenter de 1 joueur");
	                    acaciaFenceGate.setItemMeta(name);
	                    
	                    name.setDisplayName("Diminuer de 1 joueur");
	                    acaciaFence.setItemMeta(name);
	                    
	                    name.setDisplayName("Reinitialiser a 30 joueurs");
	                    cobblestoneWall.setItemMeta(name);
	
	                    maxPlayers.setContents(maxPlayersList);
	
	                    player.openInventory(maxPlayers);
	
	                    break;
	
	                    
	                    
	                //Config des kits et de la partie en elle-même
	                case WRITTEN_BOOK :
	                    break;
	
	                default :
	                    break;
	
	            }
	
	            event.setCancelled(true);
	        }
	
	        
	        
	        //in border config menu
	        if (clickedInventory.getTitle().equals(ChatColor.AQUA + "Border Menu")){
	
	            switch (clickedItem.getType()) {
	
	                case ACACIA_FENCE :
	
	                    wb.setSize(wb.getSize() - 100);
	                    player.sendMessage(consoleSender + "La taille de la bordure a ete reduite de " + ChatColor.RED + "50 blocs");
	                    player.sendMessage(consoleSender + "La taille de la bordure est maintenant de " + ChatColor.AQUA + wb.getSize() / 2);
	
	                    break;
	
	                case COBBLE_WALL :
	
	                    wb.setSize(1000);
	                    player.sendMessage(consoleSender + "La taille de la bordure a ete reinitialisee a " + ChatColor.AQUA + "500 blocs");
	
	                    break;
	
	                case ACACIA_FENCE_GATE :
	
	                    wb.setSize(wb.getSize() + 100);
	                    player.sendMessage(consoleSender + "La taille de la bordure a ete augmentee de " + ChatColor.GREEN + "50 blocs");
	                    player.sendMessage(consoleSender + "La taille de la bordure est maintenant de " + ChatColor.AQUA + wb.getSize() / 2);
	
	                    break;
	
	                case ARROW :
	
	                    Bukkit.dispatchCommand(player, "config");
	
	                    break;
	                default:
	                    break;
	
	            }
	
	            event.setCancelled(true);
	        }
	
	        
	        
	        //in max player count config menu
	        if (clickedInventory.getTitle().equals(ChatColor. DARK_GREEN + "Max Players Count Menu")) {
	
	            switch (clickedItem.getType()) {
	
	                case ACACIA_FENCE :
	
	                    plugin.getConfig().set("config.maxPlayers", plugin.getConfig().getInt("config.maxPlayers")-1);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete diminue de " + ChatColor.RED + "1 joueur");
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueur est maintenant de " + ChatColor.AQUA + plugin.getConfig().getInt("config.maxPlayers"));
	
	                    break;
	
	                case COBBLE_WALL :

						plugin.getConfig().set("config.maxPlayers", 30);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete reinitialise a " + ChatColor.AQUA + "30 joueurs");
	
	                    break;
	
	                case ACACIA_FENCE_GATE :

						plugin.getConfig().set("config.maxPlayers", plugin.getConfig().getInt("config.maxPlayers")+1);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete augmente de " + ChatColor.GREEN + "1 joueur");
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueur est maintenant de " + ChatColor.AQUA + plugin.getConfig().getInt("config.maxPlayers"));
	
	                    break;
	
	                case ARROW :
	
	                	Bukkit.dispatchCommand(player, "config");
	
	                    break;
	
	                default:
	                    break;
	
	            }
	
	            event.setCancelled(true);
	        }
	        
	        
	        
	        //if in mode de jeu config menu
	        if (clickedInventory.getTitle().equals(ChatColor.DARK_PURPLE + "Choisissez un mode de jeu")) {
	        	
	        	switch (clickedItem.getType()) {
	
	            case BEDROCK :
	
	            	plugin.getConfig().set("config.modedejeu", "Random");
	                player.sendMessage(consoleSender + "Le mode de jeu actuel est " + ChatColor.AQUA + plugin.getConfig().getString("config.modedejeu"));
	                
	                
	                Inventory configRandomInv = Bukkit.createInventory(null, 9*1, ChatColor. DARK_PURPLE + "Combien de kits max ?");
	                ItemStack[] configRandomList = new ItemStack[9*1];

	                configRandomList[0] = arrow;
	                configRandomList[2] = acaciaFence;
	                configRandomList[4] = cobblestoneWall;
	                configRandomList[6] = acaciaFenceGate;
	                configRandomList[8] = arrow;

	                name.setDisplayName("Augmenter de 1 kit");
	                acaciaFenceGate.setItemMeta(name);
	                
	                name.setDisplayName("Diminuer de 1 kit");
	                acaciaFence.setItemMeta(name);
	                
	                name.setDisplayName("Reinitialiser a 5 kit");
	                cobblestoneWall.setItemMeta(name);

	                
	                configRandomInv.setContents(configRandomList);
	                
	                player.openInventory(configRandomInv);
	
	                break;
	
	                
	                
	            case DIRT :
	
	                plugin.getConfig().set("config.modedejeu", "Classique");
	                player.sendMessage(consoleSender + "Le mode de jeu actuel est " + ChatColor.AQUA + plugin.getConfig().getString("config.modedejeu"));
	
	                Inventory configClassiqueInv = Bukkit.createInventory(null, 9*1, ChatColor. DARK_PURPLE + "Combien de points ?");
	                ItemStack[] configClassiqueList = new ItemStack[9*1];

	                configClassiqueList[0] = arrow;
	                configClassiqueList[2] = acaciaFence;
	                configClassiqueList[4] = cobblestoneWall;
	                configClassiqueList[6] = acaciaFenceGate;
	                configClassiqueList[8] = arrow;

	                name.setDisplayName("Augmenter de 2 points");
	                acaciaFenceGate.setItemMeta(name);
	                
	                name.setDisplayName("Diminuer de 2 points");
	                acaciaFence.setItemMeta(name);
	                
	                name.setDisplayName("Reinitialiser a 30 points");
	                cobblestoneWall.setItemMeta(name);

	                
	                configClassiqueInv.setContents(configClassiqueList);
	                
	                player.openInventory(configClassiqueInv);
	                
	                
	                break;
	
	                
	                
	            case TNT :
	
	            	plugin.getConfig().set("config.modedejeu", "Apocalypse");
	                player.sendMessage(consoleSender + "Le mode de jeu actuel est " + ChatColor.AQUA + plugin.getConfig().getString("config.modedejeu"));
	                
	                break;
	
	                
	            case ARROW :
	
	            	Bukkit.dispatchCommand(player, "config");
	
	                break;
	
	            default:
	                break;
	
	        	}
	
	        	event.setCancelled(true);
	        }
	        
	        	
	        
	        
	        //set nombre de kits dans mode de jeu random
	        if (clickedInventory.getTitle().equals(ChatColor. DARK_PURPLE + "Combien de kits max ?")) {
	        	
	            switch (clickedItem.getType()) {
	
	                case ACACIA_FENCE :
	
	                    plugin.getConfig().set("config.random", plugin.getConfig().getInt("config.random") - 1);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete diminue de " + ChatColor.RED + "1 kit");
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueur est maintenant de " + ChatColor.AQUA + plugin.getConfig().getInt("config.random"));
	
	                    break;
	
	                case COBBLE_WALL :
	
	                	plugin.getConfig().set("config.random", 5);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete reinitialise a " + ChatColor.AQUA + "5 kits");
	
	                    break;
	
	                case ACACIA_FENCE_GATE :
	
	                	plugin.getConfig().set("config.random", plugin.getConfig().getInt("config.random") + 1);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete augmente de " + ChatColor.GREEN + "1 kit");
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueur est maintenant de " + ChatColor.AQUA + plugin.getConfig().getInt("config.random"));
	
	                    break;
	
	                case ARROW :
	
	                	Bukkit.dispatchCommand(player, "config");
	
	                    break;
	
	                default:
	                    break;
	
	            }
	
	            event.setCancelled(true);
	        }
	        
	        
	        
	        //set nombre de points dans mode de jeu classique
	        if (clickedInventory.getTitle().equals(ChatColor. DARK_PURPLE + "Combien de points ?")) {
	        	
	            switch (clickedItem.getType()) {
	
	                case ACACIA_FENCE :
	
	                    plugin.getConfig().set("config.classique", plugin.getConfig().getInt("config.classique") - 2);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete diminue de " + ChatColor.RED + "2 points");
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueur est maintenant de " + ChatColor.AQUA + plugin.getConfig().getInt("config.classique"));
	
	                    break;
	
	                case COBBLE_WALL :
	
	                	plugin.getConfig().set("config.classique",30);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete reinitialise a " + ChatColor.AQUA + "30 points");
	
	                    break;
	
	                case ACACIA_FENCE_GATE :
	
	                	plugin.getConfig().set("config.classique", plugin.getConfig().getInt("config.classique") + 2);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete augmente de " + ChatColor.GREEN + "2 points");
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueur est maintenant de " + ChatColor.AQUA + plugin.getConfig().getInt("config.classique"));
	
	                    break;
	
	                case ARROW :
	
	                	Bukkit.dispatchCommand(player, "config");
	
	                    break;
	
	                default:
	                    break;
	
	            }
	
	            event.setCancelled(true);
	        }
	        
	        
	        
	        
	        //cancel event if in showinv inventory
	        if (clickedInventory.getTitle().equals(ChatColor.DARK_GRAY + "Inventaire de Depart")) {
	        	
	        	event.setCancelled(true);
	        	
	        	
	        }
	        
	    }
    }

}

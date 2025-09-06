package fr.teamkiwi.powerrush.events;

import fr.teamkiwi.powerrush.Game;
import fr.teamkiwi.powerrush.GameMode;
import fr.teamkiwi.powerrush.utils.Kit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.WorldBorder;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Score;

public class OnClickInventory implements Listener {
	
    public static final String consoleSender = "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "[" + ChatColor.DARK_GREEN + ChatColor.ITALIC + "POWER RUSH" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "] " + ChatColor.RESET;

	@EventHandler
    public void onClick(InventoryClickEvent event){
    	
        Inventory clickedInventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();
        HumanEntity player = event.getWhoClicked();

		Game game = Game.getPlayerGame((Player) player);

		if(game == null) {
			return;
		}
        
        if(clickedInventory != null) {
        	
	        ItemStack beacon = new ItemStack(Material.BEACON);
	        ItemStack acaciaFenceGate = new ItemStack(Material.ACACIA_FENCE_GATE);
	        ItemStack acaciaFence = new ItemStack(Material.ACACIA_FENCE);
	        ItemMeta name = acaciaFence.getItemMeta();
	        List<String> lore = new ArrayList<>();
	
	
	        ItemStack arrow = new ItemStack(Material.ARROW);
	        name.setDisplayName(ChatColor.RED + "Back");
	        arrow.setItemMeta(name);
	
	
	        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
	        wb.setCenter(0, 0);
	
	        
	        //in config menu
	        if (clickedInventory.getTitle().equals(ChatColor.DARK_PURPLE + "Config Menu")){
	
	            switch (clickedItem.getType()) {
	
	                //Config de bordure
	                case BARRIER :
	
	                    Inventory border = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Border Menu");
	                    ItemStack[] borderList = new ItemStack[9];
	
	                    name.setDisplayName(ChatColor.RESET + "Reduire de 50 blocs");
	                    acaciaFence.setItemMeta(name);
	                    
	                    name.setDisplayName(ChatColor.RESET + "Reinitaliser a 500 blocs");
	                    beacon.setItemMeta(name);
	                    
	                    name.setDisplayName(ChatColor.RESET + "Augmenter de 50 blocs");
	                    acaciaFenceGate.setItemMeta(name);
	                    
	                    borderList[0] = arrow;
	                    borderList[2] = acaciaFence;
	                    borderList[4] = beacon;
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
	                	
	                	Inventory modeDeJeuInv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Choisissez un mode de jeu");
	                	ItemStack[] modeDeJeuList = new ItemStack[9];
	                	
	                	ItemStack bedrock = new ItemStack(Material.BEDROCK);
	                	ItemStack dirt = new ItemStack(Material.DIRT);
	                	ItemStack tnt = new ItemStack(Material.TNT);
	                	
	                	
	                	modeDeJeuList[0] = arrow;
	                	modeDeJeuList[2] = bedrock;
	                	modeDeJeuList[4] = dirt;
	                	modeDeJeuList[6] = tnt;
	                	modeDeJeuList[8] = arrow;
	                	
	                	name.setDisplayName(ChatColor.RESET + "Random");
	                	lore.clear();
	                	lore.add(ChatColor.AQUA + "Permet de decouvrir les kits");
	                	name.setLore(lore);
	                	bedrock.setItemMeta(name);
	                	
	                	name.setDisplayName(ChatColor.RESET + "Classique");
	                	lore.clear();
	                	lore.add(ChatColor.AQUA + "Choisissez entre 3 kits");
	                	name.setLore(lore);
	                	dirt.setItemMeta(name);
	                	
	                	name.setDisplayName(ChatColor.RESET + "Apocalypse");
	                	lore.clear();
	                	lore.add(ChatColor.AQUA + "Aucune limite de kits");
	                	name.setLore(lore);
	                	tnt.setItemMeta(name);
	                	
	                	modeDeJeuInv.setContents(modeDeJeuList);
	                	
	                	player.openInventory(modeDeJeuInv);
	                	
	                    break;
	
	                    
	                    
	                //Config du nombre de joueurs
	                case ARMOR_STAND :
	
	                    Inventory maxPlayers = Bukkit.createInventory(null, 9, ChatColor. DARK_GREEN + "Max Players Count Menu");
	                    ItemStack[] maxPlayersList = new ItemStack[9];
	
	                    maxPlayersList[0] = arrow;
	                    maxPlayersList[2] = acaciaFence;
	                    maxPlayersList[4] = beacon;
	                    maxPlayersList[6] = acaciaFenceGate;
	                    maxPlayersList[8] = arrow;
	
	                    name.setDisplayName(ChatColor.RESET + "Augmenter de 1 joueur");
	                    acaciaFenceGate.setItemMeta(name);
	                    
	                    name.setDisplayName(ChatColor.RESET + "Diminuer de 1 joueur");
	                    acaciaFence.setItemMeta(name);
	                    
	                    name.setDisplayName(ChatColor.RESET + "Reinitialiser a 30 joueurs");
	                    beacon.setItemMeta(name);
	
	                    maxPlayers.setContents(maxPlayersList);
	
	                    player.openInventory(maxPlayers);
	
	                    break;
	
	                    
	                    
	                //Config des kits et de la partie en elle-même
	                case BOOK_AND_QUILL :
	                	
	                	createBannedKitInventory((Player) player);
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
	
	                case BEACON :
	
	                    wb.setSize(1000);
	                    player.sendMessage(consoleSender + "La taille de la bordure a ete reinitialisee a " + ChatColor.AQUA + "500 blocs");
	
	                    break;
	
	                case ACACIA_FENCE_GATE :
	
	                    wb.setSize(wb.getSize() + 100);
	                    player.sendMessage(consoleSender + "La taille de la bordure a ete augmentee de " + ChatColor.GREEN + "50 blocs");
	                    player.sendMessage(consoleSender + "La taille de la bordure est maintenant de " + ChatColor.AQUA + wb.getSize() / 2);
	
	                    break;
	
	                case ARROW :

						//TODO AFFREUX A CHANGER
	                    Bukkit.dispatchCommand(player, "config");
	
	                    break;
	                default:
	                    break;
	
	            }
	
	            event.setCancelled(true);
	        }
	
	        
	        
	        //in max player count config menu
	        if (clickedInventory.getTitle().equals(ChatColor.DARK_GREEN + "Max Players Count Menu")) {
	
	            switch (clickedItem.getType()) {
	
	                case ACACIA_FENCE :

						game.setMaxPlayers(game.getMaxPlayers()-1);
	                    //plugin.getConfig().set("config.maxPlayers", plugin.getConfig().getInt("config.maxPlayers")-1);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete diminue de " + ChatColor.RED + "1 joueur");
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs est maintenant de " + ChatColor.AQUA + game.getMaxPlayers());
	
	                    break;
	
	                case BEACON :

						game.setMaxPlayers(30);
						//plugin.getConfig().set("config.maxPlayers", 30);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete reinitialise a " + ChatColor.AQUA + "30 joueurs");
	
	                    break;
	
	                case ACACIA_FENCE_GATE :

						game.setMaxPlayers(game.getMaxPlayers()+1);
						//plugin.getConfig().set("config.maxPlayers", plugin.getConfig().getInt("config.maxPlayers")+1);
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs a ete augmente de " + ChatColor.GREEN + "1 joueur");
	                    player.sendMessage(consoleSender + "Le nombre maximum de joueurs est maintenant de " + ChatColor.AQUA + game.getMaxPlayers());
	
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

					game.setGameMode(GameMode.RANDOM);
	            	//plugin.getConfig().set("config.modedejeu", "Random");
	                player.sendMessage(consoleSender + "Le mode de jeu actuel est " + ChatColor.AQUA + game.getGameMode().toString());
	                
	                
	                Inventory configRandomInv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Combien de kits max ?");
	                ItemStack[] configRandomList = new ItemStack[9];

	                configRandomList[0] = arrow;
	                configRandomList[2] = acaciaFence;
	                configRandomList[4] = beacon;
	                configRandomList[6] = acaciaFenceGate;
	                configRandomList[8] = arrow;

	                name.setDisplayName("Augmenter de 1 kit");
	                acaciaFenceGate.setItemMeta(name);
	                
	                name.setDisplayName("Diminuer de 1 kit");
	                acaciaFence.setItemMeta(name);
	                
	                name.setDisplayName("Reinitialiser a 5 kit");
	                beacon.setItemMeta(name);

	                
	                configRandomInv.setContents(configRandomList);
	                
	                player.openInventory(configRandomInv);
	
	                break;
	
	                
	                
	            case DIRT :

					game.setGameMode(GameMode.CLASSIQUE);
	                //plugin.getConfig().set("config.modedejeu", "Classique");
	                player.sendMessage(consoleSender + "Le mode de jeu actuel est " + ChatColor.AQUA + game.getGameMode().toString());
	
	                Inventory configClassiqueInv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Combien de kits totaux ?");
	                ItemStack[] configClassiqueList = new ItemStack[9];

	                configClassiqueList[0] = arrow;
	                configClassiqueList[2] = acaciaFence;
	                configClassiqueList[4] = beacon;
	                configClassiqueList[6] = acaciaFenceGate;
	                configClassiqueList[8] = arrow;

	                name.setDisplayName("Ajouter 1 kit");
	                acaciaFenceGate.setItemMeta(name);
	                
	                name.setDisplayName("Enlever 1 kit");
	                acaciaFence.setItemMeta(name);
	                
	                name.setDisplayName("Reinitialiser a 5 kits");
	                beacon.setItemMeta(name);

	                
	                configClassiqueInv.setContents(configClassiqueList);
	                
	                player.openInventory(configClassiqueInv);
	                
	                
	                break;
	
	                
	                
	            case TNT :

					game.setGameMode(GameMode.APOCALYSPE);
	            	//plugin.getConfig().set("config.modedejeu", "Apocalypse");
	                player.sendMessage(consoleSender + "Le mode de jeu actuel est " + ChatColor.AQUA + game.getGameMode().toString());
	                
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
	        if (clickedInventory.getTitle().equals(ChatColor.DARK_PURPLE + "Combien de kits max ?")) {
	        	
	            switch (clickedItem.getType()) {
	
	                case ACACIA_FENCE :
	
	                    game.setGameModeModifier(game.getGameModeModifier()-1);
	                    player.sendMessage(consoleSender + "Le nombre maximum de kits a ete diminue de " + ChatColor.RED + "1 kit");
	                    player.sendMessage(consoleSender + "Le nombre maximum de kits est maintenant de " + ChatColor.AQUA + game.getGameModeModifier());
	
	                    break;
	
	                case BEACON :
	
	                	game.setGameModeModifier(5);
	                    player.sendMessage(consoleSender + "Le nombre maximum de kits a ete reinitialise a " + ChatColor.AQUA + "5 kits");
	
	                    break;
	
	                case ACACIA_FENCE_GATE :

						game.setGameModeModifier(game.getGameModeModifier()+1);
	                    player.sendMessage(consoleSender + "Le nombre maximum de kits a ete augmente de " + ChatColor.GREEN + "1 kit");
	                    player.sendMessage(consoleSender + "Le nombre maximum de kits est maintenant de " + game.getGameModeModifier());
	
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
	        if (clickedInventory.getTitle().equals(ChatColor. DARK_PURPLE + "Combien de kits totaux ?")) {
	        	
	            switch (clickedItem.getType()) {
	
	                case ACACIA_FENCE :

						game.setGameModeModifier(game.getGameModeModifier()-1);
	                    player.sendMessage(consoleSender + "Le nombre maximum de kits a ete diminue de " + ChatColor.RED + "1 kit");
	                    player.sendMessage(consoleSender + "Le nombre maximum de kits est maintenant de " + ChatColor.AQUA + game.getGameModeModifier());
	
	                    break;
	
	                case BEACON :

						game.setGameModeModifier(5);
	                    player.sendMessage(consoleSender + "Le nombre maximum de kits a ete reinitialise a " + ChatColor.AQUA + "5 kits");
	
	                    break;
	
	                case ACACIA_FENCE_GATE :
	
	                	game.setGameModeModifier(game.getGameModeModifier()+1);
	                    player.sendMessage(consoleSender + "Le nombre maximum de kits a ete augmente de " + ChatColor.GREEN + "1 kit");
	                    player.sendMessage(consoleSender + "Le nombre maximum de kits est maintenant de " + ChatColor.AQUA + game.getGameModeModifier());
	
	                    break;
	
	                case ARROW :
	
	                	Bukkit.dispatchCommand(player, "config");
	
	                    break;
	
	                default:
	                    break;
	
	            }
	
	            event.setCancelled(true);
	        }
	        
	        
	        
	        
	        //check if in On/Off config menu
	        if (clickedInventory.getTitle().equals(ChatColor. DARK_PURPLE + "Cliquez pour bannir")) {
	        	
	        	//check if clicking on an item
	        	if(clickedItem.getItemMeta() != null) {
	        		
	        		//case arrow: go back
		        	if(clickedItem.getType().equals(Material.ARROW)) {
		        		Bukkit.dispatchCommand(player, "config");
						return;
		        		
		        	//case cobblstone wall: reinitialiser
		        	} else if(clickedItem.getType().equals(Material.BEACON)){
		        		//plugin.getConfig().set("config.bannedKits", new ArrayList<>());
		        		game.resetBannedKits();

		        		player.sendMessage(consoleSender + ChatColor.AQUA + "Les kits bannis ont ete reinitialises");
		        		
		        	//case on a kit
		        	} else {
		        		String aKitName = clickedItem.getItemMeta().getDisplayName().substring(2);
		        		
		        		for(Kit.Kits aKit : Kit.Kits.getAsList()) {
		        			
		        			if(aKitName.equals(aKit.getName())) {
		        				
		        				//@SuppressWarnings("unchecked")
								//List<String> bannedKits = (List<String>) plugin.getConfig().getList("config.bannedKits");
								game.addBannedKit(aKit);
		        				
		        				//check if kit is banned
		        				if(game.getBannedKits().contains(aKit)){
		        					game.removeBannedKit(aKit);
		        					//plugin.getConfig().set("config.bannedKits", bannedKits);
		        					
		        					player.sendMessage(consoleSender + ChatColor.AQUA + "Le kit " + ChatColor.GREEN + aKitName + ChatColor.AQUA + " a ete remis du jeu");
		        					
		        				} else {
									game.addBannedKit(aKit);
		        					//bannedKits.add(aKitName);
		        					//plugin.getConfig().set("config.bannedKits", bannedKits);
		        					
		        					player.sendMessage(consoleSender + ChatColor.AQUA + "Le kit " + ChatColor.RED + aKitName + ChatColor.AQUA + " a ete retire du jeu");
		        					
		        				}
		        			}
		        		}

		        	}

					createBannedKitInventory((Player) player);
		        	
	        	}
	        	
	        	event.setCancelled(true);
	        	
	        }
	        
	        
	        
	        //classique choose kit menu
	        if (clickedInventory.getTitle().equals(ChatColor.DARK_PURPLE + "Choisissez un kit")) {
	        	
	        	Score playerRound = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Round").getScore((OfflinePlayer) player);
	        	
	        	//check if clicking on an item
	        	if(clickedItem.getItemMeta() != null) {
	        		
	        		if(!(playerRound.getScore() <= 0)) {
	        		
			        	//case arrow wall: skip
			        	if(clickedItem.getType().equals(Material.ARROW)){
			        		
			        		player.sendMessage(consoleSender + ChatColor.AQUA + "Vous n'avez pas choisis de kit");
			        		playerRound.setScore(playerRound.getScore() - 1);
			        		
			        		game.setClassique((Player) player);
			        		
			        	
			        	} else {
			        		String aKitName = clickedItem.getItemMeta().getDisplayName();
			        		List<Kit.Kits> allKits = new ArrayList<>(Kit.Kits.getAsList());
			        		
			        		//ban the banned kits
			        		allKits.removeAll(game.getBannedKits());
			        		
			        		
			        		for(Kit.Kits aKit : allKits) {
			        			
			        			if(aKitName.equals(aKit.getName())) {
			        				
			        				if(game.playerHasKit((Player) player, aKit)) {
			        					
		        						game.addPlayerKit(new Kit(aKit, (Player) player));
		        					
		        						playerRound.setScore(playerRound.getScore() - 1);
			        				
		        						player.sendMessage(ChatColor.AQUA + "Vous venez de recevoir le kit " + ChatColor.GOLD + aKit.getName());
			        				
		        						game.setClassique((Player) player);
			        				
			        				}else {
			        					player.sendMessage(ChatColor.DARK_RED + "Vous possedez deja le kit " + aKit.getName());
			        				}
			        					
			        			}
			        		}
			        	}
	        		} else {
	        			
	        			//exception if to not give a stack of 0
	        			if(playerRound.getScore() > 0) {
	        				ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE, playerRound.getScore()*3);
	        				player.getInventory().addItem(goldenApple);
	        			}
	        			
	        			player.closeInventory();
	        			
	        			game.giveKitItems((Player) player);
	        			
	        		}
	        			
	        	}
	        	
	        	event.setCancelled(true);
	        }
	        
	        
	        
	        
	        //cancel event if in showinv inventory
	        if (clickedInventory.getTitle().equals(ChatColor.DARK_GRAY + "Inventaire de Depart")) {
	        	
	        	event.setCancelled(true);
	        	
	        	
	        }
	        
	    }
    }


	private void createBannedKitInventory(Player player) {
		Game game = Game.getPlayerGame(player);
		if(game == null) {
			return;
		}

		
		ItemStack beacon = new ItemStack(Material.BEACON);
		ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta name = arrow.getItemMeta();
        List<String> lore = new ArrayList<>();
        
		
		Inventory banKitsInv = Bukkit.createInventory(null, 9*5, ChatColor. DARK_PURPLE + "Cliquez pour bannir");
        ItemStack[] banKitsList = new ItemStack[9*5];

        banKitsList[9*5-1] = arrow;
        banKitsList[9*4+4] = beacon;
        banKitsList[9*4] = arrow;
        
        name.setDisplayName(ChatColor.RESET + "Reinitialiser");
        beacon.setItemMeta(name);
        
        name.setDisplayName(ChatColor.RED + "Back");
        arrow.setItemMeta(name);
        
        int i = 0;
        
        for(Kit.Kits aKit : Kit.Kits.getAsList()) {
        	ItemStack anItem = new ItemStack(aKit.getMaterial());
        	
        	name.setDisplayName(ChatColor.RESET + aKit.getName());
        	lore.clear();
        	
        	if(game.getBannedKits().contains(aKit)) {
        		lore.add(ChatColor.RED + "Kit Banni");
        	} else {
        		lore.add(ChatColor.GREEN + "Kit En Jeu");
        		
        	}
        	
        	name.setLore(lore);
        	name.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        	
        	anItem.setItemMeta(name);
        	anItem.addUnsafeEnchantment(Enchantment.DURABILITY, 100);
        	banKitsList[i] = anItem;
        	
        	i++;
        	
        }
        
        banKitsInv.setContents(banKitsList);
    	
        player.openInventory(banKitsInv);
		
	}

}

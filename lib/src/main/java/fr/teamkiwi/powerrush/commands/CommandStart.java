package fr.teamkiwi.powerrush.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Score;

import fr.teamkiwi.powerrush.CommandInitServer;
import fr.teamkiwi.powerrush.Kit;
import fr.teamkiwi.powerrush.Main;

public class CommandStart implements CommandExecutor {

	public static boolean isStarted = false;
	public static List<UUID> allPlayersInGame = new ArrayList<>(); 
	
	Main plugin;
	
	public CommandStart(Main plugin) {
		this.plugin = plugin;
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//TODO: dipatch player and give inv, deop, give kits item
		
		isStarted = true;
		
		//get all online players (spec exeption ??)
		for(Player aPlayer : Bukkit.getOnlinePlayers()) {
			
			//si l'host n'a pas fait /saveinv, donner un inventaire default
			if(CommandSaveInv.inventoryOnStartContent == null) {
				
				new CommandSaveInv().setDefaultInventory();
			}
			
			//add player to allPlayersInGame
			allPlayersInGame.add(aPlayer.getUniqueId());
			
			//set inventory
			aPlayer.getInventory().setArmorContents(CommandSaveInv.inventoryOnStartArmor);
			aPlayer.getInventory().setContents(CommandSaveInv.inventoryOnStartContent);
			
			aPlayer.setGameMode(GameMode.SURVIVAL);
			
			aPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*60*2, 255, true, false));
			
			double border = Bukkit.getWorld("world").getWorldBorder().getSize()/2;
			aPlayer.teleport(new Location(Bukkit.getWorld("world"), new Random().nextDouble(border), 80, new Random().nextDouble(border)));
			
			
			
			//check mode de jeu
			switch(plugin.getConfig().getString("config.modedejeu")) {
			
			case "Random":
				
				setRandom(aPlayer);
				break;
				
				
			case "Classique":
				
				Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Points").getScore(aPlayer).setScore(plugin.getConfig().getInt("config.classique"));
				Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Round").getScore(aPlayer).setScore(0);
				setClassique(aPlayer);
				break;
				
				
			case "Apocalypse":
				break;
				
				
			default:
				break;
				
			
			}
		}
		return false;
	}

	

	
	@SuppressWarnings("deprecation")
	public void setClassique(Player player) {
		
		Random random = new Random();
		List<String> lore = new ArrayList<>();
		Score playerScore = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Points").getScore(player);
		Score playerRound = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Round").getScore(player);
		
		if(playerRound.getScore() <= 5) {
			
			//create inventory
			Inventory choicesInv = Bukkit.createInventory(null, 9*2, ChatColor.DARK_PURPLE + "Choissisez un kit");
			ItemStack[] choicesList = new ItemStack[9*2];
			
			//get all random kits
			Kit randomKit1 = CommandInitServer.allKits.get(random.nextInt(CommandInitServer.allKits.size()));
			Kit randomKit2 = CommandInitServer.allKits.get(random.nextInt(CommandInitServer.allKits.size()));
			Kit randomKit3 = CommandInitServer.allKits.get(random.nextInt(CommandInitServer.allKits.size()));
			
			//get all random items
			ItemStack randomKit1Item = new ItemStack(randomKit1.getMaterial());
			ItemStack randomKit2Item = new ItemStack(randomKit2.getMaterial());
			ItemStack randomKit3Item = new ItemStack(randomKit3.getMaterial());
			
			ItemStack arrow = new ItemStack(Material.ARROW);
			
			//get meta
			ItemMeta itemMeta = randomKit1Item.getItemMeta();
			
			//set all meta
			lore.clear();
			lore.add(ChatColor.AQUA + "Cost: " + ChatColor.GOLD + randomKit1.getPrice());
			itemMeta.setDisplayName(randomKit1.getName());
			itemMeta.setLore(lore);
			randomKit1Item.setItemMeta(itemMeta);
			
			lore.clear();
			lore.add(ChatColor.AQUA + "Cost: " + ChatColor.GOLD + randomKit1.getPrice());
			itemMeta.setDisplayName(randomKit2.getName());
			itemMeta.setLore(lore);
			randomKit2Item.setItemMeta(itemMeta);
			
			lore.clear();
			lore.add(ChatColor.AQUA + "Cost: " + ChatColor.GOLD + randomKit1.getPrice());
			itemMeta.setDisplayName(randomKit3.getName());
			itemMeta.setLore(lore);
			randomKit3Item.setItemMeta(itemMeta);
			
			lore.clear();
			itemMeta.setDisplayName(ChatColor.AQUA + "Il vous reste: " + ChatColor.GOLD + playerScore.getScore() + " points");
			itemMeta.setLore(lore);
			arrow.setItemMeta(itemMeta);
			
			lore.clear();
			itemMeta.setDisplayName("Skip");
			itemMeta.setLore(lore);
			arrow.setItemMeta(itemMeta);
			
			
			choicesList[2] = randomKit1Item;
			choicesList[4] = randomKit2Item;
			choicesList[6] = randomKit3Item;
			choicesList[13] = arrow;
			
			
			choicesInv.setContents(choicesList);
			
			player.openInventory(choicesInv);
		}
		
	}

	
	/*
	 * Desciption:
	 * Set les kits dans le mode de jeu random 
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	private void setRandom(Player player) {
		
		Random random = new Random();
		List<Kit> allKits = CommandInitServer.allKits;
		
		//if choosen random config is > than the number of kits
		if(allKits.size() < plugin.getConfig().getInt("config.random")) {
			plugin.getConfig().set("config.random", allKits.size());
		}
		
		
		for(int i = 0; i < plugin.getConfig().getInt("config.random"); i++ ) {
			
			Kit randomKit = allKits.get(random.nextInt(allKits.size()));
			List<String> randomKitList = (List<String>) plugin.getConfig().getList("kits." + randomKit.getName().toLowerCase());
			
			
			randomKitList.add(player.getName());
			allKits.remove(randomKit);
			
			plugin.getConfig().set("kits." + randomKit.getName().toLowerCase(), randomKitList);
			
			player.sendMessage(ChatColor.AQUA + "Vous venez de recevoir le kit " + ChatColor.GOLD + randomKit);
			
		}
		
		
		giveKitItems(player);
		
		
	}
	
	
	public void giveKitItems(Player player) {
		
		for(Kit aKit : CommandInitServer.allKits) {
			
			if(aKit.isGivingMaterial()) {
				
				if(plugin.getConfig().getList("kits." + aKit.getName().toLowerCase()).contains(player.getName())){
					
					ItemStack aKitItem = new ItemStack(aKit.getMaterial());
					ItemMeta aKitItemMeta = aKitItem.getItemMeta();
					
					aKitItemMeta.setDisplayName(aKit.getName());
					
					aKitItem.setItemMeta(aKitItemMeta);
					
					player.getInventory().addItem(aKitItem);
					
				}
			}
			
		}
		
	}
	

}

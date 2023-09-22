package fr.teamkiwi.powerrush.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.teamkiwi.powerrush.events.OnClickInventory;

public class CommandSaveInv implements CommandExecutor {

	
	static ItemStack[] inventoryOnStartContent = null;
	static ItemStack[] inventoryOnStartArmor = null;
	static Inventory inventoryOnStart = Bukkit.createInventory(null, InventoryType.PLAYER);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//check if the sender is a Player
		if(sender instanceof Player player) {
			
			//check if game is started
			if(! CommandStart.isStarted && player.getInventory().getContents() != null) {
				
				inventoryOnStartContent = player.getInventory().getContents();
				inventoryOnStartArmor = player.getInventory().getArmorContents();
				
				player.sendMessage(OnClickInventory.consoleSender + ChatColor.AQUA + "Votre inventaire a bien ete sauvegarde !");
				
				
				player.getInventory().clear();
				player.getInventory().setArmorContents(null);
				
			}if(! CommandStart.isStarted && args[1] != null) {
				setDefaultInventory();
			}
			
		}
		
		return false;
	}
	
	
	//permet de creer des inventaires par defaut
	public void setDefaultInventory() {
		
		//creation of all the itemstacks
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack bow = new ItemStack(Material.BOW);
		ItemStack blocks = new ItemStack(Material.STONE, 64);
		ItemStack goldenApples = new ItemStack(Material.GOLDEN_APPLE, 24);
		ItemStack arrow = new ItemStack(Material.ARROW, 32);
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		ItemStack water = new ItemStack(Material.WATER_BUCKET);
		ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
		ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemStack cookedbeef = new ItemStack(Material.COOKED_BEEF, 30);
		
		//set meta
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
		bow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
		helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		pickaxe.addEnchantment(Enchantment.DIG_SPEED, 3);
		
		//put all the items in the inventory
		inventoryOnStart.setItem(0, sword);
		inventoryOnStart.setItem(1, bow);
		inventoryOnStart.setItem(2, blocks);
		inventoryOnStart.setItem(3, helmet);
		inventoryOnStart.setItem(4, chestplate);
		inventoryOnStart.setItem(5, leggings);
		inventoryOnStart.setItem(6, boots);
		inventoryOnStart.setItem(7, goldenApples);
		inventoryOnStart.setItem(8, water);
		inventoryOnStart.setItem(9, arrow);
		
		inventoryOnStart.setItem(35, water);
		inventoryOnStart.setItem(35-9, water);
		inventoryOnStart.setItem(29, pickaxe);
		inventoryOnStart.setItem(33, lava);
		inventoryOnStart.setItem(33-9, lava);
		inventoryOnStart.setItem(30, cookedbeef);
		
		for(int i = 11; i < 15; i++) {
			inventoryOnStart.setItem(i, blocks);
		}
		
		//cancel armor and set content to Content
		inventoryOnStartArmor = null;
		inventoryOnStartContent = inventoryOnStart.getContents();
		
		
		
	}
	

}

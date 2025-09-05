package fr.teamkiwi.powerrush.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import fr.teamkiwi.powerrush.CommandInitServer;
import fr.teamkiwi.powerrush.utils.Kit;
import fr.teamkiwi.powerrush.Main;

public class CommandStart implements CommandExecutor {

	public static boolean isStarted = false;
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//TODO: dipatch player and give inv, deop, give kits item

		if (sender instanceof Player) {

			Game game = Game.getPlayerGame(((Player) sender).getPlayer());
			if (game == null) {
				sender.sendMessage("Vous n'etes pas dans une partie !");
			} else {
				game.start();
			}
		}
		return false;
	}



}

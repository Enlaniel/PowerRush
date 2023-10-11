package fr.teamkiwi.powerrush.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.teamkiwi.powerrush.Stop;

public class CommandForceStop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		new Stop().stop();
		
		return false;
	}

}

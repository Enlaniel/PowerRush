package fr.teamkiwi.powerrush.commands.debug;

import fr.teamkiwi.powerrush.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandJoinGame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            new Game();

            Game.joinFirstGame((Player) sender);
        }



        return false;
    }
}

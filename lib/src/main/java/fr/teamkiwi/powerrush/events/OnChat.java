package fr.teamkiwi.powerrush.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
    	
        Player player = event.getPlayer();
        
        if (player.getGameMode().equals(GameMode.SPECTATOR)){

            player.sendMessage(OnClickInventory.consoleSender + "Vous ne pouvez pas envoyer de messages textuels en tant que spectateur !");
            event.setCancelled(true);

        }

    }

}

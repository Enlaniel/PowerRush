package fr.teamkiwi.powerrush.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class WhenPlayerJoins implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        if (Bukkit.getOnlinePlayers().size() > OnClickInventory.maxPlayerConnected && OnClickInventory.maxPlayerConnected > 0) {

            event.getPlayer().kickPlayer("La partie est complete, il y a déja " + Bukkit.getOnlinePlayers().size() + " / " + OnClickInventory.maxPlayerConnected + " joueurs connectés !");
        }

    }

}

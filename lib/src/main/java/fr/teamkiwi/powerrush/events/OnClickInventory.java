package fr.teamkiwi.powerrush.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OnClickInventory implements Listener {

    @EventHandler
    public void onClickInConfig(InventoryClickEvent event){

        Inventory clickedInventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();
        HumanEntity player = event.getWhoClicked();

        if (clickedInventory.getTitle().equals(ChatColor.DARK_PURPLE + "Config Menu")){

            switch (clickedItem.getType()){

                //Config de bordure
                case BARRIER :
                    break;

                //Config de map
                case GRASS :
                    break;

                //Changement de mode de jeu
                case COMMAND :
                    break;

                //Config du nombre de joueurs
                case SKELETON_SKULL :
                    break;

                //Config des rôles et de la partie en elle-même
                case WRITTEN_BOOK :
                    break;

            }

            event.setCancelled(true);

        }

    }

}

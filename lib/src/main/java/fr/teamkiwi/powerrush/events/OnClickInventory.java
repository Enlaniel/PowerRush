package fr.teamkiwi.powerrush.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
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
                    WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();

                    Inventory border = Bukkit.createInventory(null, 9*1, ChatColor.AQUA + "Border Menu");
                    ItemStack[] borderList = new ItemStack[9*1];

                    ItemStack redClay = new ItemStack(Material.CLAY, 1, (byte)14);
                    ItemStack lightGrayClay = new ItemStack(Material.CLAY, 1, (byte)8);
                    ItemStack greenClay = new ItemStack(Material.CLAY, 1, (byte)5);

                    borderList[3] = redClay;
                    borderList[5] = lightGrayClay;
                    borderList[7] = greenClay;

                    player.openInventory(border);
                    break;

                //Config de map
                case GRASS :
                    break;

                //Changement de mode de jeu
                case COMMAND :
                    break;

                //Config du nombre de joueurs
                case ARMOR_STAND :
                    break;

                //Config des rôles et de la partie en elle-même
                case WRITTEN_BOOK :
                    break;

                default :
                    break;

            }

            event.setCancelled(true);

        }

    }

}

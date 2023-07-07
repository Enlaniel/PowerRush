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

    String consoleSender = "" + ChatColor.GRAY + ChatColor.ITALIC + "[" + ChatColor.DARK_GREEN + "POWER RUSH" + ChatColor.GRAY + "]";

    @EventHandler
    public void onClickInConfig(InventoryClickEvent event){

        Inventory clickedInventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();
        HumanEntity player = event.getWhoClicked();

        ItemStack acaciaFence = new ItemStack(Material.ACACIA_FENCE);
        ItemStack cobblestoneWall = new ItemStack(Material.COBBLE_WALL);
        ItemStack acaciaFenceGate = new ItemStack(Material.ACACIA_FENCE_GATE);

        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
        wb.setCenter(0, 0);

        if (clickedInventory.getTitle().equals(ChatColor.DARK_PURPLE + "Config Menu")){

            switch (clickedItem.getType()) {

                //Config de bordure
                case BARRIER :

                    Inventory border = Bukkit.createInventory(null, 9*1, ChatColor.AQUA + "Border Menu");
                    ItemStack[] borderList = new ItemStack[9*1];

                    borderList[3] = acaciaFence;
                    borderList[5] = cobblestoneWall;
                    borderList[7] = acaciaFenceGate;

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

        if (clickedInventory.getTitle().equals(ChatColor.AQUA + "Border Menu")){

            switch (clickedItem.getType()) {

                case ACACIA_FENCE :

                    wb.setSize(wb.getSize() - 100);
                    player.sendMessage(consoleSender + "La taille de la bordure a été réduite de " + ChatColor.RED + "50 blocs");

                    break;

                case COBBLE_WALL :

                    wb.setSize(1000);
                    player.sendMessage(consoleSender + "La taille de la bordure a été réinitialisée à " + ChatColor.AQUA + "500 blocs");

                    break;

                case ACACIA_FENCE_GATE :

                    wb.setSize(wb.getSize() + 100);
                    player.sendMessage(consoleSender + "La taille de la bordure a été augmentée de " + ChatColor.GREEN + "50 blocs");

                    break;

                default:
                    break;

            }

        }

    }

}

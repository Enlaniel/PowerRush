package fr.teamkiwi.powerrush.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnClickInventory implements Listener {

    String consoleSender = "" + ChatColor.LIGHT_PURPLE + ChatColor.ITALIC + "[" + ChatColor.DARK_GREEN + ChatColor.ITALIC + "POWER RUSH" + ChatColor.  LIGHT_PURPLE + ChatColor.ITALIC + "]";

    @EventHandler
    public void onClickInConfig(InventoryClickEvent event){

        Inventory clickedInventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();
        HumanEntity player = event.getWhoClicked();

        ItemStack acaciaFence = new ItemStack(Material.ACACIA_FENCE);
        ItemMeta name = acaciaFence.getItemMeta();
        name.setDisplayName("Reduire de 50 blocs");
        acaciaFence.setItemMeta(name);

        ItemStack cobblestoneWall = new ItemStack(Material.COBBLE_WALL);
        name = cobblestoneWall.getItemMeta();
        name.setDisplayName("Reinitaliser a 500 blocs");
        cobblestoneWall.setItemMeta(name);

        ItemStack acaciaFenceGate = new ItemStack(Material.ACACIA_FENCE_GATE);
        name = acaciaFenceGate.getItemMeta();
        name.setDisplayName("Augmenter de 50 blocs");
        acaciaFenceGate.setItemMeta(name);


        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
        wb.setCenter(0, 0);

        if (clickedInventory.getTitle().equals(ChatColor.DARK_PURPLE + "Config Menu")){

            switch (clickedItem.getType()) {

                //Config de bordure
                case BARRIER :

                    Inventory border = Bukkit.createInventory(null, 9*1, ChatColor.AQUA + "Border Menu");
                    ItemStack[] borderList = new ItemStack[9*1];

                    borderList[2] = acaciaFence;
                    borderList[4] = cobblestoneWall;
                    borderList[6] = acaciaFenceGate;

                    border.setContents(borderList);

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
                    player.sendMessage(consoleSender + " La taille de la bordure a ete reduite de " + ChatColor.RED + "50 blocs");
                    player.sendMessage(consoleSender + " La taille de la bordure est maintenant de " + ChatColor.AQUA + wb.getSize() / 2);

                    break;

                case COBBLE_WALL :

                    wb.setSize(1000);
                    player.sendMessage(consoleSender + " La taille de la bordure a ete reinitialisee a " + ChatColor.AQUA + "500 blocs");

                    break;

                case ACACIA_FENCE_GATE :

                    wb.setSize(wb.getSize() + 100);
                    player.sendMessage(consoleSender + " La taille de la bordure a ete augmentee de " + ChatColor.GREEN + "50 blocs");
                    player.sendMessage(consoleSender + " La taille de la bordure est maintenant de " + ChatColor.AQUA + wb.getSize() / 2);

                    break;

                default:
                    break;

            }

            event.setCancelled(true);

        }

    }

}

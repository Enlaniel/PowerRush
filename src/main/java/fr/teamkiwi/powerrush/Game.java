package fr.teamkiwi.powerrush;

import fr.teamkiwi.powerrush.events.OnClickInventory;
import fr.teamkiwi.powerrush.utils.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Score;

import java.util.*;

public class Game {

    private static final Set<Game> allGames = new HashSet<>();

    private int id;
    private final Set<Player> allPlayers = new HashSet<>();
    private final Set<Kit> allPlayersKits = new HashSet<>();
    private boolean hasStarted = false;

    //config
    private GameMode gameMode = GameMode.CLASSIQUE;
    private final Set<Kit.Kits> bannedKits = new HashSet<>();
    //private Map map;
    private int maxPlayers = 30; //TODO do max player consequences
    //else kick player
//		    if (Bukkit.getOnlinePlayers().size() > plugin.getConfig().getInt("config.maxPlayers") && plugin.getConfig().getInt("config.maxPlayers") > 0) {
//
//		    	player.kickPlayer("La partie est complete, il y a deja " + (Bukkit.getOnlinePlayers().size() - 1) + " / " + plugin.getConfig().getInt("config.maxPlayers") + " joueurs connectes !");
//		        event.setJoinMessage(OnClickInventory.consoleSender + player.getName() + ChatColor.AQUA + " essaie de rejoindre la partie");
//
//		    }


    private static ItemStack[] inventoryOnStartContent = null;
    private static ItemStack[] inventoryOnStartArmor = null;
    private final Inventory inventoryOnStart = Bukkit.createInventory(null, InventoryType.PLAYER);


    public Game() {
        allGames.add(this);
    }


    public int getId() {
        return id;
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gm) {
        gameMode = gm;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int i) {
        maxPlayers = i;
    }

    public Set<Kit.Kits> getBannedKits() {
        return bannedKits;
    }

    public void resetBannedKits() {
        bannedKits.clear();
    }

    public void addBannedKit(Kit.Kits kit) {
        bannedKits.add(kit);
    }

    public void removeBannedKit(Kit.Kits kit) {
        bannedKits.remove(kit);
    }

    public Set<Player> getAllPlayers() {
        return allPlayers;
    }

    public void addPlayerKit(Kit kit) {
        allPlayersKits.add(kit);
    }

    public Set<Kit> getPlayerKits(Player player) {
        Set<Kit> allPlayerKits = new HashSet<>();
        for(Kit kit : allPlayersKits) {
            if(kit.getPlayer().equals(player)) {
                allPlayerKits.add(kit);
            }
        }
        return allPlayerKits;
    }

    public Set<Kit.Kits> getPlayerKitsType(Player player) {
        Set<Kit> pKits = getPlayerKits(player);
        Set<Kit.Kits> allPlayerKits = new HashSet<>();
        for(Kit k : pKits) {
            allPlayerKits.add(k.getType());
        }
        return allPlayerKits;
    }

    public boolean playerHasKit(Player player, Kit.Kits kit) {
        return getPlayerKitsType(player).contains(kit);
    }

    public void removeKitToPlayer(Player player, Kit.Kits kit) {
        Set<Kit> allKits = getPlayerKits(player);
        for(Kit k : allKits) {
            if(k.getType().equals(kit)) {
                allPlayersKits.remove(k);
            }
        }

    }




    public void start() {
        //get all online players (spec exeption ??)
        for (Player aPlayer : allPlayers) {

            //si l'host n'a pas fait /saveinv, donner un inventaire default
            if (inventoryOnStartContent == null) {
                setDefaultInventory();
            }

            //add player to allPlayersInGame
            //allPlayersInGame.add(aPlayer.getUniqueId());

            //set inventory
            aPlayer.getInventory().setArmorContents(inventoryOnStartArmor);
            aPlayer.getInventory().setContents(inventoryOnStartContent);

            aPlayer.setGameMode(org.bukkit.GameMode.SURVIVAL);

            aPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 60 * 2, 255, true, false));

            double border = Bukkit.getWorld("world").getWorldBorder().getSize() / 2;
            aPlayer.teleport(new Location(Bukkit.getWorld("world"), new Random().nextInt((int) border), 80, new Random().nextInt((int) border)));


            //check mode de jeu
            switch (gameMode) {

                case RANDOM:
                    setRandom(aPlayer);
                    break;


                case CLASSIQUE:
//                    Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Round").getScore(aPlayer).setScore(plugin.getConfig().getInt("config.classique"));
                    setClassique(aPlayer);
                    break;


                case APOCALYSPE:
                    break;


                default:
                    break;


            }
        }

    }

    public void setClassique(Player player) {

        Random random = new Random();
        List<String> lore = new ArrayList<>();
        Score playerRound = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Round").getScore(player);

        List<Kit.Kits> allKits = new ArrayList<>(Kit.Kits.getAsList());

        //ban the banned kits
        allKits.removeAll(bannedKits);

        if(playerRound.getScore() <= 0) {

            //create inventory
            Inventory choicesInv = Bukkit.createInventory(null, 9*2, ChatColor.DARK_PURPLE + "Choisissez un kit");
            ItemStack[] choicesList = new ItemStack[9*2];

            //get all random kits
            Kit.Kits randomKit1 = allKits.get(random.nextInt(allKits.size()));
            Kit.Kits randomKit2 = allKits.get(random.nextInt(allKits.size()));
            Kit.Kits randomKit3 = allKits.get(random.nextInt(allKits.size()));

            //get all random items
            ItemStack randomKit1Item = new ItemStack(randomKit1.getMaterial());
            ItemStack randomKit2Item = new ItemStack(randomKit2.getMaterial());
            ItemStack randomKit3Item = new ItemStack(randomKit3.getMaterial());

            ItemStack arrow = new ItemStack(Material.ARROW);

            //get meta
            ItemMeta itemMeta = randomKit1Item.getItemMeta();

            //set all meta
            lore.clear();
            lore.add(ChatColor.AQUA + randomKit1.getName() + " : " + ChatColor.GOLD + randomKit1.getDescription());
            itemMeta.setDisplayName(randomKit1.getName());
            itemMeta.setLore(lore);
            randomKit1Item.setItemMeta(itemMeta);

            lore.clear();
            lore.add(ChatColor.AQUA + randomKit2.getName() + " : " + ChatColor.GOLD + randomKit2.getDescription());
            itemMeta.setDisplayName(randomKit2.getName());
            itemMeta.setLore(lore);
            randomKit2Item.setItemMeta(itemMeta);

            lore.clear();
            lore.add(ChatColor.AQUA + randomKit3.getName() + " : " + ChatColor.GOLD + randomKit3.getDescription());
            itemMeta.setDisplayName(randomKit3.getName());
            itemMeta.setLore(lore);
            randomKit3Item.setItemMeta(itemMeta);

            lore.clear();
            lore.add(ChatColor.AQUA + "Il vous reste : " + ChatColor.GOLD + playerRound.getScore() + " kits");
            itemMeta.setDisplayName("Skip");
            itemMeta.setLore(lore);
            arrow.setItemMeta(itemMeta);


            choicesList[2] = randomKit1Item;
            choicesList[4] = randomKit2Item;
            choicesList[6] = randomKit3Item;
            choicesList[13] = arrow;


            choicesInv.setContents(choicesList);

            player.openInventory(choicesInv);
        }

    }



    /*
     * Description:
     * Set les kits dans le mode de jeu random
     *
     */
    private void setRandom(Player player) {

        Random random = new Random();
        List<Kit.Kits> allKits = new ArrayList<>(Kit.Kits.getAsList());

        //ban the banned kits
        allKits.removeAll(bannedKits);

        //if choosen random config is > than the number of kits
//        if(allKits.size() < plugin.getConfig().getInt("config.random")) {
//            plugin.getConfig().set("config.random", allKits.size());
//        }

//        for(int i = 0; i < plugin.getConfig().getInt("config.random"); i++ ) {
        for(int i = 0; i < 3; i++ ) {

            Kit.Kits randomKit = allKits.get(random.nextInt(allKits.size()));
//            List<String> randomKitList = (List<String>) plugin.getConfig().getList("kits." + randomKit.getName().toLowerCase());



//            randomKitList.add(player.getName());
            allPlayersKits.add(new Kit(randomKit, player));
            allKits.remove(randomKit);

//            plugin.getConfig().set("kits." + randomKit.getName().toLowerCase(), randomKitList);

            player.sendMessage(ChatColor.AQUA + "Vous venez de recevoir le kit " + ChatColor.GOLD + randomKit.getName());

        }


        giveKitItems(player);


    }


    public void giveKitItems(Player player) {

        for(Kit aKit : getPlayerKits(player)) {

            //set cooldown
//            if(aKit.hasCooldown()) {
//                Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(aKit.getName());
//                obj.getScore(player).setScore(0);
//            }

            //give items
            if(aKit.getType().isGivingMaterial()) {

//                if(plugin.getConfig().getList("kits." + aKit.getName().toLowerCase()).contains(player.getName())){

                    ItemStack aKitItem = new ItemStack(aKit.getMaterial());
                    ItemMeta aKitItemMeta = aKitItem.getItemMeta();

                    aKitItemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + aKit.getName());
                    aKitItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    aKitItem.setItemMeta(aKitItemMeta);

                    aKitItem.addUnsafeEnchantment(Enchantment.DURABILITY, 100);

                    player.getInventory().addItem(aKitItem);

//                }
            }

        }

    }



    public void stop() {

//        //reset parameter
//        CommandStart.allPlayersInGame.clear();
//        CommandStart.isStarted = false;
//
//        //reset all kits
//        List<Kit> allKits = CommandInitServer.allKits;
//
//        for(Kit aKit : allKits) {
//            plugin.getConfig().set("kits." + aKit.getName().toLowerCase(), new ArrayList<>());
//        }
//
//
//        for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
//            Bukkit.getScoreboardManager().getMainScoreboard().resetScores(player);
//        }

        Location spawn = new Location(Bukkit.getWorld("world"), 0, 201, 0);

        for (Player aPlayer : allPlayers) {

            //reset players
            aPlayer.getInventory().clear();
            aPlayer.getInventory().setArmorContents(null);
            aPlayer.teleport(spawn);
            aPlayer.setGameMode(org.bukkit.GameMode.SURVIVAL);
            aPlayer.setHealth(aPlayer.getMaxHealth());
            aPlayer.setFoodLevel(20);
            //Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Points").getScore(aPlayer).setScore(plugin.getConfig().getInt("config.classique"));
            //Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Round").getScore(aPlayer).setScore(0);
            for (PotionEffect effect : aPlayer.getActivePotionEffects()) {
                aPlayer.removePotionEffect(effect.getType());
            }

            aPlayer.sendMessage(OnClickInventory.consoleSender + "La partie a bien ete arretee");
        }
    }


    public void setStartInventory(ItemStack[] contents, ItemStack[] armorContents) {
        inventoryOnStartContent = contents;
        inventoryOnStartArmor = armorContents;
    }

    public ItemStack[] getInventoryOnStartContent() {
        return inventoryOnStartContent;
    }

    public ItemStack[] getInventoryOnStartArmor() {
        return inventoryOnStartArmor;
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


    public static Game getPlayerGame(Player player) {
        for(Game g : allGames) {
            if(g.allPlayers.contains(player)) {
                return g;
            }
        }
        return null;
    }

    public static Game getGameByID(int id) {
        for(Game g : allGames) {
            if(g.id == id) {
                return g;
            }
        }
        return null;
    }

    //DEBUG
    public static void joinFirstGame(Player player) {
        Game game = (Game) allGames.toArray()[0];
        game.allPlayers.add(player);

        player.sendMessage("You joined game " + game.id);
    }

}

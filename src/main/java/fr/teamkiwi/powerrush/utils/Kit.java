package fr.teamkiwi.powerrush.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kit {

	int cooldown;
	final int maxCooldown;
	Player player;
	Kits type;

	public Kit(Kits kit, Player player) {
		this.player = player;
		type = kit;
		cooldown = type.cooldown;
		maxCooldown = type.cooldown;
	}


	public Player getPlayer() {
		return player;
	}
	public Kits getType() {
		return type;
	}
	
	public int getCooldown() {
		return cooldown;
	}
	public void minusCooldown() {
		cooldown --;
	}
	public void resetCooldown() {
		cooldown = maxCooldown;
	}

	public boolean inCooldown() {
        return cooldown > 0;
	}


	public enum Kits {
		KATANA("Katana", Material.DIAMOND_SWORD, -1, false, ""),
		ANGE("Ange", Material.GOLDEN_APPLE, 3, false, ""),
		DOPPAGE("Doppage", Material.SUGAR, 10, true, ""),
		TRADER("Trader", Material.EMERALD, 20, true, ""),
		SANGSUE("Sangsue", Material.FERMENTED_SPIDER_EYE, -1, false, ""),
		AVENIR("Avenir", Material.BEACON, -1, false, "");


		final String name;
		final Material material;
		final int cooldown;
		final boolean giveMaterial;
		final String description;


		Kits(String name, Material material, int cooldown, boolean giveMaterial, String description){
			this.name = name;
			this.material = material;
			this.giveMaterial = giveMaterial;
			this.cooldown = cooldown;
			this.description = description;

		}


		public String getName() {
			return name;
		}

		public Material getMaterial() {
			return material;
		}

		public int getCooldown() {
			return cooldown;
		}

		public boolean isGivingMaterial() {
			return giveMaterial;
		}

		public String getDescription() {
			return description;
		}

		public boolean hasCooldown() {
            return cooldown >= 0;
		}

		public static List<Kits> getAsList() {
			return new ArrayList<>(Arrays.asList(Kit.Kits.values()));
		}

	}


}

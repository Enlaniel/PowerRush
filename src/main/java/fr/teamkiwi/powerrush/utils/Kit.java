package fr.teamkiwi.powerrush.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kit {
	
	String name;
	Material material;
	int cooldown;
	boolean giveMaterial;
	String description;
	Player player;
	Kits type;
	
	public Kit(String name, Material material, int cooldown, boolean giveMaterial, String description){
		
		this.name = name;
		this.material = material;
		this.giveMaterial = giveMaterial;
		this.cooldown = cooldown;
		this.description = description;
		
	}

	public Kit(Kits kit, Player player) {
		this.player = player;
		type = kit;
	}


	public Player getPlayer() {
		return player;
	}
	public Kits getType() {
		return type;
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
		if(cooldown != 0) {
			return true;
		}else {
			return false;
		}
		
	}

	public enum Kits {
		KATANA("Katana", Material.DIAMOND_SWORD, 0, false, ""),
		ANGE("Ange", Material.GOLDEN_APPLE, 0, false, ""),
		DOPPAGE("Doppage", Material.SUGAR, 120, true, ""),
		TRADER("Trader", Material.EMERALD, 120, true, ""),
		SANGSUE("Sangsue", Material.FERMENTED_SPIDER_EYE, 0, false, ""),
		AVENIR("Avenir", Material.BEACON, 0, false, "");


		String name;
		Material material;
		int cooldown;
		boolean giveMaterial;
		String description;


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
			if(cooldown != 0) {
				return true;
			}else {
				return false;
			}

		}

		public static List<Kits> getAsList() {
			return new ArrayList<>(Arrays.asList(Kit.Kits.values()));
		}

	}


}

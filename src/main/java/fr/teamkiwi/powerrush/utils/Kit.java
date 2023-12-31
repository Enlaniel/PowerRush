package fr.teamkiwi.powerrush.utils;

import org.bukkit.Material;

public class Kit {
	
	String name;
	Material material;
	int cooldown;
	boolean giveMaterial;
	String description;
	
	public Kit(String name, Material material, int cooldown, boolean giveMaterial, String description){
		
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
}

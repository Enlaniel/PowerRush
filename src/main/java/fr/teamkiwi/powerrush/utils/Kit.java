package fr.teamkiwi.powerrush.utils;

import org.bukkit.Material;

public class Kit {
	
	String name;
	Material material;
	int price;
	int cooldown;
	boolean giveMaterial;
	
	public Kit(String name, Material material, int price, int cooldown, boolean giveMaterial){
		
		this.name = name;
		this.material = material;
		this.price = price;
		this.giveMaterial = giveMaterial;
		this.cooldown = cooldown;
		
	}
	
	
	public String getName() {
		return name;
	}
	
	public Material getMaterial() {
		return material;
	}

	public int getPrice() {
		return price;
	}
	
	public int getCooldown() {
		return cooldown;
	}
	
	public boolean isGivingMaterial() {
		return giveMaterial;
	}
	
	public boolean hasCooldown() {
		if(cooldown != 0) {
			return true;
		}else {
			return false;
		}
		
	}
}

package fr.teamkiwi.powerrush.utils;

import org.bukkit.Material;

public class Kit {
	
	String name;
	Material material;
	int price;
	boolean giveMaterial;
	boolean hasCooldown;
	
	public Kit(String name, Material material, int price, boolean giveMaterial, boolean hasCooldown){
		
		this.name = name;
		this.material = material;
		this.price = price;
		this.giveMaterial = giveMaterial;
		this.hasCooldown = hasCooldown;
		
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
	
	public boolean isGivingMaterial() {
		return giveMaterial;
	}
	
	public boolean hasCooldown() {
		return hasCooldown;
	}
}

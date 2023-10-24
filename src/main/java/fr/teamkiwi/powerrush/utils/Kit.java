package fr.teamkiwi.powerrush.utils;

import org.bukkit.Material;

public class Kit {
	
	String name;
	Material material;
	int price;
	boolean giveMaterial;
	
	public Kit(String name, Material material, int price, boolean giveMaterial){
		
		this.name = name;
		this.material = material;
		this.price = price;
		this.giveMaterial = giveMaterial;
		
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
}

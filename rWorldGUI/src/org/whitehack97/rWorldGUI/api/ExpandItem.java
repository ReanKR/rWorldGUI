package org.whitehack97.rWorldGUI.api;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class ExpandItem
{
	private String IndexName;
	private String Type;
	private Location location;
	private ItemStack itemStack;

	public ExpandItem(String index)
	{
		this.IndexName = index;
	}

	public void setType(String type)
	{
		this.Type = type;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}
	
	public String getType()
	{
		return this.Type;
	}

	public Location getLocation()
	{
		return this.location;
	}
	
	public String getIndexName()
	{
		return this.IndexName;
	}

	public void setItemStack(ItemStack itemStack)
	{
		this.itemStack = itemStack;
	}
	
	public ItemStack getItemStack()
	{
		return this.itemStack;
	}
}

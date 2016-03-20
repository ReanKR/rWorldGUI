package org.whitehack97.rWorldGUI.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.whitehack97.rWorldGUI.Util.SubManager;

public class ExpandItem
{
	private String IndexName;
	private String Type;
	private Location location;
	private ItemStack itemStack;
	private List<String> Lore = new ArrayList<String>();
	private String command;

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
	
	public void setLore(List<String> Lore)
	{
		List<String> RepLore = new ArrayList<String>();
		for(String Str : Lore)
		{
			RepLore.add(SubManager.RepColor(Str));
		}
		this.Lore = RepLore;
	}
	
	public String getType()
	{
		return this.Type;
	}
	
	public List<String> getLore()
	{
		return this.Lore;
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

	public void setComamnd(String command)
	{
		this.command = command;
	}
	
	public String getCommand()
	{
		return this.command;
	}
}

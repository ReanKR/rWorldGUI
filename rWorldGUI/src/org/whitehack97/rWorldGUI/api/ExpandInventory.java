package org.whitehack97.rWorldGUI.api;

import org.bukkit.inventory.Inventory;

public class ExpandInventory
{
	private Inventory Inventory;
	private String Path = null;
	private String OpenCommand = null;
	private boolean SoundEnabled = false;

	public ExpandInventory(Inventory Inventory, String YamlPath)
	{
		this.Inventory = Inventory;
		this.Path  = YamlPath;
	}
	
	public void setOpenCommand(String Command)
	{
		this.OpenCommand = Command;
	}
	
	public void setEnableSound(boolean Enabled)
	{
		this.SoundEnabled  = Enabled;
	}
	
	
	public String getOpenCommand()
	{
		return this.OpenCommand;
	}
	
	public Inventory getInventory()
	{
		return this.Inventory;
	}
	
	public boolean isSoundEnabled()
	{
		return this.SoundEnabled;
	}
	
	public String getPath()
	{
		return this.Path;
	}
}

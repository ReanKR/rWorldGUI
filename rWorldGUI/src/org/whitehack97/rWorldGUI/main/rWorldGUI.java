package org.whitehack97.rWorldGUI.main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import org.whitehack97.rWorldGUI.Listener.InventoryListener;
import org.whitehack97.rWorldGUI.Listener.PlayerListener;
import org.whitehack97.rWorldGUI.Listener.RefreshInventory;
import org.whitehack97.rWorldGUI.api.ExpandInventory;
import org.whitehack97.rWorldGUI.api.ExpandItem;
import org.whitehack97.rWorldGUI.config.InventoryConfig;

public class rWorldGUI extends JavaPlugin implements Listener
{
	public static Map<Inventory, Map<Integer, ExpandItem>> InventoryInfomation = new HashMap<Inventory, Map<Integer, ExpandItem>>();
	public static Map<Inventory, ExpandInventory> InventoryUtil = new HashMap<Inventory, ExpandInventory>();
	public static Map<String, Inventory> InventoryCommand = new HashMap<String, Inventory>();
	public static Map<String, Inventory> InventoryYaml = new HashMap<String, Inventory>();
	public static int TID = -1;

	public static rWorldGUI plugin;
	public static String Prefix = "」f[」1r」eW」forld」aGUI」f]」e ";
	
	@Override
	public void onEnable()
	{
		plugin = this;
		InventoryConfig.InventoryLoad();
		Bukkit.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(rWorldGUI.plugin, new RefreshInventory(), 0L, 60L);
	}
	
	@Override
	public void onDisable()
	{
		/* Nothing */
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		String cmd = command.getName();
		if(cmd.equalsIgnoreCase("rWorldGUI.main"))
		{
			Player player = (Player)sender;
			if(args.length < 1)
			{
				return false;
			}
			else
			{
				player.openInventory(InventoryYaml.get(args[0]));
				return true;
			}
		}
		return false;
	}
}

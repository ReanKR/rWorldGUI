package org.whitehack97.rWorldGUI.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.whitehack97.rWorldGUI.Util.FileManager;
import org.whitehack97.rWorldGUI.config.InventoryConfig;

public class rWorldGUICommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender Sender, Command command, String label, String[] args)
	{
		if(Sender instanceof Player)
		{
			Player player = (Player)Sender;
			if(args.length < 1)
			{
				HelpPage(player, 0);
			}
			else
			{
				if(args[0].equalsIgnoreCase("page"))
				{
					if(args.length < 2)
					{
						HelpPage(player, 0);
						return true;
					}
					else
					{
						HelpPage(player, RangeNumber(args[1]));
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("open"))
				{
					if(args.length < 2)
					{
						
					}
					else
					{
						player.openInventory(rWorldGUI.InventoryYaml.get(args[1].toLowerCase()));
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("set"))
				{
					if(args.length < 2)
					{
						return false;
					}
					else
					{
						File file = FileManager.getFile(args[1]);
						YamlConfiguration Load = FileManager.LoadSourceFile(args[1]);
						Location location = player.getLocation();
						Load.set(args[2] + ".LOCATION.WORLD", location.getWorld().getName());
						Load.set(args[2] + ".LOCATION.X", location.getX());
						Load.set(args[2] + ".LOCATION.Y", location.getY());
						Load.set(args[2] + ".LOCATION.Z", location.getZ());
						Load.set(args[2] + ".LOCATION.PITCH", location.getPitch());
						Load.set(args[2] + ".LOCATION.YAW", location.getYaw());
						try
						{
							Load.save(file);
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						msg(player, "Save Complete.");
						InventoryConfig.InventoryLoad();
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int RangeNumber(String Number)
	{
		try
		{
			Integer.parseInt(Number);
		}
		catch(NullPointerException e)
		{
			return -52;
		}
		
		return Integer.parseInt(Number);
	}
	
	public void HelpPage(Player p, int Page)
	{
		if(Page == 0)
		{
			msg(p, "rWorldGUI");
			for(String Key : rWorldGUI.InventoryYaml.keySet())
			{
				msg(p, Key);
			}
		}
	}
	
	public void msg(Player player, String Str)
	{
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', Str));
	}
	
	/*
	 *  String cmd = command.getName();
		if(cmd.equalsIgnoreCase("rWorldGUI.main"))
		{
			Player player = (Player)sender;
			if(args.length < 1)
			{
				return false;
			}
			else
			{
				for(String str : InventoryYaml.keySet())
				{
					player.openInventory(InventoryYaml.get(str));
				}
				return true;
			}
		}
	 */
	
}

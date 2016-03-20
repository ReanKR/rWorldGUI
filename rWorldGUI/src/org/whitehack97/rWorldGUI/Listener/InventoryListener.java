package org.whitehack97.rWorldGUI.Listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import org.whitehack97.rWorldGUI.api.ExpandInventory;
import org.whitehack97.rWorldGUI.api.ExpandItem;
import org.whitehack97.rWorldGUI.main.rWorldGUI;

public class InventoryListener implements Listener
{
	
	@EventHandler
	public void InventoryOpen(InventoryOpenEvent event)
	{
		Player player = (Player) event.getPlayer();
		Inventory inventory = event.getInventory();
		if(rWorldGUI.InventoryInformation.containsKey(inventory))
		{
			ExpandInventory InventoryDetailed = rWorldGUI.InventoryUtil.get(inventory);
			if(InventoryDetailed.isSoundEnabled())
			{
				player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
			}
		}
		
	}
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent event)
	{
		Player player = (Player)event.getWhoClicked();
		Inventory inventory = event.getInventory();
		if(rWorldGUI.InventoryInformation.containsKey(inventory))
		{
			try
			{
				if(!(event.getCurrentItem().getType() == Material.AIR) && rWorldGUI.InventoryInformation.get(inventory).containsKey(event.getSlot()))
				{
					if(event.isRightClick() || event.isLeftClick())
					{
						event.setCancelled(true);
					}
					ExpandItem expandItem = rWorldGUI.InventoryInformation.get(event.getClickedInventory()).get(event.getSlot());
					if(expandItem.getType().equalsIgnoreCase("CLOSE"))
					{
						player.closeInventory();
						return;
					}
					else if(expandItem.getType().equalsIgnoreCase("COMMAND"))
					{
						String command = expandItem.getCommand();
						player.closeInventory();
						if(command.contains("Console"))
						{
							String[] CommandType = command.split(": ");
							String FinCommand = CommandType[1].replaceAll("%player%", player.getName()).toLowerCase();
							rWorldGUI.plugin.getServer().dispatchCommand(rWorldGUI.plugin.getServer().getConsoleSender(), FinCommand);
						}
						else
						{
							player.chat("/" + command.toLowerCase());
						}
						return;
					}
					else if(expandItem.getType().equalsIgnoreCase("WORLD_INFORMATION"))
					{
						player.teleport(expandItem.getLocation());
						return;
					}
					else
					{
						return;
					}
				}
				else
				{
					event.setCancelled(true);
					return;
				}
			}
			catch(NullPointerException e)
			{
				return;
			}
		}
		else
		{
			return;
		}
		
	}
}

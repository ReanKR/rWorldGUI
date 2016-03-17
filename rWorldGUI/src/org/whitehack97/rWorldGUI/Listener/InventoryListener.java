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
		if(rWorldGUI.InventoryInfomation.containsKey(inventory))
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
		if(rWorldGUI.InventoryInfomation.containsKey(inventory))
		{
			if(rWorldGUI.InventoryUtil.containsKey(event.getClickedInventory()))
			{
				if(!(event.getCurrentItem().getType() == Material.AIR) && rWorldGUI.InventoryInfomation.get(event.getClickedInventory()).containsKey(event.getSlot()))
				{
					ExpandItem expandItem = rWorldGUI.InventoryInfomation.get(event.getClickedInventory()).get(event.getSlot());
					if(expandItem.getType().equalsIgnoreCase("CLOSE"))
					{
						event.setCancelled(true);
						player.closeInventory();
						return;
					}
					else if(expandItem.getType().equalsIgnoreCase("WORLD_INFORMATION"))
					{
						event.setCancelled(true);
						player.teleport(expandItem.getLocation());
						return;
					}
				}
				else
				{
					event.setCancelled(true);
					return;
				}
			}
			else
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

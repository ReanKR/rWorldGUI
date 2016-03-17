package org.whitehack97.rWorldGUI.Listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.whitehack97.rWorldGUI.Util.SubManager;
import org.whitehack97.rWorldGUI.api.ExpandItem;
import org.whitehack97.rWorldGUI.main.rWorldGUI;

public class RefreshInventory implements Runnable
{
	@Override
	public void run()
	{
		for(Inventory inventory : rWorldGUI.InventoryInfomation.keySet())
		{
			for(Integer Index : rWorldGUI.InventoryInfomation.get(inventory).keySet())
			{
				ExpandItem eItem = rWorldGUI.InventoryInfomation.get(inventory).get(Index);
				ItemStack itemStack = eItem.getItemStack();
				ItemMeta ItemMeta = itemStack.getItemMeta();
				
				if(eItem.getType().equalsIgnoreCase("WORLD_INFORMATION"))
				{
					if(ItemMeta.hasDisplayName())
					{
						ItemMeta.setDisplayName(SubManager.RepWorldAmounts(eItem.getLocation().getWorld(), ItemMeta.getDisplayName()));
						if(ItemMeta.getDisplayName().contains("%world%"));
						{
							ItemMeta.setDisplayName(SubManager.RepWorldName(eItem.getLocation().getWorld(), ItemMeta.getDisplayName()));
							
						}
					}
					if(ItemMeta.hasLore())
					{
						List<String> repLore = new ArrayList<String>();
						for(String Str : ItemMeta.getLore())
						{
							repLore.add(SubManager.RepWorldAmounts(eItem.getLocation().getWorld(), Str));
						}
						ItemMeta.setLore(repLore);
					}
					itemStack.setItemMeta(ItemMeta);
					if(eItem.getLocation().getWorld().getPlayers().size() != 0 || eItem.getLocation().getWorld().getPlayers().size() == 1)
					{
						itemStack.setAmount(1);
					}
					else
					{
						itemStack.setAmount(eItem.getLocation().getWorld().getPlayers().size());
					}
					
					itemStack.setItemMeta(ItemMeta);
					inventory.setItem(Index, itemStack);
				}
			}
		}
	}
}

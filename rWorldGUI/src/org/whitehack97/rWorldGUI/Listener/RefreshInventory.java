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
		for(Inventory inventory : rWorldGUI.InventoryInformation.keySet())
		{
			for(Integer Index : rWorldGUI.InventoryInformation.get(inventory).keySet())
			{
				ExpandItem eItem = rWorldGUI.InventoryInformation.get(inventory).get(Index);
				ItemStack itemStack = eItem.getItemStack();
				ItemMeta ItemMeta = itemStack.getItemMeta();
				
				if(eItem.getType().equalsIgnoreCase("WORLD_INFORMATION"))
				{
					if(ItemMeta.hasDisplayName())
					{
						ItemMeta.setDisplayName(SubManager.RepWorldAmounts(eItem.getLocation().getWorld(), ItemMeta.getDisplayName()));
					}
					if(ItemMeta.hasLore())
					{
						List<String> repLore = new ArrayList<String>();
						for(String Str : eItem.getLore())
						{
							repLore.add(SubManager.RepWorldAmounts(eItem.getLocation().getWorld(), Str));
						}
						ItemMeta.setLore(repLore);
					}
					itemStack.setItemMeta(ItemMeta);
					if(eItem.getLocation().getWorld().getPlayers().size() >= 0 && eItem.getLocation().getWorld().getPlayers().size() <= 63)
					{
						itemStack.setAmount(eItem.getLocation().getWorld().getPlayers().size());
					}
					else
					{
						if(eItem.getLocation().getWorld().getPlayers().size() > 63)
						{
							itemStack.setAmount(64);
						}
					}
					
					itemStack.setItemMeta(ItemMeta);
					inventory.setItem(Index, itemStack);
				}
			}
		}
	}
}

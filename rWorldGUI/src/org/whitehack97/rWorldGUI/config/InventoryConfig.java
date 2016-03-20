package org.whitehack97.rWorldGUI.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import org.whitehack97.rWorldGUI.Util.SubManager;
import org.whitehack97.rWorldGUI.api.ExpandInventory;
import org.whitehack97.rWorldGUI.api.ExpandItem;
import org.whitehack97.rWorldGUI.main.rWorldGUI;

public class InventoryConfig
{
	@SuppressWarnings("deprecation")
	public static void InventoryLoad()
	{
		File[] dirFile = new File("plugins/rWorldGUI/").listFiles();
		for(File file : dirFile)
		{
			if(file.isFile())
			{
				if(file.getName().endsWith(".yml"))
				{
					YamlConfiguration FileSection = YamlConfiguration.loadConfiguration(file);
					if(FileSection.contains("InventoryName"))
					{
						Map<Integer, ExpandItem> Items = new HashMap<Integer, ExpandItem>();
						int size = 9;
						boolean SoundEnabled = true;
						String OpenCommand = null;
						if(FileSection.contains("InventoryRows")) size = FileSection.getInt("InventoryRows") * 9;
						if(FileSection.contains("OpenCommand")) OpenCommand = FileSection.getString("OpenCommand");
						if(FileSection.contains("SoundEnabled")) SoundEnabled = FileSection.getBoolean("SoundEnabled");
						Inventory NewInventory = Bukkit.createInventory(null, size, SubManager.RepColor(FileSection.getString("InventoryName")));
						ExpandInventory InventoryInfo = new ExpandInventory(NewInventory, file.getAbsolutePath());
						InventoryInfo.setOpenCommand(OpenCommand);
						InventoryInfo.setEnableSound(SoundEnabled);
						
						for(String index : FileSection.getKeys(false))
						{
							if(FileSection.contains(index + ".ID"))
							{
								ExpandItem Item = new ExpandItem(index);
								byte data = 0;
								int amounts = 1;
								int px = 0, py =0;
								String Name = SubManager.RepColor("&arWorldGUI");
								if(FileSection.contains(index + ".DATA-VALUE")) data = Byte.parseByte(FileSection.getString(index + ".DATA-VALUE"));
								if(FileSection.contains(index + ".AMOUNTS")) amounts = FileSection.getInt(index + ".AMOUNTS");
								ItemStack itemStack = new MaterialData(FileSection.getInt(index + ".ID"), data).toItemStack(amounts);
								ItemMeta itemMeta = itemStack.getItemMeta();
								List<String> Lore = new ArrayList<String>();
								String Type = "WORLD_INFORMATION";
								if(FileSection.contains(index + ".NAME")) Name = SubManager.RepColor(FileSection.getString(index + ".NAME"));
								if(FileSection.contains(index + ".LORE"))
								{
									Lore = SubManager.RepColorList(FileSection.getStringList(index + ".LORE"));
									Item.setLore(FileSection.getStringList(index + ".LORE"));
								}
								if(FileSection.contains(index + ".ETC")) Type = FileSection.getString(index + ".ETC");
								else Type = "NONE";
								if(FileSection.contains(index + ".POSITION-X") && FileSection.contains(index + ".POSITION-Y"))
								{
									px = FileSection.getInt(index + ".POSITION-X");
									py = FileSection.getInt(index + ".POSITION-Y");
								}
								if(px == 0 || py == 0)
								{
									Bukkit.getConsoleSender().sendMessage("Return c");
									// Missing Position
									continue;
								}
								else
								{
									itemMeta.setLore(Lore);
									itemMeta.setDisplayName(Name);
									itemStack.setItemMeta(itemMeta);
									if(Type.equalsIgnoreCase("COMMAND"))
									{
										Item.setComamnd(FileSection.getString(index + ".COMMAND").toLowerCase());
										Item.setType("COMMAND");
									}
									else if(Type.equalsIgnoreCase("CLOSE"))
									{
										Item.setType("CLOSE");
									}
									else if(Type.equalsIgnoreCase("NONE"))
									{
										Item.setType("NONE");
									}
									else if(Type.equalsIgnoreCase("WORLD_INFORMATION"))
									{
										if(FileSection.contains(index + ".LOCATION"))
										{
											World world = null;
											double x, y, z;
											float Pitch, Yaw;
											Item.setType("WORLD_INFORMATION");
											if(FileSection.contains(index + ".LOCATION.WORLD"))
											{
												try
												{
												world = Bukkit.getServer().getWorld(FileSection.getString(index + ".LOCATION.WORLD"));
												}
												catch(NullPointerException e)
												{
													e.printStackTrace();
													// world does not exist on server.
													continue;
												}
											}
											else
											{
												// world missing
												continue;
											}
											
											if(FileSection.contains(index + ".LOCATION.X"))
											{
												x = FileSection.getDouble(index + ".LOCATION.X");
											}
											else
											{
												// X missing
												continue;
											}
											
											if(FileSection.contains(index + ".LOCATION.Y"))
											{
												y = FileSection.getDouble(index + ".LOCATION.Y");
											}
											else
											{
												// Y missing
												continue;
											}
											
											if(FileSection.contains(index + ".LOCATION.Z"))
											{
												z = FileSection.getDouble(index + ".LOCATION.Z");
											}
											else
											{
												// Z missing
												continue;
											}
											
											if(FileSection.contains(index + ".LOCATION.PITCH"))
											{
												Pitch = Float.parseFloat(FileSection.getString(index + ".LOCATION.PITCH"));
											}
											else
											{
												// PITCH missing
												continue;
											}
											
											if(FileSection.contains(index + ".LOCATION.YAW"))
											{
												Yaw = Float.parseFloat(FileSection.getString(index + ".LOCATION.YAW"));
											}
											else
											{
												// YAW missing
												continue;
											}
											Location location = new Location(world, x, y, z, Yaw, Pitch);
											Item.setLocation(location);
										}
										else
										{
											// Item type is WorldInformation method, but location missing
											continue;
										}
									}
								}
								Item.setItemStack(itemStack);
								NewInventory.setItem(((px - 1)+((py - 1)* 9)), itemStack);
								Items.put(((px - 1)+((py - 1)* 9)), Item);
							}
							else
							{
								// the index is not inventory, continue searching next information.
								continue;
							}
						}
						rWorldGUI.InventoryInformation.put(NewInventory, Items);
						rWorldGUI.InventoryUtil.put(NewInventory, InventoryInfo);
						rWorldGUI.InventoryCommand.put("/" + InventoryInfo.getOpenCommand().toLowerCase(), NewInventory);
						rWorldGUI.InventoryYaml.put(file.getName().replaceAll(".yml", "").toLowerCase(), NewInventory);
					}
					else
					{
						// file is not Yaml file
						continue;
					}
				}
				else
				{
					continue;
				}
			}
			else
			{
				continue;
			}
		}
	}
}

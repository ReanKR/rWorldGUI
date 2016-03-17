package org.whitehack97.rWorldGUI.Util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SubManager
{
	public static String RepColor(String Str)
	{
		return ChatColor.translateAlternateColorCodes('&', Str);
	}

	public static List<String> RepColorList(List<String> stringList)
	{
		List<String> RepList = new ArrayList<String>();
		for(String Str : stringList)
		{
			RepList.add(RepColor(Str));
		}
		
		return RepList;
	}
	
	public static String RepWorldAmounts(World world, String Str)
	{
		int PlayerAmount = world.getPlayers().size();
		String replace = Str.replaceAll("%world_player%", String.valueOf(PlayerAmount));
		return replace;
	}
	
	public static String RepPlayerName(Player player, String Str)
	{
		return Str.replaceAll("%world_player%", player.getName());
	}

	public static String RepWorldName(World world, String displayName)
	{
		return displayName.replaceAll(world.getName(), displayName);
	}
}

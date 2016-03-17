package org.whitehack97.rWorldGUI.Util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.whitehack97.rWorldGUI.main.rWorldGUI;

public class MessageManager
{
	public static String RepColor(String msg)
	{
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static List<String> RepColorList(List<String> List)
	{
		List<String> RepList = new ArrayList<String>();
		for(String Str : List)
		{
			RepList.add(RepColor(Str));
		}
		return RepList;
	}
	
	public static void Msg(Player p, String msg)
	{
		p.sendMessage(rWorldGUI.Prefix + RepColor(msg));
	}
}

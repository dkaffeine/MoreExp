package me.dkaffeine.moreexp.io;

import java.util.logging.Level;
import java.util.logging.Logger;

import me.dkaffeine.moreexp.Settings;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Print {
	public static final Logger log = Logger.getLogger("Minecraft");
	public static final String Version = "3.3";
	
	public static void Output(CommandSender sender, String message)
	{
		sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp v" + Version + ChatColor.WHITE + "] " + message);
	}

	public static void OutputERROR(CommandSender sender, String message)
	{
		sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp v" + Version  + ChatColor.WHITE + "] " + ChatColor.RED + message);
	}

	public static void OutputLog(String message)
	{
		log.log(Level.INFO,"[MoreExp v" + Version + "] " + message);
	}

	public static void OutputLogERROR(String message)
	{
		log.log(Level.SEVERE,"[MoreExp v" + Version  + "] " + message);
	}
	
	public static void DebugLog(String message)
	{
		if (Settings.getOption("DebugFlag") == false)
		{
			return;
		}
		log.log(Level.INFO, "[MoreExp v" + Version + "](DEBUG) " + message);
	}
}

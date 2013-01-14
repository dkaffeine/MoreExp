package me.dkaffeine.moreexp.listeners;

import me.dkaffeine.moreexp.Settings;
import me.dkaffeine.moreexp.io.Print;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Block implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event)
	{
		if (Settings.getOption("DisableOreFlag") == true)
		{
			event.setExpToDrop(0);
			return;
		}
		
		// If a player does not have granted permissions, return to the void (vanilla drops)
		if (event.getPlayer().hasPermission("moreexp.usr.block") == false)
		{
			return;
		}
		
		Print.DebugLog(event.getPlayer().getDisplayName() + " broke block " + String.valueOf(event.getBlock().getTypeId()) + "(" +  event.getBlock().getType().toString() + ")");
		if (event.getBlock().getTypeId() == 56) // Diamond ore
		{
			event.setExpToDrop(Settings.getXP("DiamondOre"));
			return;
		}
		if (event.getBlock().getTypeId() == 129) // Emerald ore
		{
			event.setExpToDrop(Settings.getXP("EmeraldOre"));
			return;
		}
		if (event.getBlock().getTypeId() == 16) // Coal ore
		{
			event.setExpToDrop(Settings.getXP("CoalOre"));
			return;
		}
		if (event.getBlock().getTypeId() == 15) // Iron ore
		{
			event.setExpToDrop(Settings.getXP("IronOre"));
			return;
		}
		if (event.getBlock().getTypeId() == 14) // Gold ore
		{
			event.setExpToDrop(Settings.getXP("GoldOre"));
			return;
		}
		if (event.getBlock().getTypeId() == 73 || event.getBlock().getTypeId() == 74) // Redstone ore
		{
			event.setExpToDrop(Settings.getXP("RedstoneOre"));
			return;
		}
		if (event.getBlock().getTypeId() == 21) // Lapis-lazuli ore
		{
			event.setExpToDrop(Settings.getXP("LapisOre"));
			return;
		}
		if (event.getBlock().getTypeId() == 89) // Glowstone
		{
			event.setExpToDrop(Settings.getXP("Glowstone"));
			return;
		}
		String CustomOre = "CustomOre"+String.valueOf(event.getBlock().getTypeId());
		String CustomOreRand = CustomOre+"Rand";
		if (Settings.containsOption(CustomOre) && Settings.containsOption(CustomOreRand))
		{
			event.setExpToDrop(Settings.getXP(CustomOre));
		}

		event.setExpToDrop(0);
		return;
	}
	
}

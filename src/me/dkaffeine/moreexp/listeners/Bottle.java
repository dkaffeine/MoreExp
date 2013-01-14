package me.dkaffeine.moreexp.listeners;

import me.dkaffeine.moreexp.Settings;
import me.dkaffeine.moreexp.io.Print;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

public class Bottle implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onExpBottle(ExpBottleEvent event)
	{
		if (Settings.getOption("DisableBottleFlag") == true)
		{
			event.setExperience(0);
			return;
		}
		
		Print.DebugLog("Thrown experience bottle in world " + event.getEntity().getWorld().getName());
		event.setExperience(Settings.getXP("Bottle"));
	}
}

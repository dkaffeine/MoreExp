package me.dkaffeine.moreexp;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

public class ExperienceListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onExpBottle(ExpBottleEvent event)
	{
		if (MoreExpSettings.DisableBottleFlag)
		{
			return;
		}
		event.setExperience(MoreExpSettings.ComputeXP(MoreExpSettings.BottleExp, MoreExpSettings.BottleExpRand));
		if (MoreExpSettings.DebugFlag == true)
		{
			MoreExpSettings.OutputLog("Thrown experience bottle in world " + event.getEntity().getWorld().getName());
		}
	}
}

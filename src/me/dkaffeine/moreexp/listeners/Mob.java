package me.dkaffeine.moreexp.listeners;

import me.dkaffeine.moreexp.Settings;
import me.dkaffeine.moreexp.io.Print;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.*;

public class Mob implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDeath(EntityDeathEvent event)
	{
		// If this flag is activated, then nothing is done
		if (Settings.getOption("DisableMobFlag") == true)
		{
			return;
		}
		
		// If this flag is activated, only "vanilla" drop method occurs, except when a player dies
		if (Settings.getOption("DropsOnlyFromPlayers") == true)
		{
			if (event.getDroppedExp() == 0 && !(event.getEntity() instanceof Player))
			{
				return;
			}
		}

		Entity deadmob = event.getEntity();
		// If a player does not have granted permissions, return to the void
		if ( deadmob.getLastDamageCause().getEntity() instanceof Player)
		{
			if ( ((Player) deadmob.getLastDamageCause().getEntity()).hasPermission("moreexp.usr.mob")==false)
			{
				Print.DebugLog("Player " + ((Player) deadmob.getLastDamageCause().getEntity()).getName() + " does not have permission to get custom experience.");
				return;
			}
		}
		
		Print.DebugLog("Entity " + deadmob.getClass().getName() + " has been slain in world " + deadmob.getWorld().getName());
		
		if (deadmob instanceof Creeper) {
			event.setDroppedExp(Settings.getXP("Creeper"));
			return;
		}
		if (deadmob instanceof Wither)
		{
			event.setDroppedExp(Settings.getXP("Wither"));
			return;
		}
		if (deadmob instanceof Witch)
		{
			event.setDroppedExp(Settings.getXP("Witch"));
			return;
		}
		if (deadmob instanceof Blaze) {
			event.setDroppedExp(Settings.getXP("Blaze"));
			return;
		}
		if (deadmob instanceof Giant) {
			event.setDroppedExp(Settings.getXP("Giant"));
			return;
		}
		if (deadmob instanceof EnderDragon) {
			event.setDroppedExp(Settings.getXP("EnderDragon"));
			return;
		}
		if (deadmob instanceof Silverfish) {
			event.setDroppedExp(Settings.getXP("Silverfish"));
			return;
		}
		if (deadmob instanceof MagmaCube) {
			Print.DebugLog(String.valueOf(((MagmaCube) deadmob).getSize()));
			event.setDroppedExp(Settings.getXP("MagmaCube"));
			return;
		}
		if (deadmob instanceof MushroomCow) {
			event.setDroppedExp(Settings.getXP("MushroomCow"));
			return;
		}
		if (deadmob instanceof Slime) {
			Print.DebugLog(String.valueOf(((Slime) deadmob).getSize()));
			event.setDroppedExp(Settings.getXP("Slime"));
			return;
		}
		if (deadmob instanceof Squid) {
			event.setDroppedExp(Settings.getXP("Squid"));
			return;
		}
		if (deadmob instanceof Ocelot) {
			event.setDroppedExp(Settings.getXP("Ocelot"));
			return;
		}
		if (deadmob instanceof IronGolem) {
			event.setDroppedExp(Settings.getXP("IronGolem"));
			return;
		}
		if (deadmob instanceof Snowman) {
			event.setDroppedExp(Settings.getXP("Snowman"));
			return;
		}
		if (deadmob instanceof PigZombie) {
			event.setDroppedExp(Settings.getXP("ZombiePigman"));
			return;
		}
		if (deadmob instanceof Enderman) {
			event.setDroppedExp(Settings.getXP("Enderman"));
			return;
		}
		if (deadmob instanceof Zombie) {
			event.setDroppedExp(Settings.getXP("Zombie"));
			return;
		}
		if (deadmob instanceof Skeleton) {
			event.setDroppedExp(Settings.getXP("Skeleton"));
			return;
		}
		if (deadmob instanceof CaveSpider) {
			event.setDroppedExp(Settings.getXP("CaveSpider"));
			return;
		}
		if (deadmob instanceof Spider) {
			event.setDroppedExp(Settings.getXP("Spider"));
			return;
		}
		if (deadmob instanceof Pig) {
			event.setDroppedExp(Settings.getXP("Pig"));
			return;
		}
		if (deadmob instanceof Cow) {
			event.setDroppedExp(Settings.getXP("Cow"));
			return;
		}
		if (deadmob instanceof Sheep) {
			event.setDroppedExp(Settings.getXP("Sheep"));
			return;
		}
		if (deadmob instanceof Chicken) {
			event.setDroppedExp(Settings.getXP("Chicken"));
			return;
		}
		if (deadmob instanceof Wolf) {
			event.setDroppedExp(Settings.getXP("Wolf"));
			return;
		}
		if (deadmob instanceof Ghast) {
			event.setDroppedExp(Settings.getXP("Ghast"));
			return;
		}
		if (deadmob instanceof Villager) {
			event.setDroppedExp(Settings.getXP("Villager"));
			return;
		}
		if (deadmob instanceof Player) {
			event.setDroppedExp(event.getDroppedExp() + Settings.getXP("Player"));
			return;
		}
		Print.OutputLogERROR("Experience method for entity " + deadmob.getClass().getName() + " is not implemented.");
	}
	
}

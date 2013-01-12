package me.dkaffeine.moreexp;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobListener implements Listener {

	private Entity mob;

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDeath(EntityDeathEvent event)
	{
		if (MoreExpSettings.DisableMobFlag)
		{
			return;
		}
		
		if (MoreExpSettings.DropsOnlyFromPlayers)
		{
			if (event.getDroppedExp() == 0 && !(event.getEntity() instanceof Player) )
			{
				return;	// nothing to modify!
			}
		}
		
		mob = event.getEntity();
		
		if (MoreExpSettings.DebugFlag == true)
		{
			MoreExpSettings.OutputLog("Monster" + mob.getClass().getName() + " slain on world " + mob.getWorld().getName() );
		}
		
		if (mob instanceof Creeper){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.CreeperExp, MoreExpSettings.CreeperExpRand));
		}		
		else if (mob instanceof Blaze){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.BlazeExp, MoreExpSettings.BlazeExpRand));
		}
		else if (mob instanceof Giant){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.GiantExp, MoreExpSettings.GiantExpRand));
		}
		else if (mob instanceof EnderDragon){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.EnderDragonExp, MoreExpSettings.EnderDragonExpRand));
		}
		else if (mob instanceof Silverfish){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.SilverfishExp, MoreExpSettings.SilverfishExpRand));
		}
		else if (mob instanceof MagmaCube){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.MagmaCubeExp, MoreExpSettings.MagmaCubeExpRand));
		}
		else if (mob instanceof MushroomCow){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.MushroomCowExp, MoreExpSettings.MushroomCowExpRand));
		}
		else if (mob instanceof Slime){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.SlimeExp, MoreExpSettings.SlimeExpRand));
		}
		else if (mob instanceof Squid){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.SquidExp, MoreExpSettings.SquidExpRand));
		}
		else if (mob instanceof Ocelot){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.OcelotExp, MoreExpSettings.OcelotExpRand));
		}
		else if (mob instanceof IronGolem){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.IronGolemExp, MoreExpSettings.IronGolemExpRand));
		}
		else if (mob instanceof Snowman){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.SnowmanExp, MoreExpSettings.SnowmanExpRand));
		}
		else if (mob instanceof PigZombie){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.PigZombieExp, MoreExpSettings.PigZombieExpRand));
		}
		else if (mob instanceof Enderman){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.EnderExp, MoreExpSettings.EnderExpRand));
		}
		else if (mob instanceof Zombie){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.ZombieExp, MoreExpSettings.ZombieExpRand));
		}
		else if (mob instanceof Skeleton){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.SkeletonExp, MoreExpSettings.SkeletonExpRand));
		}
		else if (mob instanceof CaveSpider){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.CaveSpiderExp, MoreExpSettings.CaveSpiderExpRand));
		}
		else if (mob instanceof Spider){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.SpiderExp, MoreExpSettings.SpiderExpRand));
		}
		else if (mob instanceof Pig){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.PigExp, MoreExpSettings.PigExpRand));
		}
		else if (mob instanceof Cow){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.CowExp, MoreExpSettings.CowExpRand));
		}
		else if (mob instanceof Sheep){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.SheepExp, MoreExpSettings.SheepExpRand));
		}
		else if (mob instanceof Chicken){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.ChickenExp, MoreExpSettings.ChickenExpRand));
		}
		else if (mob instanceof Wolf){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.WolfExp, MoreExpSettings.WolfExpRand));
		}
		else if (mob instanceof Ghast){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.GhastExp, MoreExpSettings.GhastExpRand));
		}
		else if (mob instanceof Villager){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.VillagerExp, MoreExpSettings.VillagerExpRand));
		}
		else if (mob instanceof Witch){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.WitchExp, MoreExpSettings.WitchExpRand));
		}
		else if (mob instanceof Wither){
			event.setDroppedExp(MoreExpSettings.ComputeXP(MoreExpSettings.WitherExp, MoreExpSettings.WitherExpRand));
		}
	}
}

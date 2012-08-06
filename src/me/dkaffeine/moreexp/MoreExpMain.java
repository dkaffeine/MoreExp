package me.dkaffeine.moreexp;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MoreExpMain extends JavaPlugin
{
	static String maindirectory = "plugins/moreexp/";

	private final MobListener moblistener = new MobListener();
	private final ExperienceListener explistener = new ExperienceListener();
	private final BlockListener blocklistener = new BlockListener();
	
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(explistener, this);
		getServer().getPluginManager().registerEvents(moblistener, this);
		getServer().getPluginManager().registerEvents(blocklistener, this);
		MoreExpSettings.OutputLog("Plugin enabled.");
		MoreExpSettings.OutputLog("Developed by Dkaffeine (adrien.semin@gmail.com)");

		new File(maindirectory).mkdir();

		MoreExpSettings.load();
		if (MoreExpSettings.ExperienceMultiplier != 1)
		{
			MoreExpSettings.OutputLog("Experience multiplier set to "+String.valueOf(MoreExpSettings.ExperienceMultiplier) );
		}
	}

	public boolean CheckPermissions(CommandSender sender, String permissiontocheck, boolean displayerror)
	{
		boolean ValidPermission = true;
		if ( (sender instanceof Player) )
		{
			if( !(sender.hasPermission(permissiontocheck)) && !(sender.isOp()) )
			{
				ValidPermission = false;
				if (displayerror)
				{
					MoreExpSettings.OutputERROR(sender, "You don't have permission " + permissiontocheck);
					MoreExpSettings.OutputLogERROR(sender.getName() + " does not have permission " + permissiontocheck);
				}
			}
		}
		return ValidPermission;
	}

	public void Reload(CommandSender sender)
	{
		if (this.CheckPermissions(sender, "moreexp.adm.reload", true))
		{
			MoreExpSettings.load();
			if (sender instanceof Player)
			{
				MoreExpSettings.Output(sender, "reloaded.");
			}
			MoreExpSettings.OutputLog("reloaded.");
		}
	}
	
	public void Save(CommandSender sender)
	{
		if (this.CheckPermissions(sender, "moreexp.adm.save", true))
		{
			MoreExpSettings.save();
			if (sender instanceof Player)
			{
				MoreExpSettings.Output(sender, "configuration saved.");
			}
			MoreExpSettings.OutputLog("configuration saved.");
		}
	}


	public void onDisable()
	{
		MoreExpSettings.save();
		MoreExpSettings.OutputLog("Plugin disabled.");
	}

	public void Usage(CommandSender sender, Command cmd)
	{
		MoreExpSettings.Output(sender, "");
		sender.sendMessage(ChatColor.YELLOW + "----------------------------------------");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " config load/save" + ChatColor.WHITE + " : load/save configuration");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " give player level" + ChatColor.WHITE + " : give XP levels to player");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " info" + ChatColor.WHITE + " : display mob informations");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " reload" + ChatColor.WHITE + " : reload configuration");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " reset" + ChatColor.WHITE + " : reset experience of a player");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " save" + ChatColor.WHITE + " : save configuration from current state");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " set mob XP [XPvariable]" + ChatColor.WHITE + " : set XP value drop for creature mob");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " xpgain value" + ChatColor.WHITE + " : set global XP multiplier");
		sender.sendMessage(ChatColor.YELLOW + "----------------------------------------");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if ( cmd.getName().equalsIgnoreCase("moreexp") || cmd.getName().equalsIgnoreCase("mex") )
		{
			if (args.length == 0)
			{
				this.Usage(sender, cmd);
				return true;
			}
			else
			{
				if (args[0].equalsIgnoreCase("info"))
				{
					// Usage of this command: /moreexp info [mob/option]
					if (args.length == 2)
					{
						if (MoreExpSettings.containsOption(args[1]) && MoreExpSettings.containsOption(args[1].concat("Rand")))
						{
							MoreExpSettings.Output(sender, "Mob information");
							MoreExpSettings.Output(sender, ChatColor.WHITE + "Experience for mob " + ChatColor.GOLD + args[1] + ChatColor.WHITE + " : " + MoreExpSettings.getValue(args[1]) + " - " + ( Integer.parseInt(MoreExpSettings.getValue(args[1])) + Integer.parseInt(MoreExpSettings.getValue(args[1].concat("Rand"))) ));
						}
						else if (MoreExpSettings.containsOption(args[1]))
						{
							MoreExpSettings.Output(sender, "Option information");
							MoreExpSettings.Output(sender, ChatColor.WHITE + "Parameter for mob/option " + ChatColor.GOLD + args[1] + ChatColor.WHITE + " : " + MoreExpSettings.getValue(args[1]) );
						}
						else
						{
							MoreExpSettings.OutputERROR(sender, "[MoreExp] Mob / Option " + args[1] + " is not found.");
						}
					}
					else if (args.length == 1)
					{
						MoreExpSettings.Output(sender, "Mob information.");
						sender.sendMessage(ChatColor.YELLOW + "----------------------------------------");
						sender.sendMessage(ChatColor.WHITE + "Blaze : " + ChatColor.AQUA + MoreExpSettings.BlazeExp);
						sender.sendMessage(ChatColor.WHITE + "Cave Spider : " + ChatColor.AQUA + MoreExpSettings.CaveSpiderExp + ChatColor.WHITE + " Chicken : " + ChatColor.AQUA + MoreExpSettings.ChickenExp + ChatColor.WHITE + " Cow : " + ChatColor.AQUA + MoreExpSettings.CowExp + ChatColor.WHITE + " Creeper : " + ChatColor.AQUA + MoreExpSettings.CreeperExp);
						sender.sendMessage(ChatColor.WHITE + "Enderman : " + ChatColor.AQUA + MoreExpSettings.EnderExp + ChatColor.WHITE + " Ender dragon : " + ChatColor.AQUA + MoreExpSettings.EnderDragonExp + ChatColor.WHITE + " Ghast : " + ChatColor.AQUA + MoreExpSettings.GhastExp + ChatColor.WHITE + " Giant : " + ChatColor.AQUA + MoreExpSettings.GiantExp);
						sender.sendMessage(ChatColor.WHITE + "Iron golem : " + ChatColor.AQUA + MoreExpSettings.IronGolemExp + ChatColor.WHITE + " Magma cube : " + ChatColor.AQUA + MoreExpSettings.MagmaCubeExp + ChatColor.WHITE + " Mushroom cow : " + ChatColor.AQUA + MoreExpSettings.MushroomCowExp);
						sender.sendMessage(ChatColor.WHITE + "Ocelot : " + ChatColor.AQUA + MoreExpSettings.OcelotExp + ChatColor.WHITE + " Pig : " + ChatColor.AQUA + MoreExpSettings.PigExp + ChatColor.WHITE + " Sheep : " + ChatColor.AQUA + MoreExpSettings.SheepExp);
						sender.sendMessage(ChatColor.WHITE + "Silverfish : " + ChatColor.AQUA + MoreExpSettings.SilverfishExp + ChatColor.WHITE + " Skeleton : " + ChatColor.AQUA + MoreExpSettings.SkeletonExp + ChatColor.WHITE + " Slime : " + ChatColor.AQUA + MoreExpSettings.SlimeExp + ChatColor.WHITE + " Snowman : " + ChatColor.AQUA + MoreExpSettings.SnowmanExp);
						sender.sendMessage(ChatColor.WHITE + "Spider : " + ChatColor.AQUA + MoreExpSettings.SpiderExp + ChatColor.WHITE + " Squid : " + ChatColor.AQUA + MoreExpSettings.SquidExp + ChatColor.WHITE + " Villager : " + ChatColor.AQUA + MoreExpSettings.VillagerExp + ChatColor.WHITE + " Wolf : " + ChatColor.AQUA + MoreExpSettings.WolfExp);
						sender.sendMessage(ChatColor.WHITE + "Zombie : " + ChatColor.AQUA + MoreExpSettings.ZombieExp + ChatColor.WHITE + " Zombie Pigman : " + ChatColor.AQUA + MoreExpSettings.PigZombieExp);
					}
					else
					{
						MoreExpSettings.OutputERROR(sender, "[MoreExp] Usage : /" + cmd.getName() + " info [mob]");
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("reload"))
				{
					this.Reload(sender);
					return true;
				}
				if (args[0].equalsIgnoreCase("save"))
				{
					this.Save(sender);
					return true;
				}
				if (args[0].equalsIgnoreCase("config"))
				{
					if (args.length == 2)
					{	
						if (args[1].equalsIgnoreCase("load"))
						{
							this.Reload(sender);
							return true;
						}
						if (args[1].equalsIgnoreCase("save"))
						{
								this.Save(sender);
							return true;
						}
					}
					MoreExpSettings.OutputERROR(sender, "[MoreExp] Usage : /" + cmd.getName() + " config load|save");
					return true;
				}
				if (args[0].equalsIgnoreCase("give"))
				{
					// Usage of command : /moreexp give player exp_lvl_value
					if (this.CheckPermissions(sender, "moreexp.adm.give", true))
					{
						if (args.length == 3)
						{
							Player receiver = Bukkit.getServer().getPlayer(args[1]);
							int givenlvl = Integer.parseInt(args[2]);
							if (receiver == null)
							{
								MoreExpSettings.OutputERROR(sender, "Player " + args[1] + " seems to be offline.");
							}
							else
							{
								int currentlvl = receiver.getLevel();
								receiver.setLevel(currentlvl + givenlvl);
								if (sender instanceof Player)
								{
									MoreExpSettings.Output(sender, "You have gived " + args[2] + " XP levels to " + args[1] );
									MoreExpSettings.Output(receiver, "You have received " + args[2] + " XP levels  from " + sender.getName() );
									MoreExpSettings.OutputLog(sender.getName() + " give " + args[2] + " XP levels to " + args[1] );
								}
								else
								{
									MoreExpSettings.Output(receiver, "You have received " + args[2] + " XP levels  from the console." );
								}
							}
						}
						else
						{
							MoreExpSettings.OutputERROR(sender, "Usage : /" + cmd.getName() + " give player exp_lvl_value");
						}
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("reset"))
				{
					// Usage of command : /moreexp reset player
					if (this.CheckPermissions(sender, "moreexp.adm.reset", true))
					{
						if (args.length == 2)
						{
							Player receiver = Bukkit.getServer().getPlayer(args[1]);
							if (receiver == null)
							{
								sender.sendMessage(ChatColor.DARK_RED + "[MoreExp] Player " + args[1] + " seems to be offline.");
							}
							else
							{
								receiver.setExp(0);
								receiver.setLevel(0);
								if (sender instanceof Player)
								{
									sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp" + ChatColor.WHITE + "] You just have reset levels of " + args[1]);
									receiver.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp" + ChatColor.WHITE + "] " + sender.getName() + " has reset your level.");
									MoreExpSettings.OutputLog(sender.getName() + " reset levels of " + args[1] );
								}
								else
								{
									receiver.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp" + ChatColor.WHITE + "] admin from console has reset your level.");
								}
							}
						}
						else
						{
							sender.sendMessage(ChatColor.DARK_RED + "[MoreExp] Usage : /" + cmd.getName() + " reset player");
						}
					}
					return true;
					
				}
				if (args[0].equalsIgnoreCase("set"))
				{
					// Usage of command : /moreexp set mob_value exp_value
					if (this.CheckPermissions(sender, "moreexp.adm.set", true))
					{
						if (args.length == 3)
						{
							String mob = args[1];
							int XP = Integer.parseInt(args[2]);
							if (MoreExpSettings.containsOption(mob))
							{
								MoreExpSettings.setMobXP(mob, XP);
								if (sender instanceof Player)
								{
									MoreExpSettings.Output(sender, "You have set " + args[1] + " XP drop to " + args[2] );
								}
								MoreExpSettings.OutputLog(sender.getName() + " sets " + args[1] + " XP drop to " + args[2] );
							}
							else
							{
								MoreExpSettings.OutputERROR(sender, "Mob " + mob + " does not exist.");
							}
						}
						else if (args.length == 4)
						{
							String mob = args[1];
							int XP = Integer.parseInt(args[2]);
							int XPvariable = Integer.parseInt(args[3]);
							if (MoreExpSettings.containsOption(mob))
							{
								MoreExpSettings.setMobXPVariable(mob, XP, XPvariable);
								if (sender instanceof Player)
								{
									MoreExpSettings.Output(sender, "You have set " + args[1] + " XP drop to " + args[2] + "-" + String.valueOf(Integer.parseInt(args[2])+Integer.parseInt(args[3]) ) );
								}
								MoreExpSettings.OutputLog(sender.getName() + " sets " + args[1] + " XP drop to " + args[2] + "-" + String.valueOf(Integer.parseInt(args[2])+Integer.parseInt(args[3]) ) );
							}
							else
							{
								sender.sendMessage(ChatColor.DARK_RED + "[MoreExp] Mob " + mob + " does not exist.");
							}
						}
						else
						{
							MoreExpSettings.OutputERROR(sender, "Usage : /" + cmd.getName() + " set mob_value exp_value [exp_value_variable]");
						}
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("xpgain"))
				{
					if (this.CheckPermissions(sender, "moreexp.adm.xpgain", true))
					{
						if (args.length == 2)
						{
							int experiencemultiplier = Integer.parseInt(args[1]);
							if (experiencemultiplier < 1)
							{
								experiencemultiplier = 1;
							}
							MoreExpSettings.setOption("ExperienceMultiplier", experiencemultiplier);
							Bukkit.getServer().broadcastMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp" + ChatColor.WHITE + "] v" + this.getDescription().getVersion() + " experience multiplier set to " + ChatColor.GOLD + experiencemultiplier);
						}
						else
						{
							MoreExpSettings.OutputERROR(sender, "Usage : /" + cmd.getName() + " xpgain value");
						}
					}
					return true;
				}
			}
		}
		Usage(sender, cmd);
		return true;
	}
}

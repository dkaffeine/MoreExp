package me.dkaffeine.moreexp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.dkaffeine.moreexp.listeners.Block;
import me.dkaffeine.moreexp.listeners.Bottle;
import me.dkaffeine.moreexp.listeners.Mob;
import me.dkaffeine.moreexp.io.Print;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MoreExp extends JavaPlugin {

	static String maindirectory = "plugins/moreexp/";

	static boolean DisableMobFlag;

	private final Mob MobListener = new Mob();
	private final Block BlockListener = new Block();
	private final Bottle BottleListener = new Bottle();

	public void onEnable()
	{
		Print.OutputLog("Plugin enabled.");
		Print.OutputLog("Developed by Dkaffeine (adrien.semin@gmail.com).");
		new File(maindirectory).mkdir();
		Settings.load();
		DisableMobFlag = Settings.getOption("DisableMobFlag");
		if (DisableMobFlag == false)
		{
			Print.OutputLog("Register Mob listener");
			getServer().getPluginManager().registerEvents(MobListener, this);
		}
		Print.OutputLog("Register Block listener");
		getServer().getPluginManager().registerEvents(BlockListener, this);
		Print.OutputLog("Register Bottle listener");
		getServer().getPluginManager().registerEvents(BottleListener, this);
		if (Settings.getValue("ExperienceMultiplier") != 1)
		{
			Print.OutputLog("Experience multiplier set to " + String.valueOf(Settings.getValue("ExperienceMultiplier")));
		}
	}

	public void onDisable()
	{
		Settings.save();
		Print.OutputLog("Plugin disabled.");
	}

	public void Reload(CommandSender sender)
	{
		if (this.CheckPermissions(sender, "moreexp.adm.reload") == false)
			return;
		Settings.load();
		if (DisableMobFlag == true && Settings.getOption("DisableMobFlag") == false)
		{
			Print.OutputLog("Register Mob listener");
			this.getServer().getPluginManager().registerEvents(MobListener, this);
		}
		if (sender instanceof Player)
		{
			Print.Output(sender, "Plugin reloaded.");
		}
		Print.OutputLog("Plugin reloaded.");
	}

	public void Save(CommandSender sender)
	{
		if (this.CheckPermissions(sender, "moreexp.adm.save") == false)
			return;
		Settings.save();
		if (sender instanceof Player)
		{
			Print.Output(sender, "Plugin configuration saved.");
		}
		Print.OutputLog("Plugin configuration saved.");

	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		return this.CallCommand(sender, cmd, commandLabel, args);
	}

	public static void OnUsage(CommandSender sender, Command cmd)
	{
		Print.Output(sender, "");
		sender.sendMessage(ChatColor.YELLOW + "----------------------------------------");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " config load/save" + ChatColor.WHITE + " : load/save configuration");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " give player level" + ChatColor.WHITE + " : give XP levels to player");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " info" + ChatColor.WHITE + " : display information");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " reinit" + ChatColor.WHITE + " : revert plugin to default state");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " reload" + ChatColor.WHITE + " : reload configuration");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " reset" + ChatColor.WHITE + " : reset experience of a player");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " save" + ChatColor.WHITE + " : save configuration from current state");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " set mob XP [XPvariable]" + ChatColor.WHITE + " : set XP value drop for creature mob");
		sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " xpgain value" + ChatColor.WHITE + " : set global XP multiplier");
		sender.sendMessage(ChatColor.YELLOW + "----------------------------------------");
	}

	public boolean CheckPermissions(CommandSender sender, String permissiontocheck)
	{
		boolean ValidPermission = true;

		// Check permissions only if sender is not the console
		if (sender instanceof Player)
		{
			if ( !(sender.hasPermission(permissiontocheck)) && !(sender.isOp()) )
			{
				ValidPermission = false;
				Print.OutputLogERROR(sender.getName() + " does not have permission " + permissiontocheck);
			}
		}
		return ValidPermission;
	}

	public boolean CallCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if ( !(cmd.getName().equalsIgnoreCase("moreexp")) && !(cmd.getName().equalsIgnoreCase("mex")) )
		{
			return false;
		}

		if (args.length == 0)
		{
			MoreExp.OnUsage(sender, cmd);
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

		if (args[0].equalsIgnoreCase("reinit"))
		{
			if (this.CheckPermissions(sender, "moreexp.sadm.reinit") == true)
			{
				String outputFile = Settings.propertiesFile;
				Date savedate = new Date();
				SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddkkmmss");
				outputFile = outputFile + ".save" + formatdate.format(savedate);
				Settings.save(outputFile);
				Print.OutputLog("Configuration reverted to default state by " + sender.getName());
				Settings.reinit();
				return true;
			}
			else
			{
				Print.OutputERROR(sender, "Sorry, you don't have permission.");
			}
		}

		if (args[0].equalsIgnoreCase("config"))
		{
			if (args.length == 2)
			{
				if (args[1].equalsIgnoreCase("reload"))
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
			Print.OutputERROR(sender, "Usage: /" + cmd.getName() + " config reload|save");
			return true;
		}

		if (args[0].equalsIgnoreCase("info"))
		{
			if (args.length == 2)
			{
				if (args[1].equalsIgnoreCase("ore"))
				{
					if (Settings.getOption("DisableOreFlag") == false)
					{
						Print.Output(sender, ChatColor.GOLD + "Ore information");
						Print.Output(sender, Settings.printxp("CoalOre"));
						Print.Output(sender, Settings.printxp("DiamondOre"));
						Print.Output(sender, Settings.printxp("EmeraldOre"));
						Print.Output(sender, Settings.printxp("GoldOre"));
						Print.Output(sender, Settings.printxp("IronOre"));
						Print.Output(sender, Settings.printxp("LapisOre"));
						Print.Output(sender, Settings.printxp("RedstoneOre"));
						return true;
					}
					else
					{
						Print.Output(sender, "Ore experience drop is disabled.");
						return true;
					}
				}
				if (args[1].equalsIgnoreCase("bottle"))
				{
					if (Settings.getOption("DisableBottleFlag") == false)
					{
						Print.Output(sender, ChatColor.GOLD + "Bottle o'enchanting");
						Print.Output(sender, Settings.printxp("Bottle"));
						return true;
					}
					else
					{
						Print.Output(sender, "Bottle experience drop is disabled.");
						return true;
					}
				}
				if (args[1].equalsIgnoreCase("mob"))
				{
					if (Settings.getOption("DisableMobFlag") == false)
					{
						Print.Output(sender, ChatColor.GOLD + "Mob information");
						Print.Output(sender, Settings.printxp("Blaze") + "  " + Settings.printxp("CaveSpider"));
						Print.Output(sender, Settings.printxp("Chicken") + "  " + Settings.printxp("Cow"));
						Print.Output(sender, Settings.printxp("Creeper") + "  " + Settings.printxp("Enderman"));
						Print.Output(sender, Settings.printxp("EnderDragon") + "  " + Settings.printxp("Ghast"));
						Print.Output(sender, Settings.printxp("Giant") + "  " + Settings.printxp("IronGolem"));
						Print.Output(sender, Settings.printxp("MagmaCube") + "  " + Settings.printxp("MushroomCow"));
						Print.Output(sender, Settings.printxp("Ocelot") + "  " + Settings.printxp("Pig"));
						Print.Output(sender, Settings.printxp("Sheep") + "  " + Settings.printxp("Silverfish"));
						Print.Output(sender, Settings.printxp("Skeleton") + "  " + Settings.printxp("Slime"));
						Print.Output(sender, Settings.printxp("Snowman") + "  " + Settings.printxp("Spider"));
						Print.Output(sender, Settings.printxp("Squid") + "  " + Settings.printxp("Villager"));
						Print.Output(sender, Settings.printxp("Wolf") + "  " + Settings.printxp("Zombie"));
						Print.Output(sender, Settings.printxp("ZombiePigman"));
						return true;
					}
					else
					{
						Print.Output(sender, "Mob experience drop is deactivated.");
						return true;
					}
				}
				if (args[1].equalsIgnoreCase("option"))
				{
					Print.Output(sender, ChatColor.GOLD + "Plugin options");
					Print.Output(sender, Settings.print("DropsOnlyFromPlayers"));
					Print.Output(sender, Settings.print("DebugFlag"));
					Print.Output(sender, Settings.print("DisableMobFlag"));
					Print.Output(sender, Settings.print("DisableBottleFlag"));
					Print.Output(sender, Settings.print("DisableOreFlag"));
					Print.Output(sender, Settings.print("ExperienceMultiplier"));
					return true;
				}
				if (args[1].equalsIgnoreCase("custom"))
				{
					if (Settings.getOption("DisableOreFlag") == false)
					{
						Print.Output(sender, ChatColor.GOLD + "Custom block information");
						for (int Block = 1; Block < 256; Block++)
						{
							String BlockName = "CustomOre"+String.valueOf(Block);
							if (Settings.containsOption(BlockName))
							{
								Print.Output(sender, Settings.printxp(BlockName));
							}
						}
						return true;
					}
					else
					{
						Print.Output(sender, "Ore experience drop is disabled.");
						return true;
					}
				}
			}
			Print.OutputERROR(sender, "Usage: /" + cmd.getName() + " info mob|ore|bottle|option|custom");
			return true;
		}

		if (args[0].equalsIgnoreCase("give"))
		{
			if (this.CheckPermissions(sender, "moreexp.adm.give"))
			{
				if (args.length == 3)
				{
					Player receiver = Bukkit.getServer().getPlayer(args[1]);
					int givenlvl = Integer.parseInt(args[2]);
					if (receiver == null)
					{
						Print.OutputERROR(sender, "Player " + args[1] + " seems to be offline.");
					}
					else
					{
						int currentlvl = receiver.getLevel();
						receiver.setLevel(currentlvl + givenlvl);
						if (sender instanceof Player)
						{
							Print.Output(sender, "You have gived " + args[2] + " XP levels to " + args[1] );
							Print.Output(receiver, "You have received " + args[2] + " XP levels  from " + sender.getName() );
							Print.OutputLog(sender.getName() + " give " + args[2] + " XP levels to " + args[1] );
						}
						else
						{
							Print.Output(receiver, "You have received " + args[2] + " XP levels  from the console." );
						}
					}
				}
				else
				{
					Print.OutputERROR(sender, "Usage : /" + cmd.getName() + " give player exp_lvl_value");
				}
			}
			else
			{
				Print.OutputERROR(sender, "Sorry, you don't have permission.");
			}
			return true;
		}
		
		if (args[0].equalsIgnoreCase("reset"))
		{
			// Usage of command : /moreexp reset player
			if (this.CheckPermissions(sender, "moreexp.adm.reset"))
			{
				if (args.length == 2)
				{
					Player receiver = Bukkit.getServer().getPlayer(args[1]);
					if (receiver == null)
					{
						Print.OutputERROR(sender, "Player " + args[1] + " seems to be offline.");
					}
					else
					{
						receiver.setExp(0);
						receiver.setLevel(0);
						if (sender instanceof Player)
						{
							sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp" + ChatColor.WHITE + "] You just have reset levels of " + args[1]);
							receiver.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp" + ChatColor.WHITE + "] " + sender.getName() + " has reset your level.");
							Print.OutputLog(sender.getName() + " reset levels of " + args[1] );
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
			else
			{
				Print.OutputERROR(sender, "Sorry, you don't have permission.");
			}
			return true;
		}
		
		if (args[0].equalsIgnoreCase("setcustom"))
		{
			if (this.CheckPermissions(sender, "moreexp.adm.set"))
			{
				if (args.length == 4)
				{
					String block = "CustomOre"+args[1];
					int XP = Integer.parseInt(args[2]);
					int XPvariable = Integer.parseInt(args[3]);
					Settings.setValue(block, XP, XPvariable);
					if (sender instanceof Player)
					{
						Print.Output(sender, "You have set block " + args[1] + " XP drop to " + args[2] + "-" + String.valueOf(Integer.parseInt(args[2])+Integer.parseInt(args[3]) ) );
					}
					Print.OutputLog(sender.getName() + " sets block " + args[1] + " XP drop to " + args[2] + "-" + String.valueOf(Integer.parseInt(args[2])+Integer.parseInt(args[3]) ) );
				}
				else
				{
					Print.OutputERROR(sender, "Usage : /" + cmd.getName() + " setcustom blockID XP XPvariable");
				}
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("delcustom"))
		{
			if (this.CheckPermissions(sender, "moreexp.adm.set"))
			{
				if (args.length == 2)
				{
					String block = "CustomOre"+args[1];
					Settings.delOption(block);
					Settings.delOption(block+"Rand");
					if (sender instanceof Player)
					{
						Print.Output(sender, "You have unset block " + args[1] );
					}
					Print.OutputLog(sender.getName() + " unsets block " + args[1] );
				}
				else
				{
					Print.OutputERROR(sender, "Usage : /" + cmd.getName() + " delcustom blockID");
				}
			}
			return true;
		}
		
		if (args[0].equalsIgnoreCase("set"))
		{
			// Usage of command : /moreexp set mob_value exp_value
			if (this.CheckPermissions(sender, "moreexp.adm.set"))
			{
				if (args.length == 3)
				{
					String option = args[1];
					boolean optionvalue = Boolean.parseBoolean(args[2]);
					if (Settings.containsOption(option))
					{
						Settings.setOption(option, optionvalue);
						if (sender instanceof Player)
						{
							Print.Output(sender, "You have set option " + args[1] + " to " + args[2] );
						}
						Print.OutputLog(sender.getName() + " sets option " + args[1] + " to " + args[2] );
					}
					else
					{
						Print.OutputERROR(sender, "Option " + option + " does not exist.");
					}
				}
				else if (args.length == 4)
				{
					String mob = args[1];
					int XP = Integer.parseInt(args[2]);
					int XPvariable = Integer.parseInt(args[3]);
					if (Settings.containsOption(mob))
					{
						Settings.setValue(mob, XP, XPvariable);
						if (sender instanceof Player)
						{
							Print.Output(sender, "You have set " + args[1] + " XP drop to " + args[2] + "-" + String.valueOf(Integer.parseInt(args[2])+Integer.parseInt(args[3]) ) );
						}
						Print.OutputLog(sender.getName() + " sets " + args[1] + " XP drop to " + args[2] + "-" + String.valueOf(Integer.parseInt(args[2])+Integer.parseInt(args[3]) ) );
					}
					else
					{
						Print.OutputERROR(sender, "Mob " + mob + " does not exist.");
					}
				}
				else
				{
					Print.OutputERROR(sender, "Usage : /" + cmd.getName() + " set option value");
					Print.OutputERROR(sender, "Usage : /" + cmd.getName() + " set mob XP XPvariable");
				}
			}
			else
			{
				Print.OutputERROR(sender, "Sorry, you don't have permission.");
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("xpgain"))
		{
			if (this.CheckPermissions(sender, "moreexp.adm.xpgain"))
			{
				if (args.length == 2)
				{
					int experiencemultiplier = Integer.parseInt(args[1]);
					if (experiencemultiplier < 1)
					{
						experiencemultiplier = 1;
					}
					Settings.setValue("ExperienceMultiplier", experiencemultiplier);
					Bukkit.getServer().broadcastMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp v" + Print.Version + ChatColor.WHITE + "] experience multiplier set to " + ChatColor.GOLD + experiencemultiplier);
				}
				else
				{
					Print.OutputERROR(sender, "Usage : /" + cmd.getName() + " xpgain value");
				}
			}
			else
			{
				Print.OutputERROR(sender, "Sorry, you don't have permission.");
			}
			return true;
		}
		
		MoreExp.OnUsage(sender, cmd);
		return true;
	}


}

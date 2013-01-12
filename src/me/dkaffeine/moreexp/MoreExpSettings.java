package me.dkaffeine.moreexp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MoreExpSettings
{

	public static final Logger log = Logger.getLogger("Minecraft");

	public static int BlazeExp;
	public static int BlazeExpRand;
	
	public static int BottleExp;
	public static int BottleExpRand;
	
	public static int CreeperExp;
	public static int CreeperExpRand;
	
	public static int EnderExp;
	public static int EnderExpRand;
	
	public static int ZombieExp;
	public static int ZombieExpRand;
	
	public static int SkeletonExp;
	public static int SkeletonExpRand;
	
	public static int SpiderExp;
	public static int SpiderExpRand;
	
	public static int PigExp;
	public static int PigExpRand;
	
	public static int SheepExp;
	public static int SheepExpRand;
	
	public static int CowExp;
	public static int CowExpRand;
	
	public static int ChickenExp;
	public static int ChickenExpRand;
	
	public static int WolfExp;
	public static int WolfExpRand;
	
	public static int CaveSpiderExp;
	public static int CaveSpiderExpRand;
	
	public static int GhastExp;
	public static int GhastExpRand;
	
	public static int PigZombieExp;
	public static int PigZombieExpRand;
	
	public static int EnderDragonExp;
	public static int EnderDragonExpRand;
	
	public static int GiantExp;
	public static int GiantExpRand;
	
	public static int IronGolemExp;
	public static int IronGolemExpRand;
	
	public static int MagmaCubeExp;
	public static int MagmaCubeExpRand;
	
	public static int MushroomCowExp;
	public static int MushroomCowExpRand;
	
	public static int OcelotExp;
	public static int OcelotExpRand;
	
	public static int SilverfishExp;
	public static int SilverfishExpRand;
	
	public static int SlimeExp;
	public static int SlimeExpRand;
	
	public static int SnowmanExp;
	public static int SnowmanExpRand;
	
	public static int SquidExp;
	public static int SquidExpRand;
	
	public static int VillagerExp;
	public static int VillagerExpRand;
	
	public static int WitchExp;
	public static int WitchExpRand;
	
	public static int WitherExp;
	public static int WitherExpRand;

	public static Random generator = new Random();
	
	public static int ExperienceMultiplier;
	public static boolean DropsOnlyFromPlayers;
	public static boolean DisableMobFlag;
	public static boolean DisableBottleFlag;
	
	public static boolean DebugFlag;
	
	public static Properties Datas;
	
	public static String propertiesFile = MoreExpMain.maindirectory + "config.xml";

	public static void listMobXP()
	{
		BlazeExp = getMobXP("Blaze",10);
		BlazeExpRand = getMobXP("BlazeRand",0);

		BottleExp = getMobXP("Bottle",3);
		BottleExpRand = getMobXP("BottleRand",3);
		
		CaveSpiderExp = getMobXP("CaveSpider", 5);
		CaveSpiderExpRand = getMobXP("CaveSpiderRand", 0);

		ChickenExp = getMobXP("Chicken", 1);
		ChickenExpRand = getMobXP("ChickenRand", 2);

		CowExp = getMobXP("Cow", 1);
		CowExpRand = getMobXP("CowRand", 2);

		CreeperExp = getMobXP("Creeper", 5);
		CreeperExpRand = getMobXP("CreeperRand", 0);

		EnderExp = getMobXP("Enderman", 5);
		EnderExpRand = getMobXP("EndermanRand", 0);
		
		EnderDragonExp = getMobXP("EnderDragon", 20000);
		EnderDragonExpRand = getMobXP("EnderDragonRand", 0);

		GhastExp = getMobXP("Ghast", 5);
		GhastExpRand = getMobXP("GhastRand", 0);
		
		GiantExp = getMobXP("Giant", 100);
		GiantExpRand = getMobXP("GiantRand", 0);
		
		IronGolemExp = getMobXP("IronGolem", 0);
		IronGolemExpRand = getMobXP("IronGolemRand", 0);
		
		MagmaCubeExp = getMobXP("MagmaCube", 2);
		MagmaCubeExpRand = getMobXP("MagmaCubeRand", 0);
		
		MushroomCowExp = getMobXP("MushroomCow", 1);
		MushroomCowExpRand = getMobXP("MushroomCowRand", 2);
		
		OcelotExp = getMobXP("Ocelot", 1);
		OcelotExpRand = getMobXP("OcelotRand", 2);
		
		PigExp = getMobXP("Pig", 1);
		PigExpRand = getMobXP("PigRand", 2);
		
		PigZombieExp = getMobXP("ZombiePigman", 5);
		PigZombieExpRand = getMobXP("ZombiePigmanRand", 0);
		
		SheepExp = getMobXP("Sheep", 1);
		SheepExpRand = getMobXP("SheepRand", 2);
		
		SilverfishExp = getMobXP("Silverfish", 5);
		SilverfishExpRand = getMobXP("SilverfishRand", 0);
		
		SkeletonExp = getMobXP("Skeleton", 5);
		SkeletonExpRand = getMobXP("SkeletonRand", 0);
		
		SlimeExp = getMobXP("Slime", 2);
		SlimeExpRand = getMobXP("SlimeRand", 0);
		
		SnowmanExp = getMobXP("Snowman", 0);
		SnowmanExpRand = getMobXP("SnowmanRand", 0);
		
		SpiderExp = getMobXP("Spider", 5);
		SpiderExpRand = getMobXP("SpiderRand", 0);
		
		SquidExp = getMobXP("Squid", 1);
		SquidExpRand = getMobXP("SquidRand", 2);
		
		VillagerExp = getMobXP("Villager", 0);
		VillagerExpRand = getMobXP("VillagerRand", 0);
		
		WitchExp = getMobXP("Witch", 15);
		WitchExpRand = getMobXP("WitchRand", 0);
		
		WitherExp = getMobXP("Wither", 0);
		WitherExpRand = getMobXP("WitherRand", 0);
		
		WolfExp = getMobXP("Wolf", 1);
		WolfExpRand = getMobXP("WolfRand", 2);
		
		ZombieExp = getMobXP("Zombie", 5);
		ZombieExpRand = getMobXP("ZombieRand", 0);
		
		DropsOnlyFromPlayers = getOption("DropsOnlyFromPlayers", true);
		DebugFlag = getOption("DebugFlag", false);
		DisableMobFlag = getOption("DisableMobFlag", false);
		DisableBottleFlag = getOption("DisableBottleFlag", false);
		
		
		ExperienceMultiplier = getOption("ExperienceMultiplier", 1);
	}
	
	public static boolean containsOption(String key)
	{
		return Datas.containsKey(key);
	}
	
	public static void setMobXP(String key, int value)
	{
		Datas.put(key, String.valueOf(value));
		listMobXP();
	}

	public static void setMobXPVariable(String key, int valuebase, int valuevariable)
	{
		Datas.put(key, String.valueOf(valuebase));
		Datas.put(key.concat("Rand"),  String.valueOf(valuevariable));
		listMobXP();
	}
	
	public static String getValue(String key)
	{
		if (Datas.containsKey(key))
		{
			return Datas.getProperty(key, "");
		}
		return "";
	}
	
	public static int getMobXP(String key, int defaultvalue)
	{
		if (Datas.containsKey(key))
		{
			return Integer.parseInt(Datas.getProperty(key, String.valueOf(defaultvalue)));
		}
		Datas.put(key, String.valueOf(defaultvalue));
		return defaultvalue;
	}

	public static void setOption(String key, boolean value)
	{
		if (Datas.containsKey(key))
		{
			Datas.remove(key);
		}
		Datas.put(key, String.valueOf(value));
		listMobXP();
	}

	public static void setOption(String key, int value)
	{
		Datas.put(key, String.valueOf(value));
		listMobXP();
	}
	
	public static boolean getOption(String key, boolean defaultvalue)
	{
		if (Datas.containsKey(key))
		{
			return Boolean.parseBoolean(Datas.getProperty(key, String.valueOf(defaultvalue)));
		}
		Datas.put(key, String.valueOf(defaultvalue));
		return defaultvalue;
	}

	public static int getOption(String key, int defaultvalue)
	{
		if (Datas.containsKey(key))
		{
			return Integer.parseInt(Datas.getProperty(key, String.valueOf(defaultvalue)));
		}
		Datas.put(key, String.valueOf(defaultvalue));
		return defaultvalue;
	}
	
	public static void load()
	{
		Datas = new Properties();
		File FileToOpen = new File(propertiesFile);
		if(!FileToOpen.exists())
			try {
				FileToOpen.createNewFile();
				listMobXP();
				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
		{
			try
			{
				Datas.loadFromXML(new FileInputStream(propertiesFile));
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		listMobXP();
	}

	public static void save()
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(propertiesFile));
			ArrayList<String> list = new ArrayList<String>();
			for (Object o : Datas.keySet()) {
				list.add((String)o);
			}
			Collections.sort(list);
			String g = Character.toString('"');
            out.write("<?xml version=" + g + "1.0" + g + " encoding=" + g + "UTF-8" + g + "?>\n");
            out.write("<!DOCTYPE properties SYSTEM " + g + "http://java.sun.com/dtd/properties.dtd" + g + ">\n");
            out.write("<properties>\n");
            out.write("<comment>Modify lines below to modify experience value of each mob or option</comment>\n");
            for(String s : list){
                out.write("<entry key=" + g + s + g + ">" + Datas.getProperty(s) + "</entry>\n");
            }
            out.write("</properties>\n");
            out.flush();
            out.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			log.log(Level.SEVERE, "[MoreExp]: Unable to save "+propertiesFile, ex);
		}
	}
	
	public static int ComputeXP(int base, int variable)
	{
		int variablexp = 0;
		if (variable > 0)
		{
			variablexp = generator.nextInt(variable+1);
		}
		if (MoreExpSettings.DebugFlag == true)
		{
			OutputLog("Experience gained : " + String.valueOf(base) + " + " + String.valueOf(variablexp) + "(0 - " + String.valueOf(variable) + ")");
		}
		int ReturnXP = base + variablexp;
		ReturnXP = ReturnXP * ExperienceMultiplier;
		return ReturnXP;
	}
	
	public static void Output(CommandSender sender, String message)
	{
		sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp" + ChatColor.WHITE + "] " + message);
	}

	public static void OutputERROR(CommandSender sender, String message)
	{
		sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "MoreExp" + ChatColor.WHITE + "] " + ChatColor.RED + message);
	}

	public static void OutputLog(String message)
	{
		log.log(Level.INFO,"[MoreExp] " + message);
	}

	public static void OutputLogERROR(String message)
	{
		log.log(Level.SEVERE,"[MoreExp] " + message);
	}
}

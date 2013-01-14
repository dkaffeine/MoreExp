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

import org.bukkit.ChatColor;

import me.dkaffeine.moreexp.io.Print;

public class Settings {
	public static Properties Datas;
	public static String propertiesFile = MoreExp.maindirectory + "config.xml";
	public static Random generator = new Random();
	
	public static void initValue(String key, String value)
	{
		if (Datas.containsKey(key))
		{
			return;
		}
		Datas.put(key, value);
	}
	
	public static void init() {
		// Bottle o'enchant initialization
		initValue("Bottle","3");
		initValue("BottleRand","8");
		// Mobs initialization
		initValue("Blaze","10");
		initValue("BlazeRand","0");
		initValue("CaveSpider","5");
		initValue("CaveSpiderRand","0");
		initValue("Chicken","1");
		initValue("ChickenRand","2");
		initValue("Cow","1");
		initValue("CowRand","2");
		initValue("Creeper","5");
		initValue("CreeperRand","0");
		initValue("Enderman","5");
		initValue("EndermanRand","0");
		initValue("EnderDragon","20000");
		initValue("EnderDragonRand","0");
		initValue("Ghast","5");
		initValue("GhastRand","0");
		initValue("Giant","100");
		initValue("GiantRand","0");
		initValue("IronGolem","0");
		initValue("IronGolemRand","0");
		initValue("MagmaCube","2");
		initValue("MagmaCubeRand","0");
		initValue("MushroomCow","1");
		initValue("MushroomCowRand","2");
		initValue("Ocelot","1");
		initValue("OcelotRand","2");
		initValue("Pig","1");
		initValue("PigRand","2");
		initValue("Sheep","1");
		initValue("SheepRand","2");
		initValue("Silverfish","5");
		initValue("SilverfishRand","0");
		initValue("Skeleton","5");
		initValue("SkeletonRand","0");
		initValue("Slime","2");
		initValue("SlimeRand","0");
		initValue("Snowman","0");
		initValue("SnowmanRand","0");
		initValue("Spider","5");
		initValue("SpiderRand","0");
		initValue("Squid","1");
		initValue("SquidRand","2");
		initValue("Villager","0");
		initValue("VillagerRand","0");
		initValue("Witch","5");
		initValue("WitchRand","0");
		initValue("Wither","50");
		initValue("WitherRand","0");
		initValue("Wolf","1");
		initValue("WolfRand","2");
		initValue("Zombie","5");
		initValue("ZombieRand","0");
		initValue("ZombiePigman","5");
		initValue("ZombiePigmanRand","0");

		// Ore initialization
		initValue("CoalOre","0");
		initValue("CoalOreRand","2");
		initValue("DiamondOre","0");
		initValue("DiamondOreRand","2");
		initValue("EmeraldOre","0");
		initValue("EmeraldOreRand","2");
		initValue("GoldOre","0");
		initValue("GoldOreRand","0");
		initValue("Glowstone","0");
		initValue("GlowstoneRand","0");
		initValue("IronOre","0");
		initValue("IronOreRand","0");
		initValue("LapisOre","0");
		initValue("LapisOreRand","2");
		initValue("RedstoneOre","0");
		initValue("RedstoneOreRand","2");
		
		// PVP bonus initialization
		initValue("Player","0");
		initValue("PlayerRand","0");
		
		// Various options
		initValue("DropsOnlyFromPlayers","true");
		initValue("DebugFlag","false");
		initValue("DisableMobFlag","false");
		initValue("DisableBottleFlag","false");
		initValue("DisableOreFlag","false");
		initValue("ExperienceMultiplier","1");
		
	}
	
	public static void reinit()
	{
		Datas.clear();
		init();
	}
	
	public static void load()
	{
		Datas = new Properties();
		File FileToOpen = new File(propertiesFile);
		if(!FileToOpen.exists())
			try {
				Print.OutputLog("File "+propertiesFile+ " does not exist, creating it.");
				FileToOpen.createNewFile();
				init();
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
        Print.OutputLog("Datas loaded from file "+propertiesFile);
		init();
	}

	public static void save()
	{
		save(propertiesFile);
	}
	
	public static void save(String outputFile)
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
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
            Print.OutputLog("Datas saved on file "+outputFile);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			Print.OutputLogERROR("Unable to save on file "+outputFile);
		}
	}
	
	public static boolean getOption(String key)
	{
		if (!Datas.containsKey(key))
		{
			Print.OutputLogERROR("Option " + key + " is not defined.");
			return false;
		}
		return Boolean.parseBoolean(Datas.getProperty(key));
	}

	public static int getValue(String key)
	{
		if (!Datas.containsKey(key))
		{
			Print.OutputLogERROR("Option " + key + " is not defined.");
			return 0;
		}
		return Integer.parseInt(Datas.getProperty(key));
	}

	public static int getXP(String base)
	{
		int basexp = getValue(base);
		int variablexp = getValue(base.concat("Rand"));
		int returnxp = basexp;
		if (variablexp > 0)
		{
			returnxp = returnxp + generator.nextInt(variablexp+1);
		}
		Print.DebugLog("Experience gained : " + String.valueOf(returnxp) + " (" + String.valueOf(basexp) + "-" + String.valueOf(basexp + variablexp) + ")");
		if (getValue("ExperienceMultiplier") > 0)
		{
			returnxp = returnxp * getValue("ExperienceMultiplier");			
		}
		
		return returnxp;
	}
	
	public static String printxp(String base)
	{
		int basexp = getValue(base);
		int variablexp = getValue(base.concat("Rand"));
		String returnstring = ChatColor.AQUA + base + ChatColor.WHITE + ": " + String.valueOf(basexp) + "-" + String.valueOf(basexp + variablexp);
		return returnstring;
	}
	
	public static String print(String option)
	{
		if (containsOption(option) == false)
			return "";
		return ChatColor.AQUA + option + ChatColor.WHITE + ": " + Datas.getProperty(option);
	}
	
	public static void delOption(String key)
	{
		Datas.remove(key);
	}
	
	public static void setOption(String key, boolean value)
	{
		Datas.put(key, String.valueOf(value));
	}

	public static void setOption(String key, int value)
	{
		Datas.put(key, String.valueOf(value));
	}

	public static void setOption(String key, String value)
	{
		Datas.put(key, value);
	}

	public static void setValue(String key, int value)
	{
		Datas.put(key, String.valueOf(value));
	}

	public static void setValue(String key, int value, int valuerandom)
	{
		Datas.put(key, String.valueOf(value));
		Datas.put(key.concat("Rand"), String.valueOf(valuerandom));
	}
	
	public static boolean containsOption(String key)
	{
		return Datas.containsKey(key);
	}
}

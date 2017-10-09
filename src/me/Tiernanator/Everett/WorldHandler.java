package me.Tiernanator.Everett;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

public class WorldHandler {
	
	private static EverettMain plugin;
	public static void setPlugin(EverettMain main) {
		plugin = main;
	}
	
	public static void addWorldNameToConfig(String worldName) {
		
		List<String> customWorldNames = plugin.getConfig()
				.getStringList("CustomWorlds");
		if (customWorldNames != null) {
			
			if(!customWorldNames.contains(worldName)) {
				customWorldNames.add(worldName);
			}
			
		}
		plugin.getConfig().set("CustomWorlds", customWorldNames);
		
	}
	
	public static void loadAllWorldsFromConfig() {
		
		List<String> customWorldNames = plugin.getConfig()
				.getStringList("CustomWorlds");
		if (customWorldNames != null) {
			for (String worldName : customWorldNames) {
				try {
				WorldCreator worldCreator = new WorldCreator(worldName);
				Bukkit.createWorld(worldCreator);
				} catch(Exception e) {
					plugin.getLogger().log(Level.WARNING, "Could not load world: " + worldName);
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	

}

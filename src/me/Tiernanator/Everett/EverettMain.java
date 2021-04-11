package me.Tiernanator.Everett;

import org.bukkit.plugin.java.JavaPlugin;

import me.Tiernanator.Everett.Commands.CreateWorld;
import me.Tiernanator.Everett.Commands.TeleportWorld;

public class EverettMain extends JavaPlugin {

	@Override
	public void onEnable() {
		
		setPlugins();
		registerCommands();
		registerEvents();
		
		WorldHandler.loadAllWorldsFromConfig();
		
	}

	@Override
	public void onDisable() {

	}

	private void registerCommands() {
		getCommand("teleport").setExecutor(new TeleportWorld(this));
		getCommand("createworld").setExecutor(new CreateWorld());
	}

	private void registerEvents() {
//		PluginManager pm = getServer().getPluginManager();
		
//		pm.registerEvents(new OnSignInteract(this), this);
//		pm.registerEvents(new OnSeatLeave(this), this);
	}
	
	private void setPlugins() {
		WorldHandler.setPlugin(this);
	}
	
}

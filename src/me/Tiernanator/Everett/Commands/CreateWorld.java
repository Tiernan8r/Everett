package me.Tiernanator.Everett.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Tiernanator.Everett.WorldHandler;
import me.Tiernanator.Utilities.Colours.Colour;

public class CreateWorld implements CommandExecutor {

	private ChatColor warning = Colour.WARNING.getColour();
	private ChatColor good = Colour.GOOD.getColour();
	private ChatColor highlight = Colour.HIGHLIGHT.getColour();
	
	public CreateWorld() {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if(args.length < 2) {
			
			sender.sendMessage(warning + "You must provide a " + highlight + "seed" + warning + " and a " + highlight + "name" + warning + " for the world.");
			return false;
			
		}
		
		String seedString = args[0];
		long seed = seedString.hashCode();
		
		String worldName = "";
		for(int i = 1; i < args.length; i++) {
			String namePart = args[i];
			worldName += namePart;
			
			if(i < args.length - 1) {
				worldName += "_";
			}
			
		}
		
		if(Bukkit.getServer().getWorld(worldName) != null) {
			sender.sendMessage(warning + "A world called " + highlight + worldName + warning + " already exists!");
			return false;
		}
		
		WorldCreator worldCreator = new WorldCreator(worldName);
		worldCreator.seed(seed);
		worldCreator.createWorld();
		WorldHandler.addWorldNameToConfig(worldName);
		
		sender.sendMessage(good + "The world " + highlight + worldName + good + " has been created.");
		
		return true;
		
	}
}

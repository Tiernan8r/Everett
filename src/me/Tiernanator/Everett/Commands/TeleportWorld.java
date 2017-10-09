package me.Tiernanator.Everett.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tiernanator.Everett.EverettMain;
import me.Tiernanator.Utilities.Colours.Colour;
import me.Tiernanator.Utilities.Locations.RelativeLocation;

public class TeleportWorld implements CommandExecutor {

	private static EverettMain plugin;

	private ChatColor warning = Colour.WARNING.getColour();
	private ChatColor good = Colour.GOOD.getColour();
	private ChatColor informative = Colour.INFORMATIVE.getColour();
	
	public TeleportWorld(EverettMain main) {
		plugin = main;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (args.length < 4) {
				player.sendMessage(warning + 
						"You must provide a world name, and the x, y, z coordinates to move to in the world.");
				return false;
			}
			String worldName = args[0];
			Location relativeLocation = RelativeLocation.getRelativeLocationsFromString(player, args[1], args[2], args[3]);
			if(relativeLocation == null) {
				player.sendMessage(warning + "Those are not valid coordinates");
				return false;
			}
			
			org.bukkit.World world = plugin.getServer().getWorld(worldName);
			if(world == null) {
				player.sendMessage(worldName + warning + " is not a real world.");
				return false;
			}
			
			relativeLocation.setWorld(world);
			player.teleport(relativeLocation);

			String x = Double.toString(relativeLocation.getX());
			String y = Double.toString(relativeLocation.getY());
			String z = Double.toString(relativeLocation.getZ());
			worldName = relativeLocation.getWorld().getName();
			
			player.sendMessage(good + "Teleported to " + informative + "(" + worldName + ")" + " " + x + good + ", " + informative + y + good + ", " + informative + z + good + ".");
			
		}
		return false;
	}
}

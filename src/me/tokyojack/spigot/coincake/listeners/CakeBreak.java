package me.tokyojack.spigot.coincake.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CakeBreak implements Listener {

	@EventHandler
	public void onCakeBreak(BlockBreakEvent event) {
		
		// If the cake doesn't have the metatag "gamblecake". (Gains it when placed)
		if (!event.getBlock().hasMetadata("gamblecake"))
			return;

		event.getPlayer().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "&c You cannot break &ecoin cakes"));
		event.setCancelled(true);
	}

}

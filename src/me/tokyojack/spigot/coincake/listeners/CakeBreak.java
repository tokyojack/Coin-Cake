package me.tokyojack.spigot.coincake.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CakeBreak implements Listener {

	@EventHandler
	public void onCakeBreak(BlockBreakEvent event) {
		if (!event.getBlock().hasMetadata("gamblecake"))
			return;

		event.getPlayer().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "&4&l[&c&l>>&4&l]&f You cannot break &ecoin cakes"));
		event.setCancelled(true);
	}

}

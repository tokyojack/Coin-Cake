package me.tokyojack.spigot.coincake.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import me.tokyojack.spigot.coincake.Core;

public class CakePlace implements Listener {

	@EventHandler
	public void cakeeaterion(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		
		if (item == null)
			return;

		if (!item.hasItemMeta())
			return;

		if (!item.getItemMeta().hasDisplayName())
			return;

		if (!item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&6&lCoin Cake")))
			return;

		int amount = Integer.parseInt((ChatColor.stripColor(item.getItemMeta().getDisplayName()).split(" ")[0])
				.replace("$", "").replace(",", "")) * 2;

		event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&2&l[&a&l>>&2&l]&f Now, eat your whole coin cake by right clicking it!"));

		event.getBlock().setMetadata("gamblecake", new FixedMetadataValue(Core.getPlugin(), amount));
	}

}

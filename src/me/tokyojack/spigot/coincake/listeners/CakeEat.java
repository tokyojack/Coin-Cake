package me.tokyojack.spigot.coincake.listeners;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Cake;

import me.tokyojack.spigot.coincake.Core;
import me.tokyojack.spigot.coincake.utils.ParticleEffect;
import net.milkbowl.vault.economy.EconomyResponse;

public class CakeEat implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Block clickedBlock = event.getClickedBlock();
		
		// Checks the block is null
		if (clickedBlock == null)
			return;

		// Checks the clicked block type is AIR
		if (clickedBlock.getType() == Material.AIR)
			return;

		// Checks if the clicked block is a CAKE_BLOCK
		if (clickedBlock.getType() != Material.CAKE_BLOCK)
			return;

		// Checks if the action is right clicking
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		Cake cake = (Cake) event.getClickedBlock().getState().getData();
		event.setCancelled(true);
		
		int amount = event.getClickedBlock().getMetadata("gamblecake").get(0).asInt();

		if (RandomNumber(100) <= 7) {
			EconomyResponse transactionResponse = Core.getPlugin().getEconomy().depositPlayer(event.getPlayer().getName(), amount);
			if (!transactionResponse.transactionSuccess())
				return;

			event.getClickedBlock().setType(Material.AIR);
			event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.EXPLODE, 2, 1);
			
			event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&fYou've found a &e&l$" + amount + "&e coin!"));
					
			event.getClickedBlock().removeMetadata("gamblecake", Core.getPlugin());
			return;
		}

		int total = cake.getSlicesRemaining() - 1;

		if (total >= 1)
			// Divides the "amount" by 2 as "amount" is the reward (as the reward is 2x the bought price)
			event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&fYou've eaten a slice of a &e$" + amount / 2 + " coin cake"));
					
		if (total < 0) {
			event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&fYou've eaten all the slices in the &e$" + amount / 2 + " &Fcoin cake!"));
			event.getClickedBlock().setType(Material.AIR);
			event.getClickedBlock().removeMetadata("gamblecake", Core.getPlugin());
			return;
		}
		
		cake.setSlicesRemaining(total);
		event.getClickedBlock().setData(cake.getData());
	}

	private int RandomNumber(int range) {
		return new Random().nextInt(range) + 1;
	}

}

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
	@EventHandler
	public void onEat(PlayerInteractEvent event) {
		if (event.isCancelled())
			return;

		if (event.getClickedBlock() == null)
			return;

		if (event.getClickedBlock().getType() == Material.AIR)
			return;

		if (event.getClickedBlock().getType() != Material.CAKE_BLOCK)
			return;

		if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		Cake cake = (Cake) event.getClickedBlock().getState().getData();
		event.setCancelled(true);
		
		int amount = event.getClickedBlock().getMetadata("gamblecake").get(0).asInt();

		if (RandomNumber(100) <= 7) {
			EconomyResponse r = Core.getPlugin().getEconomy().depositPlayer(event.getPlayer().getName(), amount);
			if (!r.transactionSuccess())
				return;

			event.getClickedBlock().setType(Material.AIR);
			event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.EXPLODE, 2, 1);
			ParticleEffect.FLAME.display(RandomNumber(1), RandomNumber(1), RandomNumber(1), 1, 50,
					event.getClickedBlock().getLocation(), 50);
			ParticleEffect.EXPLOSION_HUGE.display(0, 0, 0, 0, 1, event.getClickedBlock().getLocation(), 50);
			event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&2&l[&a&l>>&2&l]&f You've found a &e&l$" + amount + "&e coin!"));
			event.getClickedBlock().removeMetadata("gamblecake", Core.getPlugin());
			return;
		}

		int total = cake.getSlicesRemaining() - 1;

		if (total >= 1)
			event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&2&l[&a&l>>&2&l]&f You've eaten a slice of a &e$" + amount / 2 + " coin cake"));

		if (total < 0) {
			event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&4&l[&c&l>>&4&l]&f You've eaten all the slices in the &e$" + amount / 2 + " &Fcoin cake!"));
			event.getClickedBlock().setType(Material.AIR);
			event.getClickedBlock().removeMetadata("gamblecake", Core.getPlugin());
			return;
		}
		
		cake.setSlicesRemaining(total);
		event.getClickedBlock().setData(cake.getData());
	}

	private int RandomNumber(int s) {
		Random random = new Random();
		int randomnumber = random.nextInt(s) + 1;
		int intran = randomnumber;
		return intran;

	}

}

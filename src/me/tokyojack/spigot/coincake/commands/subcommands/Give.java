package me.tokyojack.spigot.coincake.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tokyojack.spigot.coincake.utils.ItemBuilder;
import me.tokyojack.spigot.coincake.utils.subKommand.SubKommand;

public class Give extends SubKommand {
	public Give() {
		super("Give cakes");
	}

	// TODO check if player's inventory is full

	@Override
	public boolean execute(CommandSender commandSender, String[] args) {
		if(args.length <= 0){
			commandSender.sendMessage("/coincake give <amount> <player>");
			return false;
		}
		
		int amount = Integer.parseInt(args[0]);

		if (args.length <= 1) {
			if (!(commandSender instanceof Player)) {
				commandSender.sendMessage("/coincake give <amount> <player>");
				return false;
			}

			((Player) commandSender).getInventory()
					.addItem(new ItemBuilder(Material.CAKE)
							.setName(ChatColor.translateAlternateColorCodes('&', "&e$&l" + amount + " &6&lCoin Cake"))
							.toItemStack());
		} else {
			Player player = Bukkit.getPlayer(args[1]);

			if (player == null) {
				commandSender.sendMessage("Unknown player");
				return false;
			}

			player.getInventory()
					.addItem(new ItemBuilder(Material.CAKE)
							.setName(ChatColor.translateAlternateColorCodes('&', "&e$&l" + amount + " &6&lCoin Cake"))
							.toItemStack());
		}

		return true;
	}
}

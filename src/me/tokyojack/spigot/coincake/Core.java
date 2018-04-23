package me.tokyojack.spigot.coincake;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.tokyojack.spigot.coincake.commands.Coincake;
import me.tokyojack.spigot.coincake.commands.subcommands.Give;
import me.tokyojack.spigot.coincake.listeners.CakeBreak;
import me.tokyojack.spigot.coincake.listeners.CakeEat;
import me.tokyojack.spigot.coincake.listeners.CakePlace;
import me.tokyojack.spigot.coincake.utils.Gependecy;
import me.tokyojack.spigot.coincake.utils.subKommand.SubKommandManager;
import net.milkbowl.vault.economy.Economy;

public class Core extends JavaPlugin {

	private Economy economy;

	public Economy getEconomy() {
		return this.economy;
	}
	
	private static Core plugin;

	public static Core getPlugin() {
		return plugin;
	}
	
	public void onEnable() {
		plugin = this;
		
		new SubKommandManager(new Coincake(), true).addSubCommand(new Give()).build();

		PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new CakeBreak(), this);
		pluginManager.registerEvents(new CakeEat(), this);
		pluginManager.registerEvents(new CakePlace(), this);

		new Gependecy("Vault", true, this) {

			@Override
			public void ifFound() {
				setupEconomy();
			}

			@Override
			public void ifNotFound() {
				// Nothing happens.
			}
		}.check();
	}

	// From Vault's WIKI if I'm correct
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
			economy = economyProvider.getProvider();
		
		return (economy != null);
	}
	
}
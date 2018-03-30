package me.tokyojack.spigot.coincake.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;

import me.tokyojack.spigot.coincake.utils.kommand.Kommand;

public class Coincake extends Kommand {

	public Coincake() {
		super("Main coincake command", Arrays.asList("cc", "cakecoin"));
	}

	@Override
	public boolean execute(CommandSender commandSender, String label, String[] args) {
		return true;
	}

}
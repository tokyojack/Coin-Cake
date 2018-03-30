package me.tokyojack.spigot.coincake.utils.subKommand;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import me.tokyojack.spigot.coincake.utils.kommand.Kommand;

/**
* Kommand - https://github.com/tokyojack/Kommand
*
* @author  Tokyojack
* Discord: tokyojack#7353
* McM:     tokyojack
*/

public class SubKommandManager extends BukkitCommand {

	private Kommand command;
	private boolean doesProvideHelpMessage;

	private List<SubKommand> subCommands = new ArrayList<SubKommand>();

	/**
	 * The first command that the args is register on
	 *
	 * @param Kommand
	 *            Class that extends Kommand
	 */
	public SubKommandManager(Kommand command) {
		super(command.getName() == null ? command.getClass().getSimpleName().toLowerCase() : command.getName(),
				command.getDescription(), null, command.getAliases());
		this.setUsage("/" + this.getName());

		this.command = command;
		this.doesProvideHelpMessage = false;

	}

	/**
	 * The first command that the args is register on
	 *
	 * @param Kommand
	 *            Class that extends Kommand
	 * @param Boolean
	 *            When doing the command is run by its self, does it paste a
	 *            generated sub-command menu
	 */
	public SubKommandManager(Kommand command, boolean doesProvideHelpMessage) {
		this(command);

		this.doesProvideHelpMessage = doesProvideHelpMessage;
	}

	/**
	 * Adds a SubKommand to the manager
	 *
	 * @param SubKommand
	 *            Class that extends SubKommand
	 */
	public SubKommandManager addSubCommand(SubKommand subCommand) {
		String currentName = subCommand.getName();
		String subCommadName = currentName == null ? subCommand.getClass().getSimpleName().toLowerCase() : currentName;

		subCommand.setName(subCommadName);

		this.subCommands.add(subCommand);
		return this;
	}

	/**
	 * Registers the main kommand. MUST BE DONE AFTER ALL THE SUBKOMMADNS BEEN
	 * ADDED (.addCommand)
	 */
	public void build() {
		try {
			Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

			bukkitCommandMap.setAccessible(true);
			CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

			commandMap.register(this.getName(), this);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!(this.getAliases().contains(label.toLowerCase()) || this.getName().equalsIgnoreCase(label)))
			return false;

		if(!sender.hasPermission("command."+this.getName()))
			return false;
		
		if (args.length <= 0) {
			if (this.doesProvideHelpMessage) {
				sender.sendMessage(ChatColor.GREEN + "=====" + StringUtils.capitalise(this.getName()) + "=====");
				this.subCommands.forEach(subCommand -> sender.sendMessage(ChatColor.YELLOW + "/"
						+ subCommand.getClass().getSimpleName().toLowerCase() + " | " + subCommand.getDescription()));
			} else
				this.command.execute(sender, label, args);

			return true;
		}

		String subCommand = args[0];

		SubKommand subCommandItem = this.subCommands.stream()
				.filter(subCommandEntry -> subCommandEntry.getName().equalsIgnoreCase(subCommand)
						|| subCommandEntry.getAlias().contains(subCommand.toLowerCase()))
				.findFirst().orElse(null);

		if (subCommandItem == null) {
			sender.sendMessage(ChatColor.RED + "Unknown sub command '" + subCommand + "'");
			return false;
		}

		if (subCommandItem.getCommandSenderType() != CommandSenderType.ANY) {
			if (subCommandItem.getCommandSenderType() == CommandSenderType.PLAYER) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "Only players can do '/" + this.getName() + "'");
					return false;
				}
			}

			if (subCommandItem.getCommandSenderType() == CommandSenderType.CONSOLE) {
				if (sender instanceof Player) {
					sender.sendMessage(ChatColor.RED + "Only the console can do '/" + this.getName() + "'");
					return false;
				}
			}
		}

		String[] removeFirstArg = new ArrayList<String>(Arrays.asList(args)).stream()
				.filter(item -> !item.equalsIgnoreCase(subCommand)).toArray((String[]::new));

		subCommandItem.execute(sender, removeFirstArg);
		return true;
	}

}
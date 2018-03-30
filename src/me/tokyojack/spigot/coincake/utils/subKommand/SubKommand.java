package me.tokyojack.spigot.coincake.utils.subKommand;

/**
* Kommand - https://github.com/tokyojack/Kommand
*
* @author  Tokyojack
* Discord: tokyojack#7353
* McM:     tokyojack
*/

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

public abstract class SubKommand {

	private final static String DEFAULT_DESCRIPTION = "No description set";
	private final static CommandSenderType DEFAULT_COMMAND_SENDER = CommandSenderType.ANY;

	private String name;
	private String decription;
	private CommandSenderType commandSenderType;
	private List<String> alias;

	/**
	 * Creates a sub command with the class name for the command name,
	 * description, command sender, and aliases
	 *
	 * @param String
	 *            Sub command description
	 * @param CommandSenderType
	 *            Type of command sender who can do the sub command
	 * @param CommandSenderType
	 *            Type of command sender who can do the sub command
	 */
	public SubKommand(String description, CommandSenderType commandSenderType, List<String> alias) {
		this.name = null;
		this.decription = description;
		this.commandSenderType = commandSenderType;
		this.alias = alias;
	}

	/**
	 * Creates a sub command with the command name, description, command sender,
	 * and aliases
	 *
	 * @param String
	 *            Sub command name
	 * @param String
	 *            Sub command description
	 * @param CommandSenderType
	 *            Type of command sender who can do the sub command
	 * @param CommandSenderType
	 *            Type of command sender who can do the sub command
	 */
	public SubKommand(String name, String description, CommandSenderType commandSenderType, List<String> alias) {
		this.name = name;
		this.decription = description;
		this.commandSenderType = commandSenderType;
		this.alias = alias;
	}

	/**
	 * Creates a sub command with the class name for the command name, default
	 * description, ANY command sender, and no aliases
	 */
	public SubKommand() {
		this(DEFAULT_DESCRIPTION, DEFAULT_COMMAND_SENDER, new ArrayList<String>());
	}

	/**
	 * Creates a sub command with the class name for the command name,
	 * description, ANY command sender, and no aliases
	 *
	 * @param String
	 *            Sub command description
	 */
	public SubKommand(String description) {
		this(description, DEFAULT_COMMAND_SENDER, new ArrayList<String>());
	}

	/**
	 * Creates a sub command with the class name for the command name,
	 * description, ANY command sender, and no aliases
	 *
	 * @param CommandSenderType
	 *            Type of command sender who can do the sub command
	 */
	public SubKommand(CommandSenderType commandSenderType) {
		this(DEFAULT_DESCRIPTION, commandSenderType, new ArrayList<String>());
	}

	/**
	 * Creates a sub command with the class name for the command name,
	 * description, command sender, and no aliases
	 *
	 * @param String
	 *            Sub command description
	 * @param CommandSenderType
	 *            Type of command sender who can do the sub command
	 */
	public SubKommand(String description, CommandSenderType commandSenderType) {
		this(description, commandSenderType, new ArrayList<String>());
	}

	/**
	 * Creates a sub command with the class name for the command name, default
	 * description, ANY command sender, and aliases
	 *
	 * @param List<String>
	 *            Sub command aliases
	 */
	public SubKommand(List<String> alias) {
		this(DEFAULT_DESCRIPTION, DEFAULT_COMMAND_SENDER, alias);
	}

	/**
	 * Creates a sub command with the class name for the command name,
	 * description, ANY command sender, and aliases
	 *
	 * @param String
	 *            Sub command description
	 * @param List<String>
	 *            Sub command aliases
	 */
	public SubKommand(String description, List<String> alias) {
		this(description, DEFAULT_COMMAND_SENDER, alias);
	}

	/**
	 * Creates a sub command with the class name for the command name, default
	 * description, command sender, and aliases
	 *
	 * @param CommandSenderType
	 *            Type of command sender who can do the sub command
	 * @param List<String>
	 *            Sub command aliases
	 */
	public SubKommand(CommandSenderType commandSenderType, List<String> alias) {
		this(DEFAULT_DESCRIPTION, commandSenderType, alias);
	}

	/**
	 * Creates a sub command with the command name, description, ANY command
	 * sender, and no aliases
	 *
	 * @param String
	 *            Sub command name
	 * @param String
	 *            Sub command description
	 */
	public SubKommand(String name, String description) {
		this(name, description, DEFAULT_COMMAND_SENDER, new ArrayList<String>());
	}

	/**
	 * Creates a sub command with the command name, description, command sender,
	 * and no aliases
	 *
	 * @param String
	 *            Sub command name
	 * @param String
	 *            Sub command description
	 * @param CommandSenderType
	 *            Type of command sender who can do the sub command
	 */
	public SubKommand(String name, String description, CommandSenderType commandSenderType) {
		this(name, description, commandSenderType, new ArrayList<String>());
	}

	public abstract boolean execute(CommandSender commandSender, String[] args);

	/**
	 * Sets the sub command name
	 * 
	 * @param String
	 *            The new name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the sub command description
	 * 
	 * @param String
	 *            The new name.
	 */
	public void setDescription(String description) {
		this.decription = description;
	}

	/**
	 * Sets the sub command CommandSenderType
	 * 
	 * @param String
	 *            The new CommandSenderType
	 */
	public void setCommandSenderType(CommandSenderType commandSenderType) {
		this.commandSenderType = commandSenderType;
	}

	/**
	 * Sets the sub command aliases
	 * 
	 * @param List<String>
	 *            The new aliases
	 */
	public void setAlias(List<String> aliases) {
		this.alias = aliases;
	}

	/**
	 * Sets the sub command name
	 * 
	 * @return String The name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the sub command description
	 * 
	 * @return String The description
	 */
	public String getDescription() {
		return this.decription;
	}

	/**
	 * Sets the sub command CommandSenderType
	 * 
	 * @return CommandSenderType The CommandSenderType
	 */
	public CommandSenderType getCommandSenderType() {
		return this.commandSenderType;
	}

	/**
	 * Sets the sub command aliases
	 * 
	 * @return List<String> The aliases
	 */
	public List<String> getAlias() {
		return this.alias;
	}

}
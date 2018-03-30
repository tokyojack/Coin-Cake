package me.tokyojack.spigot.coincake.utils.kommand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.defaults.BukkitCommand;

/**
* Kommand - https://github.com/tokyojack/Kommand
*
* @author  Tokyojack
* Discord: tokyojack#7353
* McM:     tokyojack
*/

public abstract class Kommand extends BukkitCommand {

	private final static String DEFAULT_DESCRIPTION = "No description set";

	/**
	 * Creates a command with the class name for the command name, default
	 * description, and no alias
	 */
	public Kommand() {
		super(null, DEFAULT_DESCRIPTION, null, new ArrayList<String>());
	}

	/**
	 * Creates a command with the class name for the command label, description,
	 * and no alias
	 *
	 * @param String
	 *            Command description
	 */
	public Kommand(String description) {
		super(null, description, null, new ArrayList<String>());
	}

	/**
	 * Creates a command with the class name for the command name, default
	 * description, and alias
	 *
	 * @param List<String>
	 *            Command alias
	 */
	public Kommand(List<String> alias) {
		super(null, DEFAULT_DESCRIPTION, null, alias);
	}

	/**
	 * Creates a command with the class name for the command name, description,
	 * and alias
	 *
	 * @param String
	 *            Command descriptions
	 * @param List<String>
	 *            Command alias
	 */
	public Kommand(String description, List<String> alias) {
		super(null, description, null, alias);
	}

	/**
	 * Creates a command with the command name, description, and no alias
	 *
	 * @param String
	 *            Command name
	 * @param String
	 *            Command description
	 */
	public Kommand(String name, String description) {
		super(name, description, null, new ArrayList<String>());
	}

	/**
	 * Creates a command with the class name for the command name, description,
	 * and alias
	 *
	 * @param String
	 *            Command name
	 * @param String
	 *            Command description
	 * @param List<String>
	 *            Command alias
	 */
	public Kommand(String name, String description, List<String> alias) {
		super(name, description, null, alias);
	}
}
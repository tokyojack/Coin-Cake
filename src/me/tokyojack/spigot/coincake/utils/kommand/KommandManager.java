package me.tokyojack.spigot.coincake.utils.kommand;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

/**
* Kommand - https://github.com/tokyojack/Kommand
*
* @author  Tokyojack
* Discord: tokyojack#7353
* McM:     tokyojack
*/

public class KommandManager {

	private List<Kommand> commands = new ArrayList<Kommand>();

	/**
	 * Adds a kommand to the manager
	 *
	 * @param Kommand
	 *            Class that extends Kommand
	 */
	public KommandManager addCommand(Kommand kommand) {
		String name = kommand.getName() == null ? kommand.getClass().getSimpleName().toLowerCase() : kommand.getName();

		kommand.setName(name);
		kommand.setUsage("/" + name);

		this.commands.add(kommand);
		return this;
	}

	/**
	 * Registers all the Kommands. MUST BE DONE AFTER ALL THE COMMAND BEEN ADDED
	 * (.addCommand)
	 */
	public void build() {
		this.commands.forEach(command -> {
			try {
				Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

				bukkitCommandMap.setAccessible(true);
				CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

				commandMap.register(command.getName(), command);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
	}

}
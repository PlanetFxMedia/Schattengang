package de.SebastianMikolai.PlanetFx.Schattengang.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ChatUtils {
	
	private static FileConfiguration cfg;
	private static Boolean useprefix;
	private static String prefix;
	
	public ChatUtils(FileConfiguration _cfg) {
		cfg = _cfg;
		useprefix = Boolean.valueOf(cfg.getBoolean("useprefix"));
		prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("prefix"));
	}
	
	public static void sendMessage(Player p, String msg) {
		if (useprefix.booleanValue()) {
			p.sendMessage(prefix  + ChatColor.translateAlternateColorCodes('&', cfg.getString(msg)));
		} else {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString(msg)));
		}
	}
	
	public static void sendMessage(CommandSender cs, String msg) {
		if (useprefix.booleanValue()) {
			cs.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', cfg.getString(msg)));
		} else {
			cs.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString(msg)));
		}
	}
	
	public static void sendMessageConfig(Player p, String msg, String replace, String replace_mitwas) {
		p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', cfg.getString(msg).replace(replace, replace_mitwas)));
	}
}
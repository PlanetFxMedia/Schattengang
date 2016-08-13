package de.SebastianMikolai.PlanetFx.Schattengang;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.SebastianMikolai.PlanetFx.Schattengang.Utils.ChatUtils;

public class Schattengang extends JavaPlugin {

	private static Schattengang instance;
	private int duration;
	private int amplifier;
	private Map<Player, Integer> PlayerTaskID = new HashMap<Player, Integer>();
	private HashMap<Player, Collection<? extends Player>> Playerplayers = new HashMap<Player, Collection<? extends Player>>();
	
	public static Schattengang getInstance() {
		return instance;
	}
	
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		duration = getConfig().getInt("duration");
		amplifier = getConfig().getInt("amplifier");
		new ChatUtils(getConfig());
		getCommand("schattengang").setExecutor(new CommandListener());
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EventListener(), this);
	}
	
	public void setPlayers(Player p, Collection<? extends Player> players) {
		if (Playerplayers.containsKey(p)) {
			Playerplayers.remove(p);
		}
		Playerplayers.put(p, players);
	}
	
	public Collection<? extends Player> getPlayers(Player p) {
		return Playerplayers.get(p);
	}
	
	public void setTaskID(Player p, Integer TaskID) {
		if (PlayerTaskID.containsKey(p)) {
			PlayerTaskID.remove(p);
		}
		PlayerTaskID.put(p, TaskID);
	}

	public Integer getTaskID(Player p) {
		return PlayerTaskID.get(p);
	}

	public void removeTaskID(Player p) {
		PlayerTaskID.remove(p);
	}
	
	public Boolean containsTaskID(Player p) {
		return PlayerTaskID.containsKey(p);
	}

	public Integer getDuration() {
		return duration;
	}
	
	public Integer getAmplifier() {
		return amplifier;
	}
}
package de.SebastianMikolai.PlanetFx.Schattengang;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.SebastianMikolai.PlanetFx.Schattengang.Utils.ChatUtils;

public class CommandListener implements CommandExecutor {
	
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player) {
			Player p = (Player)cs;
			if (args.length > 0) {
				try {
					int duration = Integer.parseInt(args[0]);
					if (duration < Schattengang.getInstance().getDuration() + 1) {
						if (p.hasPermission("schattengang.use")) {
							if (!Schattengang.getInstance().containsTaskID(p)) {
								Schattengang.getInstance().setPlayers(p, Bukkit.getOnlinePlayers());
								for (Player player : Schattengang.getInstance().getPlayers(p)) {
									player.hidePlayer(p);
								}
								p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, duration * 20, Schattengang.getInstance().getAmplifier()));
								p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, duration * 20, Schattengang.getInstance().getAmplifier()));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration * 20, Schattengang.getInstance().getAmplifier()));
								ChatUtils.sendMessage(p, "schattengangBegin");
								Schattengang.getInstance().setTaskID(p, Integer.valueOf(Bukkit.getScheduler().scheduleSyncDelayedTask(Schattengang.getInstance(), new Runnable() {
									@Override
									public void run() {
										if (Schattengang.getInstance().containsTaskID(p)) {
											Schattengang.getInstance().removeTaskID(p);
											for (Player player : Schattengang.getInstance().getPlayers(p)) {
												player.showPlayer(p);
											}
											p.removePotionEffect(PotionEffectType.INVISIBILITY);
											p.removePotionEffect(PotionEffectType.NIGHT_VISION);
											p.removePotionEffect(PotionEffectType.SPEED);
											ChatUtils.sendMessage(p, "schattengangEnd");
										}
									}
								}, duration * 20L)));
							} else {
								ChatUtils.sendMessage(p, "hasSchattengang");
							}
						} else {
							ChatUtils.sendMessage(p, "nopermission");
						}
					} else {
						ChatUtils.sendMessageConfig(p, "maxDuration", "%duration%", String.valueOf(Schattengang.getInstance().getDuration()));
					}
				} catch (Exception e) {
					ChatUtils.sendMessage(p, "useSchattengang");
				}
			} else {
				if (p.hasPermission("schattengang.use")) {
					if (!Schattengang.getInstance().containsTaskID(p)) {
						Schattengang.getInstance().setPlayers(p, Bukkit.getOnlinePlayers());
						for (Player player : Schattengang.getInstance().getPlayers(p)) {
							player.hidePlayer(p);
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Schattengang.getInstance().getDuration() * 20, Schattengang.getInstance().getAmplifier()));
						p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Schattengang.getInstance().getDuration() * 20, Schattengang.getInstance().getAmplifier()));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Schattengang.getInstance().getDuration() * 20, Schattengang.getInstance().getAmplifier()));
						ChatUtils.sendMessage(p, "schattengangBegin");
						Schattengang.getInstance().setTaskID(p, Integer.valueOf(Bukkit.getScheduler().scheduleSyncDelayedTask(Schattengang.getInstance(), new Runnable() {
							@Override
							public void run() {
								if (Schattengang.getInstance().containsTaskID(p)) {
									Schattengang.getInstance().removeTaskID(p);
									for (Player player : Schattengang.getInstance().getPlayers(p)) {
										player.showPlayer(p);
									}
									p.removePotionEffect(PotionEffectType.INVISIBILITY);
									p.removePotionEffect(PotionEffectType.NIGHT_VISION);
									p.removePotionEffect(PotionEffectType.SPEED);
									ChatUtils.sendMessage(p, "schattengangEnd");
								}
							}
						}, Schattengang.getInstance().getDuration() * 20L)));
					} else {
						ChatUtils.sendMessage(p, "hasSchattengang");
					}
				} else {
					ChatUtils.sendMessage(p, "nopermission");
				}
			}
		} else {
			ChatUtils.sendMessage(cs, "console");
		}
		return true;
	}
}
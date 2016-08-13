package de.SebastianMikolai.PlanetFx.Schattengang;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import de.SebastianMikolai.PlanetFx.Schattengang.Utils.ChatUtils;

public class EventListener implements Listener {
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player)e.getDamager();
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
		if (e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
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
	}
}
package me.xnuminousx.spirits.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;

import me.xnuminousx.spirits.ability.spirit.Dash;
import me.xnuminousx.spirits.ability.spirit.Possess;
import me.xnuminousx.spirits.ability.spirit.Soar;
import me.xnuminousx.spirits.ability.dark.Intoxicate;
import me.xnuminousx.spirits.ability.dark.Shackle;
import me.xnuminousx.spirits.ability.dark.Strike;
import me.xnuminousx.spirits.ability.light.Alleviate;
import me.xnuminousx.spirits.ability.light.Shelter;
import me.xnuminousx.spirits.ability.light.Shelter.ShelterType;

public class AbilityListener implements Listener {

	@EventHandler
	public void onSwing(PlayerAnimationEvent event) {

		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

		if (event.isCancelled() || bPlayer == null) {
			return;

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase(null)) {
			return;

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Agility")) {
			new Dash(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Shackle")) {
			new Shackle(player);
			
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Shelter")) {
			new Shelter(player, ShelterType.CLICK);
			
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Strike")) {
			new Strike(player);
			
		}

	}

	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event) {

		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

		if (event.isCancelled() || bPlayer == null) {
			return;

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase(null)) {
			return;

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Possess")) {
			new Possess(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Alleviate")) {
			new Alleviate(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Intoxicate")) {
			new Intoxicate(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Agility")) {
			new Soar(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Shelter")) {
			new Shelter(player, ShelterType.SHIFT);
			
		}

	}

	@EventHandler
	public void onPlayerFall(EntityDamageEvent event) {

		if (event.getEntity() instanceof Player) {
			Element element = Element.getElement("Spirit");
			Player player = (Player) event.getEntity();
			BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

			if (event.isCancelled() || bPlayer == null || bPlayer.hasElement(Element.AIR) || bPlayer.hasElement(Element.EARTH)) {
				return;

			} else if (bPlayer.hasElement(element) && event.getCause() == DamageCause.FALL) {
				event.setDamage(0D);
				event.setCancelled(true);
				
			}
		}
	}
}

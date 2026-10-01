package com.player_data_collector.collector;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;

public class Collector extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        // Damage calculated by Minecraft, converted from health points to hearts
        double damageHearts = event.getFinalDamage() / 2.0;

        // Player's health before the damage
        double healthBeforeHearts = player.getHealth() / 2.0;

        // Why the player took damage
        EntityDamageEvent.DamageCause cause = event.getCause();

        // Wait one tick so Minecraft can apply the damage
        getServer().getScheduler().runTask(this, () -> {

            // Player's actual health after the damage
            double healthAfterHearts = player.getHealth() / 2.0;

            getLogger().info(
                    player.getName()
                            + " took " + damageHearts + " hearts of damage"
                            + " from " + cause
                            + ". Health: "
                            + healthBeforeHearts + " -> "
                            + healthAfterHearts
            );
        });
    }
}
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

        double damage = event.getFinalDamage();
        EntityDamageEvent.DamageCause cause = event.getCause();

        getLogger().info(
                player.getName() + " took " + damage + " damage from " + cause
        );
    }
}
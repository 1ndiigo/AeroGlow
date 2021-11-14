package me.cobble.aeroglow.cmds;

import me.cobble.aeroglow.AeroGlow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Listeners implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (AeroGlow.isGlowing(p.getUniqueId())) {
            p.setGlowing(true);
        }
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        if (AeroGlow.isGlowing(p.getUniqueId())) {
            p.setGlowing(true);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (AeroGlow.isGlowing(p.getUniqueId())) {
            p.setGlowing(true);
        }
    }
}

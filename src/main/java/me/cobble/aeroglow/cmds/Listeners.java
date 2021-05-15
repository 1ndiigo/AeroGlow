package me.cobble.aeroglow.cmds;

import me.cobble.aeroglow.AeroGlow;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Listeners implements Listener {

    private final AeroGlow plugin;

    public Listeners(AeroGlow plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChangeWorld(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(AeroGlow.isGlowing(p)){
            p.setGlowing(true);
        }
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e){
        Player p = e.getPlayer();
        if(AeroGlow.isGlowing(p)){
            p.setGlowing(true);
        }
    }

    @EventHandler
    public void onChangeWorld(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        if(AeroGlow.isGlowing(p)){
            p.setGlowing(true);
        }
    }
}

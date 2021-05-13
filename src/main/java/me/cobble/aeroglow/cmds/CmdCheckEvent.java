package me.cobble.aeroglow.cmds;

import me.cobble.aeroglow.AeroGlow;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.persistence.PersistentDataType;

public class CmdCheckEvent implements Listener {

    private final AeroGlow plugin;

    public CmdCheckEvent(AeroGlow plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCmdRun(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();

        if(e.getMessage().equalsIgnoreCase("/aglow") || e.getMessage().equalsIgnoreCase("/glow:aglow")) {
            if(p.hasPermission("glow.use")) {
                if (!(p.getPersistentDataContainer().has(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER))) {
                    p.getPersistentDataContainer().set(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER, 1);
                }
            }
        }
    }
}

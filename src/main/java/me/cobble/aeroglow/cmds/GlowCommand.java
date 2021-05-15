package me.cobble.aeroglow.cmds;

import me.cobble.aeroglow.AeroGlow;
import me.cobble.aeroglow.Config;
import me.cobble.aeroglow.Utils;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GlowCommand implements CommandExecutor {

    private final AeroGlow plugin;

    public GlowCommand(AeroGlow plugin) {
        this.plugin = plugin;
        plugin.getCommand("glow").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            boolean flipFlop = false;

            if (p.hasPermission("glow.use")) {

                flipFlop = !AeroGlow.isGlowing(p);
                int isGlowingInt = flipFlop ? 1 : 0;
                PersistentDataContainer pdc = p.getPersistentDataContainer();

                pdc.set(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER, isGlowingInt);

                if (Objects.equals(pdc.get(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER), 1)) {
                    p.sendMessage(Utils.chat(Config.get().getString("enable-msg")));
                    p.setGlowing(true);
                    return false;
                }

                // BukkitRunnable

                if (Objects.equals(pdc.get(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER), 1)) {
                    p.sendMessage(Utils.chat(Config.get().getString("disable-msg")));
                    p.setGlowing(false);
                    return false;
                }
            } else {
                p.sendMessage(Utils.chat("&cSorry you don't have permission to use that command"));
            }
        }
        return false;
    }
}

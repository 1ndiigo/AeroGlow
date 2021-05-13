package me.cobble.aeroglow.cmds;

import me.cobble.aeroglow.AeroGlow;
import me.cobble.aeroglow.Config;
import me.cobble.aeroglow.Utils;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GlowCommand implements CommandExecutor {

    private final AeroGlow plugin;
    private boolean flipFlop = false;

    public GlowCommand(AeroGlow plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("aglow")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("glow.use")) {

                flipFlop = !flipFlop;
                int isGlowingInt = flipFlop ? 1 : 0;

                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER, isGlowingInt);

                if (Objects.requireNonNull(p.getPersistentDataContainer().get(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER)) == 1) {
                    p.sendMessage(Utils.chat(Config.get().getString("enable-msg")));
                    p.setGlowing(true);
                    p.getPersistentDataContainer().set(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER, 1);
                    return false;
                }

                if (Objects.requireNonNull(p.getPersistentDataContainer().get(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER)) == 0) {
                    p.sendMessage(Utils.chat(Config.get().getString("disable-msg")));
                    p.setGlowing(false);
                    p.getPersistentDataContainer().set(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER, 0);
                    return false;
                }
            } else {
                p.sendMessage(Utils.chat("&cSorry you don't have permission to use that command"));
            }
        }
        return false;
    }
}

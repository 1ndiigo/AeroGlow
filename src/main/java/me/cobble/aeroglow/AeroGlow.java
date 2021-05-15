package me.cobble.aeroglow;

import me.cobble.aeroglow.cmds.CmdCheckEvent;
import me.cobble.aeroglow.cmds.GlowCommand;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AeroGlow extends JavaPlugin {

    private static AeroGlow plugin;

    public static AeroGlow getPlugin() { // NO_UCD (unused code)
        return plugin;
    }

    @SuppressWarnings("unused")
    public static boolean isGlowing(Player user) {
        if (user.getPersistentDataContainer().has(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER)) {

            // if the boolean equals 1, return true
            return Objects.requireNonNull(user.getPersistentDataContainer().get(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER)) == 1;
        }
        user.getPersistentDataContainer().set(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER, 0);
        return false;
    }

    @Override
    public void onEnable() {
        plugin = this;

        AeroGlow.plugin = AeroGlow.getPlugin();

        this.loadConfig();
        new GlowCommand(plugin);
        new CmdCheckEvent(plugin);
    }

    // loads config.yml
    private void loadConfig() {
        this.saveDefaultConfig();
        Config.setup();
        Config.get().options().copyDefaults(true);
        Config.save();
    }
}

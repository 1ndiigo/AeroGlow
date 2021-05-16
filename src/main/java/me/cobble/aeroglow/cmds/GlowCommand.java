package me.cobble.aeroglow.cmds;

import me.cobble.aeroglow.AeroGlow;
import me.cobble.aeroglow.Config;
import me.cobble.aeroglow.Utils;
import org.bukkit.Bukkit;
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

            if (p.hasPermission("glow.use")) {

                if(args.length == 0) {
                    int isGlowingInt = !AeroGlow.isGlowing(p) ? 1 : 0;
                    PersistentDataContainer pdc = p.getPersistentDataContainer();

                    pdc.set(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER, isGlowingInt);

                    if (Objects.equals(pdc.get(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER), 1)) {
                        p.sendMessage(Utils.chat(Config.get().getString("enable-msg")));
                        p.setGlowing(true);
                        return false;
                    }

                    if (Objects.equals(pdc.get(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER), 0)) {
                        p.sendMessage(Utils.chat(Config.get().getString("disable-msg")));
                        p.setGlowing(false);
                        return false;
                    }
                }

                if(args.length == 1){
                    if(p.hasPermission("glow.admin")) {
                        p.sendMessage(Utils.chat("&cPlease specify value (on/off)"));
                    }
                }

                if(args.length == 2){
                    if(p.hasPermission("glow.admin")) {
                        if(Bukkit.getPlayer(args[0]) != null) {
                            Player target = Bukkit.getPlayer(args[0]);
                            PersistentDataContainer pdc = target.getPersistentDataContainer();

                            if (args[1].equalsIgnoreCase("on")) {
                                pdc.set(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER, 1);
                                p.sendMessage(Utils.chat("&aYou enabled " + target.getName() + "'s glow"));
                                target.setGlowing(true);
                                return false;
                            }

                            if (args[1].equalsIgnoreCase("off")) {
                                pdc.set(new NamespacedKey(plugin, "glowing"), PersistentDataType.INTEGER, 0);
                                p.sendMessage(Utils.chat("&cYou disabled " + target.getName() + "'s glow"));
                                target.setGlowing(false);
                                return false;
                            }
                        }
                    }
                }
            } else {
                p.sendMessage(Utils.chat("&cSorry you don't have permission to use that command"));
            }
        }
        return false;
    }
}

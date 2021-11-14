package me.cobble.aeroglow.cmds;

import me.cobble.aeroglow.AeroGlow;
import me.cobble.aeroglow.Config;
import me.cobble.aeroglow.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GlowCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("glow.use")) {

                if (args.length == 0) {
                    boolean state = AeroGlow.isGlowing(p.getUniqueId());
                    if (!state) {
                        p.sendMessage(Utils.chat(Config.get().getString("enable-msg")));
                        p.setGlowing(true);
                    } else {
                        p.sendMessage(Utils.chat(Config.get().getString("disable-msg")));
                        p.setGlowing(false);
                    }
                    AeroGlow.setGlowing(p.getUniqueId(), !state);
                    return false;
                }

                if (args.length == 1) {
                    if (p.hasPermission("glow.admin")) {
                        p.sendMessage(Utils.chat("&ePlease specify value (on/off)"));
                    }
                }

                if (args.length == 2) {
                    if (p.hasPermission("glow.admin")) {
                        if (Bukkit.getPlayer(args[0]) != null) {
                            Player target = Bukkit.getPlayerExact(args[0]);
                            if (target == null) {
                                p.sendMessage(Utils.chat("&4Player not online!"));
                                return false;
                            }

                            if (args[1].equalsIgnoreCase("on")) {
                                if (AeroGlow.setGlowing(p.getUniqueId(), true)) {
                                    p.sendMessage(Utils.chat("&aYou enabled " + target.getName() + "'s glow"));
                                } else {
                                    p.sendMessage(Utils.chat("&e" + target.getName() + "'s glow is already enabled!"));
                                }
                                target.setGlowing(true);
                                return false;
                            }

                            if (args[1].equalsIgnoreCase("off")) {
                                if (AeroGlow.setGlowing(p.getUniqueId(), false)) {
                                    p.sendMessage(Utils.chat("&cYou disabled " + target.getName() + "'s glow"));
                                } else {
                                    p.sendMessage(Utils.chat("&e" + target.getName() + "'s glow is already disabled!"));
                                }
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

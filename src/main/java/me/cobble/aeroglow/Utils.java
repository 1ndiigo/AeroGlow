package me.cobble.aeroglow;

import org.bukkit.ChatColor;

public class Utils { // NO_UCD (unused code)
    static private final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    public static String chat(final String string) {
        if (!Config.get().getBoolean("legacy-mode")) {
            final String[] texts = string.split(String.format(WITH_DELIMITER, "&"));

            final StringBuilder finalText = new StringBuilder();

            for (int i = 0; i < texts.length; i++) {
                if (texts[i].equalsIgnoreCase("&")) {
                    // get the next string
                    i++;
                    if (texts[i].charAt(0) == '#') {
                        finalText.append(net.md_5.bungee.api.ChatColor.of(texts[i].substring(0, 7)))
                                .append(texts[i].substring(7));
                    } else {
                        finalText.append(ChatColor.translateAlternateColorCodes('&', "&" + texts[i]));
                    }
                } else {
                    finalText.append(texts[i]);
                }
            }
            return finalText.toString();
        }
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}

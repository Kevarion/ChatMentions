package net.kevarion.chatmentions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatMention extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String[] message = event.getMessage().split(" ");

        for (int i = 0; i < message.length; i++) {
            String currentWord = message[i];
            Player player;

            if (currentWord.matches("^//w{3,16}$") && (player = Bukkit.getPlayer(currentWord)) != null) {
                message[i] = ChatColor.BLUE + "@" + player.getName() + ChatColor.RESET;
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F,1F);
            }
        }

        event.setMessage(String.join(" ", message));
    }

}

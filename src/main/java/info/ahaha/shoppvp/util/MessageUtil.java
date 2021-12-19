package info.ahaha.shoppvp.util;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageUtil {
    JavaPlugin plugin;
    public MessageUtil(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public String sendMessage(String message){
        return ChatColor.GOLD+"[ "+plugin.getName()+" ] "+ChatColor.GREEN+message;
    }

    public String sendError(String error){
        return ChatColor.GOLD+"[ "+plugin.getName()+" ] "+ChatColor.RED+error;
    }
}

package info.ahaha.shoppvp.listener;

import info.ahaha.shoppvp.data.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        for (PlayerData data : PlayerData.data){
            if (data.getUuid().equals(e.getPlayer().getUniqueId()))return;
        }
        PlayerData.data.add(new PlayerData(e.getPlayer()));
    }
}

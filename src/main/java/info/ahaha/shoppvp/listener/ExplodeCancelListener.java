package info.ahaha.shoppvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplodeCancelListener implements Listener {

    @EventHandler
    public void onExplode(EntityExplodeEvent e){
        e.blockList().clear();
    }

    @EventHandler
    public void onExplode(BlockExplodeEvent e){
        e.blockList().clear();
    }
}

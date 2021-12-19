package info.ahaha.shoppvp.listener;

import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.data.PlayerData;
import info.ahaha.shoppvp.data.ShopData;
import info.ahaha.shoppvp.util.DropToMoney;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DropListener implements Listener {

    @EventHandler
    public void onCancel(PlayerDropItemEvent e) {
        for (PlayerData data : PlayerData.data) {
            if (data.getUuid().equals(e.getPlayer().getUniqueId())) {
                if (e.getItemDrop().getItemStack().getType() != Material.BUCKET)
                    return;
                if (e.getItemDrop().getItemStack().getItemMeta().getLore() == null)
                    return;
                e.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (e.getItem().getCustomName() != null)
            if (e.getItem().getCustomName().equalsIgnoreCase("ThrowTNT")){
                e.setCancelled(true);
            }
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        for (Material material : ShopPVP.pickupMoney.keySet()) {
            if (e.getItem().getItemStack().getType() == material) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.getInventory().remove(e.getItem().getItemStack());
                    }
                }.runTaskLater(ShopPVP.plugin, 2);
            }
        }

        for (PlayerData data : PlayerData.data) {
            if (data.getUuid().equals(e.getEntity().getUniqueId())) {
                DropToMoney.dropToMoney(e, data);
                return;
            }
        }
    }
}

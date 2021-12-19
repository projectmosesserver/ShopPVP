package info.ahaha.shoppvp.listener;

import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.SkillType;
import info.ahaha.shoppvp.data.EffectData;
import info.ahaha.shoppvp.data.ItemData;
import info.ahaha.shoppvp.data.SkillData;
import info.ahaha.shoppvp.skill.ThrowTNT;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static org.bukkit.Bukkit.getLogger;

public class ItemUseListener implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getHand() != EquipmentSlot.HAND) return;
            for (ItemData data : ItemData.data) {
                if (e.getPlayer().getInventory().getItemInMainHand().isSimilar(data.getItem())) {
                    data.activeEffect(e.getPlayer());
                    e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                    return;
                }
            }
        }
    }
}

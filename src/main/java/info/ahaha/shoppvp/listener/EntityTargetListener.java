package info.ahaha.shoppvp.listener;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.Skill;
import info.ahaha.shoppvp.SkillType;
import info.ahaha.shoppvp.data.EntityData;
import info.ahaha.shoppvp.data.SkillData;
import info.ahaha.shoppvp.skill.ThrowSplash;
import info.ahaha.shoppvp.skill.ThrowTNT;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getLogger;

public class EntityTargetListener implements Listener {

    @EventHandler
    public void onMerge(ItemMergeEvent e) {
        if (e.getEntity().getCustomName() == null) return;
        if (e.getEntity().getCustomName().equalsIgnoreCase("ThrowTNT")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onThrow(EntityTargetEvent e) {
        if (e.getEntity().getCustomName() == null) return;
        int level = 0;
        if (e.getEntity().getCustomName().contains("Lv.")) {
            level = Integer.parseInt(e.getEntity().getCustomName().split("Lv.")[1].split(" ")[0]);
        }
        for (EntityData data : EntityData.data) {
            if (data.getType() == e.getEntity().getType()) {
                if (data.getLevel() == level) {
                    for (Skill skill : data.getSkillData().getSkills()) {
                        LivingEntity entity = (LivingEntity) e.getEntity();
                        if (skill instanceof ThrowTNT) {
                            if (e.getTarget() instanceof Player) {
                                ThrowTNT throwTNT = (ThrowTNT) skill;
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        if (e.getTarget() == null) {
                                            this.cancel();
                                            return;
                                        }
                                        if (entity.isDead()) {
                                            this.cancel();
                                            return;
                                        }
                                        throwTNT.skillActive(entity);

                                    }
                                }.runTaskTimer(ShopPVP.plugin, 0, 20L * throwTNT.getTime());
                            }
                        }
                        if (skill instanceof ThrowSplash) {
                            if (e.getTarget() instanceof Player) {
                                ThrowSplash splash = new ThrowSplash(skill.getRange(), skill.getTime(), ((ThrowSplash) skill).getEffect());
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        if (e.getTarget() == null) {
                                            this.cancel();
                                            return;
                                        }
                                        if (entity.isDead()) {
                                            this.cancel();
                                            return;
                                        }
                                        splash.skillActive(entity);
                                    }
                                }.runTaskTimer(ShopPVP.plugin, 0, 20L * splash.getTime());
                            }
                        }
                    }
                }
            }
        }
    }
}

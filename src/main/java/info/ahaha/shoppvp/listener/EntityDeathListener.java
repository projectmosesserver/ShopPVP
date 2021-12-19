package info.ahaha.shoppvp.listener;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.Skill;
import info.ahaha.shoppvp.SkillType;
import info.ahaha.shoppvp.data.DropData;
import info.ahaha.shoppvp.data.EntityData;
import info.ahaha.shoppvp.data.SkillData;
import info.ahaha.shoppvp.skill.Revival;
import info.ahaha.shoppvp.util.ParticleUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getLogger;

public class EntityDeathListener implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity().getCustomName() == null) return;
        e.getDrops().clear();
        int level = 0;
        if (e.getEntity().getCustomName().contains("Lv.")) {
            level = Integer.parseInt(e.getEntity().getCustomName().split("Lv.")[1].split(" ")[0]);
        }
        for (DropData data : DropData.data) {
            if (data.getType() == e.getEntity().getType()) {
                if (data.getLevel() == level) {
                    for (ItemStack item : data.getItems()) {
                        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
                    }
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDamageEvent e) {
        if (e.getEntity().getCustomName() == null) return;
        int level = 0;
        if (e.getEntity().getCustomName().contains("Lv.")) {
            level = Integer.parseInt(e.getEntity().getCustomName().split("Lv.")[1].split(" ")[0]);
        }
        for (EntityData data : EntityData.data) {
            if (e.getEntity().getType() == data.getType()) {
                if (data.getLevel() == level) {
                    for (Skill skill : data.getSkillData().getSkills()) {
                        if (skill instanceof Revival) {
                            Revival revival = (Revival) skill;
                            if (revival.isRevival()) {
                                LivingEntity entity = (LivingEntity) e.getEntity();
                                if (entity.getHealth() - e.getDamage() <= 0) {
                                    e.setDamage(0);
                                    revival.skillActive(entity);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

package info.ahaha.shoppvp.listener;

import info.ahaha.shoppvp.Skill;
import info.ahaha.shoppvp.data.EntityData;
import info.ahaha.shoppvp.skill.GhastShoot;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LargeFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class GhastShootListener implements Listener {

    @EventHandler
    public void onShoot(ProjectileHitEvent e) {
        int level;
        if (e.getEntity() instanceof LargeFireball) {
            if (e.getEntity().getShooter() instanceof Ghast) {
                for (EntityData data : EntityData.data) {
                    if (data.getType() == EntityType.GHAST) {
                        Ghast ghast = (Ghast) e.getEntity().getShooter();
                        if (ghast.getCustomName() == null) return;
                        if (ghast.getCustomName().contains("Lv.")) {
                            level = Integer.parseInt(ghast.getCustomName().split("Lv.")[1].split(" :")[0]);
                            if (data.getLevel() == level) {
                                for (Skill skill : data.getSkillData().getSkills()) {
                                    if (skill instanceof GhastShoot) {
                                        ((GhastShoot) skill).skillActive(e.getEntity());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

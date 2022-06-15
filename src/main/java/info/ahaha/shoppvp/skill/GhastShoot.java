package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.Skill;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class GhastShoot implements Skill {
    int time;

    public GhastShoot(int time) {
        this.time = time;
    }

    @Override
    public double getRange() {
        return 0;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public int getNum() {
        return 0;
    }

    @Override
    public ActiveType getType() {
        return ActiveType.SHOOT;
    }

    @Override
    public void skillActive(LivingEntity user) {

    }

    public void skillActive(Entity entity) {

        if (entity instanceof LargeFireball) {
            new BukkitRunnable() {
                Location loc;
                int i = 0;

                @Override
                public void run() {
                    loc = entity.getLocation().add(0,1,0);
                    loc.getBlock().setType(Material.LAVA);
                    i++;
                    if (i >= getTime() * 10) {
                        loc.getBlock().setType(Material.AIR);
                        this.cancel();
                    }
                }
            }.runTaskTimer(ShopPVP.plugin, 0, 2);
        }
    }
}

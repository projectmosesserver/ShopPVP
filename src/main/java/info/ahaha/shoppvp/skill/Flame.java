package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class Flame implements Skill {
    double range;
    double damage;
    int time;

    public Flame(double range,int time){
        this.range = range;
        this.time = time;
    }
    @Override
    public double getRange() {
        return range;
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
        return ActiveType.USE;
    }

    @Override
    public void skillActive(LivingEntity user) {
        double range = getRange();
        for (Entity entity : user.getWorld().getNearbyEntities(user.getLocation(),range,range,range)){
            if (entity instanceof LivingEntity){
                if (entity.getUniqueId().equals(user.getUniqueId()))continue;
                LivingEntity entity1 = (LivingEntity) entity;
                entity1.setFireTicks(getTime());
            }
        }
    }
}

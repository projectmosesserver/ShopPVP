package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.Skill;
import org.bukkit.entity.LivingEntity;

public class DragonBreath implements Skill {
    double range;
    double damage;
    int time;

    public DragonBreath(double damage){
        this.damage = damage;
    }
    @Override
    public double getRange() {
        return 0;
    }

    @Override
    public int getTime() {
        return 0;
    }

    @Override
    public double getDamage() {
        return damage;
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

    }

}

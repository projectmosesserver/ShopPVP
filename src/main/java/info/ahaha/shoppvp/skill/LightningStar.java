package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.Skill;
import org.bukkit.entity.LivingEntity;

public class LightningStar implements Skill {

    private final double range;
    private final double damage;

    public LightningStar(int range,double damage){
        this.range = range;
        this.damage = damage;
    }

    @Override
    public double getRange() {
        return range;
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

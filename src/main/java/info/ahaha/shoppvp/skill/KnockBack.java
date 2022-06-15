package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.Skill;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class KnockBack implements Skill {

    private final double range;
    public KnockBack(double range){
        this.range = range;
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
        return 0;
    }

    @Override
    public int getNum() {
        return 0;
    }

    @Override
    public ActiveType getType() {
        return null;
    }

    @Override
    public void skillActive(LivingEntity user) {

    }

    public void skillActive(LivingEntity attacker,LivingEntity victim) {
        victim.setVelocity(attacker.getLocation().getDirection().multiply(getRange() * 0.3D));

    }
}

package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.Skill;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class UpKnockBack implements Skill {

    private final int y;
    public UpKnockBack(int y){
        this.y = y;
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
        return 0;
    }

    @Override
    public int getNum() {
        return 0;
    }

    @Override
    public ActiveType getType() {
        return ActiveType.ATTACK;
    }

    public int getY() {
        return y;
    }

    @Override
    public void skillActive(LivingEntity user) {

    }
    public void skillActive(LivingEntity attacker,LivingEntity victim) {
        Location location = victim.getLocation().add(0, getY(), 0);
        victim.setVelocity(attacker.getLocation().getDirection().multiply((double) 5 * 0.3D));
        location.add(victim.getVelocity());
        victim.teleport(location);

    }
}

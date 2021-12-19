package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.Skill;
import info.ahaha.shoppvp.util.ParticleUtil;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class Revival implements Skill {

    int num;

    public Revival(int num) {
        this.num = num;
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
        return num;
    }

    @Override
    public ActiveType getType() {
        return ActiveType.LOOP;
    }

    public void subtractionNum() {
        if (num-- <= 0) {
            num = 0;
        } else this.num--;
    }
    public boolean isRevival(){
        return num > 0;
    }

    @Override
    public void skillActive(LivingEntity user) {
            user.setHealth(user.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            subtractionNum();
            ParticleUtil.createCircle(user.getLocation(),1, Particle.TOTEM,Particle.REDSTONE, Color.WHITE);
    }
}

package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.Skill;
import info.ahaha.shoppvp.util.ParticleUtil;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Revival implements Skill {

    int num;
    public Map<UUID,Integer>nums = new HashMap<>();

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

    public void putNums(Entity entity){
        nums.put(entity.getUniqueId(),getNum());
    }

    public void subtractionNum(Entity entity) {
        int mapnum = nums.get(entity.getUniqueId());
        nums.put(entity.getUniqueId(), Math.max(mapnum - 1, 0));
    }
    public boolean isRevival(Entity entity){
        int mapnum = nums.get(entity.getUniqueId());
        return mapnum > 0;
    }


    @Override
    public void skillActive(LivingEntity user) {
            user.setHealth(user.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            subtractionNum(user);
            ParticleUtil.createCircle(user.getLocation(),1, Particle.TOTEM,Particle.REDSTONE, Color.WHITE);
    }
}

package info.ahaha.shoppvp;

import org.bukkit.entity.LivingEntity;

public interface Skill {

    double getRange();

    int getTime();

    double getDamage();

    int getNum();

    ActiveType getType();

    void skillActive(LivingEntity user);

}

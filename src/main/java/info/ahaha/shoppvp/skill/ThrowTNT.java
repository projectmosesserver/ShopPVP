package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.Skill;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ThrowTNT implements Skill {

    double damage;
    int time;
    public ThrowTNT(double damage,int time){
        this.damage = damage;
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

        Item tnt = user.getWorld().dropItem(user.getEyeLocation(),new ItemStack(Material.TNT));
        tnt.setVelocity(user.getLocation().getDirection().multiply(1.0));
        tnt.setCustomName("ThrowTNT");
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Entity entity : tnt.getWorld().getNearbyEntities(tnt.getLocation(),0.5,0.5,0.5)){
                    if (entity instanceof LivingEntity) {
                        if (entity.getUniqueId().equals(user.getUniqueId())) continue;
                        ((LivingEntity) entity).damage(getDamage(), user);

                        tnt.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tnt.getLocation(), 1);
                        tnt.getWorld().playSound(tnt.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2, 1);
                        tnt.remove();
                        this.cancel();
                    }
                }
                if (tnt.isOnGround()){
                    tnt.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,tnt.getLocation(),1);
                    tnt.getWorld().playSound(tnt.getLocation(), Sound.ENTITY_GENERIC_EXPLODE,2,1);
                    for (Entity entity : tnt.getWorld().getNearbyEntities(tnt.getLocation(),2.5,2.5,2.5)){
                        if (entity instanceof LivingEntity){
                            if (entity.equals(user))continue;
                            ((LivingEntity) entity).damage(getDamage(),user);
                        }
                    }
                    tnt.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(ShopPVP.plugin,0,2);
    }
}

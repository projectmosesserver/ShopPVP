package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.Skill;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ThrowSplash implements Skill {

    private final double range;
    private final PotionEffect effect;
    private final int time;


    public ThrowSplash(double range, int time, PotionEffect effect) {
        this.range = range;
        this.effect = effect;
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
        return ActiveType.LOOP;
    }

    public PotionEffect getEffect() {
        return effect;
    }

    @Override
    public void skillActive(LivingEntity user) {

        ItemStack item = new ItemStack(Material.SPLASH_POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.addCustomEffect(getEffect(), true);
        item.setItemMeta(meta);
        ThrownPotion potion = user.getWorld().spawn(user.getLocation().add(0, 2.5, 0), ThrownPotion.class);
        potion.setItem(item);
        potion.setShooter(user);
        potion.setVelocity(user.getLocation().getDirection().multiply(1.0D));
    }

    public void pushAwayEntity(Entity entity, double speed, LivingEntity shooter) {
        Vector unitVector = entity.getLocation().toVector().subtract(shooter.getLocation().toVector()).normalize();
        entity.setVelocity(unitVector.multiply(speed));
    }
}

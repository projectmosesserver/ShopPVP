package info.ahaha.shoppvp.listener;

import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.data.SpawnerLoc;
import info.ahaha.shoppvp.skill.ThrowSplash;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class EntitySpawnListener implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {

        /*if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER ||
                e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.COMMAND ||
                e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM) {
            if (e.getEntity().getWorld().getTime() < 12000) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(true);
        }*/
    }

    @EventHandler
    public void onSpawner(SpawnerSpawnEvent e) {
        LivingEntity entity = (LivingEntity) e.getEntity();
        for (SpawnerLoc loc : SpawnerLoc.locs) {
            if (loc.equalSpawner(e.getSpawner().getBlock())) {
                e.getSpawner().setMaxNearbyEntities(3);
                if (loc.getLevel() == 15)
                    entity.setCustomName("Lv." + getRandomNumberInRange(15, 20) + " : " + entity.getName());
                else
                    entity.setCustomName("Lv." + getRandomNumberInRange(loc.getLevel(), loc.getLevel() + 10) + " : " + entity.getName());

                entity.setCustomNameVisible(true);
                int level = Integer.parseInt(entity.getCustomName().split("Lv.")[1].split(" :")[0]);
                entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2 * level);
                entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                if (entity.getType() == EntityType.ENDER_DRAGON) {
                    entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(
                            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + (level * 0.1));

                } else if (entity.getType() == EntityType.WITHER) {
                    entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(
                            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + (level * 0.1));

                } else if (entity.getType() == EntityType.ELDER_GUARDIAN) {
                    entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(
                            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + (level * 0.06));

                } else {
                    entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(
                            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + (level * 0.05));
                }
                if (entity instanceof Ghast) {
                    new BukkitRunnable() {
                        int i = 0;

                        @Override
                        public void run() {
                            if (entity.isDead()) {
                                this.cancel();
                            }
                            if (i >= 120) {
                                entity.remove();
                                this.cancel();
                            }
                            i++;
                        }
                    }.runTaskTimer(ShopPVP.plugin, 0, 20);
                }
            }
        }
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}

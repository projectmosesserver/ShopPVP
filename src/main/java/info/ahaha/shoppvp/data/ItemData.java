package info.ahaha.shoppvp.data;

import info.ahaha.shoppvp.Skill;
import info.ahaha.shoppvp.SkillType;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class ItemData {

    private final ItemStack item;
    private final String id;
    private final double range;

    public static List<ItemData> data = new ArrayList<>();

    public ItemData(ItemStack item, String id, int range) {
        this.item = item;
        this.id = id;
        this.range = range;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getID() {
        return id;
    }


    public double getRange() {
        return range;
    }

    public void activeEffect(Player player) {
        for (EffectData data : EffectData.data) {
            if (data.getID().equalsIgnoreCase(getID())) {
                double range = getRange() / 2;
                for (int i = 0; i < 30; i++)
                    player.spawnParticle(Particle.REDSTONE, player.getLocation(), 10, range, range, range, 1, new Particle.DustOptions(data.getColor(), 3));
                for (Entity entity : player.getWorld().getNearbyEntities(player.getLocation(), range, range, range)) {
                    if (entity instanceof LivingEntity) {
                        if (entity.equals(player)) continue;
                        LivingEntity living = (LivingEntity) entity;
                        for (PotionEffect effect : data.getEffects())
                        living.addPotionEffect(effect);
                    }
                }
                return;
            }
        }
        Color color = null;
        for (SkillData data : SkillData.data) {
            if (data.getId().equalsIgnoreCase(getID())) {
                for (Skill skill : data.getSkills()) {
                    color = data.getColor();
                    skill.skillActive(player);
                }
            }
        }
        double range = getRange() / 2;
        for (int i = 0; i < 30; i++) {
            if (color == null) return;
            player.spawnParticle(Particle.REDSTONE, player.getLocation(), 10, range, range, range, 0, new Particle.DustOptions(color, 3));
        }
    }
}

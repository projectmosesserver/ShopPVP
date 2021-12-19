package info.ahaha.shoppvp.skill;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.Skill;
import info.ahaha.shoppvp.util.ParticleUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CircleMagma implements Skill {
    double range;

    public CircleMagma(double range) {
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
        return ActiveType.HIT;
    }

    @Override
    public void skillActive(LivingEntity user) {

        List<Location>locations = new ArrayList<>();
        ParticleUtil.createCircle(user.getLocation(), (int) getRange(), Particle.FLAME, Particle.REDSTONE, Color.RED);
        for (Entity entity : user.getWorld().getNearbyEntities(user.getLocation(), range, range, range)) {
            if (entity instanceof Player){
                Player player = (Player) entity;
                player.getLocation().getBlock().setType(Material.LAVA);
                locations.add(player.getLocation().getBlock().getLocation());
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (locations.size() != 0){
                    for (Location location : locations){
                        location.getBlock().setType(Material.AIR);
                    }
                }
            }
        }.runTaskLater(ShopPVP.plugin,10);
    }
}

package info.ahaha.shoppvp.util;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

import static org.bukkit.Bukkit.getLogger;

public class ParticleUtil {

    public static void createCircle(Location location, int size, Particle particle1, Particle particle2) {
        createCircle(location, size, particle1, particle2, null);
    }

    public static void createCircle(Location location, int size, Particle particle1, Particle particle2, Color color) {
        for (int d = 0; d <= 90; d++) {
            Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ()).clone();
            particleLoc.setX(location.getX() + Math.cos(d) * size);
            particleLoc.setY(location.getY() + 1);
            particleLoc.setZ(location.getZ() + Math.sin(d) * size);
            if (color != null) {
                if (particle1.name().equalsIgnoreCase("REDSTONE")) {
                    location.getWorld().spawnParticle(particle1, particleLoc, 1, new Particle.DustOptions(color, 1));
                    location.getWorld().spawnParticle(particle2, particleLoc, 1, 0.1, 0.2, 0.1, 0);
                } else if (particle2.name().equalsIgnoreCase("REDSTONE")) {
                    location.getWorld().spawnParticle(particle1, particleLoc, 1, 0.1, 0.2, 0.1, 0);
                    location.getWorld().spawnParticle(particle2, particleLoc, 1, new Particle.DustOptions(color, 1));
                }
            } else {
                location.getWorld().spawnParticle(particle1, particleLoc, 1, 0.1, 0.2, 0.1, 0);
                location.getWorld().spawnParticle(particle2, particleLoc, 1, 0.1, 0.2, 0.1, 0);
            }
        }
    }
}

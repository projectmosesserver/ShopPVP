package info.ahaha.shoppvp.data;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpawnerLoc implements Serializable {

    private int x,y,z;
    private String world;
    private int level;
    private transient Location location;
    public static List<SpawnerLoc>locs = new ArrayList<>();

    public SpawnerLoc(Block block, int level){
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.world = block.getWorld().getName();
        this.level =level;
        location = new Location(Bukkit.getWorld(world),x,y,z);
    }

    public String getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getLevel() {
        return level;
    }

    public Location getLocation() {
        if (location == null){
            location = new Location(Bukkit.getWorld(world),x,y,z);
        }
        return location;
    }

    public boolean equalSpawner(Block block){
        return block.getLocation().equals(getLocation());
    }
}

package info.ahaha.shoppvp.data;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DropData {

    private final int level;
    private final EntityType type;
    private final List<ItemStack> items;
    public static List<DropData> data = new ArrayList<>();

    public DropData(int level, EntityType type, List<ItemStack> items) {
        this.level = level;
        this.type = type;
        this.items = items;
    }

    public int getLevel() {
        return level;
    }

    public EntityType getType() {
        return type;
    }

    public List<ItemStack> getItems() {

        return items;
    }
}

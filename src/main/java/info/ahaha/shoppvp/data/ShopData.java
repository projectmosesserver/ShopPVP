package info.ahaha.shoppvp.data;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ShopData {

    private final String npcName;
    private final String shopName;
    private final Map<ItemStack, Integer> items;
    public static List<ShopData>data = new ArrayList<>();

    public ShopData(String npcName,String shopName, Map<ItemStack, Integer> items) {
        this.npcName = npcName;
        this.shopName = shopName;
        this.items = items;
    }

    public String getNpcName() {
        return npcName;
    }

    public String getShopName() {
        return shopName;
    }

    public Map<ItemStack, Integer> getItems() {
        return items;
    }

    public int getPrice(ItemStack item) {
        if (items.containsKey(item)) {

            return items.get(item);
        }
        return 0;
    }

    public ItemStack getGoods(ItemStack item) {
        ItemStack itemStack = item.clone();
        ItemMeta meta = itemStack.getItemMeta();
        if (item.getType().name().contains("CHEST") ||
                item.getType().name().contains("BOOTS") ||
                item.getType().name().contains("LEGGINGS") ||
                item.getType().name().contains("HELMET")
        ) {
            if (meta.getLore().size() == 2) {
                meta.setLore(new ArrayList<>(Arrays.asList(
                        ChatColor.GRAY + "-------------------------",
                        ChatColor.GOLD + "Equipment"
                )));
                item.setItemMeta(meta);
                return item;
            }
        } else {
            if (meta.getLore().size() == 2) {
                meta.setLore(new ArrayList<>(Arrays.asList(
                        ChatColor.GRAY + "-------------------------",
                        ChatColor.GOLD + "Weapon"
                )));
            } else {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "-------------------------");
                lore.add(ChatColor.GOLD + "Weapon");
                lore.add(meta.getLore().get(2));
                lore.add(meta.getLore().get(3));
                lore.add(meta.getLore().get(4));
                lore.add(meta.getLore().get(5));
                meta.setLore(lore);
            }
            item.setItemMeta(meta);
            return item;
        }
        return null;
    }
}

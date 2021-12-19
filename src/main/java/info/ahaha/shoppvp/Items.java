package info.ahaha.shoppvp;

import info.ahaha.mosesmoney.MosesMoney;
import info.ahaha.shoppvp.data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class Items {

    public static ItemStack getPlayerInfo(Player player) {
        ItemStack item = new ItemStack(Material.BUCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + player.getName() + "'s Information");
        String money = MosesMoney.plugin.resultDisplayMoney(MosesMoney.plugin.getBalance(player.getUniqueId()));
        for (PlayerData data : PlayerData.data) {
            if (data.getUuid().equals(player.getUniqueId())) {
                meta.setLore(new ArrayList<>(Arrays.asList(
                        ChatColor.GRAY + "-----------------------------",
                        "",
                        ChatColor.YELLOW + "Money : " + ChatColor.AQUA + money,
                        ChatColor.YELLOW + "PlayerKill : " + ChatColor.AQUA + data.getPlayerKillTotal(),
                        ChatColor.YELLOW + "PlayerDeath : " + ChatColor.AQUA + data.getPlayerDeathTotal(),
                        ChatColor.YELLOW + "MobKill : " + ChatColor.AQUA + data.getMobKillTotal(),
                        ChatColor.YELLOW + "MobDeath : " + ChatColor.AQUA + data.getMobDeathTotal(),
                        "",
                        ChatColor.GRAY + "-----------------------------"
                )));
            }
            item.setItemMeta(meta);
            return item;
        }
        return null;
    }

    public static ItemStack getSpawner(int level){
        ItemStack item = new ItemStack(Material.SPAWNER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Lv"+level+".Spawner");
        item.setItemMeta(meta);
        return item;
    }
}

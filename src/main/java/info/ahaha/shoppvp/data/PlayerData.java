package info.ahaha.shoppvp.data;

import info.ahaha.mosesmoney.MosesMoney;
import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.util.ItemSerializable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class PlayerData implements Serializable {

    private final UUID uuid;
    private String name;
    private final Map<EntityType, Integer> mobKill = new HashMap<>();
    private final Map<EntityType, Integer> mobDeath = new HashMap<>();
    private final Map<String, Integer> playerKill = new HashMap<>();
    private final Map<String, Integer> playerDeath = new HashMap<>();
    private transient ItemStack collateral;
    private String collateralData;
    private double debt = 0;
    private boolean debtCheck = true;

    public static List<PlayerData> data = new ArrayList<>();

    public PlayerData(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return MosesMoney.plugin.getBalance(getUuid());
    }

    public void setMoney(double money) {
        MosesMoney.plugin.setBalance(getUuid(), money);
    }

    public void depositMoney(double money) {
        MosesMoney.plugin.depositMoney(getUuid(), money);
    }

    public boolean withdrawMoney(double money) {
        return MosesMoney.plugin.withdrawMoney(getUuid(), money);
    }

    public double getBank() {
        return MosesMoney.plugin.getBankBalance(getUuid());
    }

    public void setBank(double money) {
        MosesMoney.plugin.setBankBalance(getUuid(), money);
    }

    public void depositBank(double money) {
        MosesMoney.plugin.depositBank(getUuid(), money);
    }

    public boolean withdrawBank(double money) {
        return MosesMoney.plugin.withdrawBank(getUuid(), money);
    }

    public int getMobKillTotal() {
        int i = 0;
        for (int c : mobKill.values()) {
            i += c;
        }
        return i;
    }

    public int getMobDeathTotal() {
        int i = 0;
        for (int c : mobDeath.values()) {
            i += c;
        }
        return i;
    }

    public int getPlayerKillTotal() {
        int i = 0;
        for (int c : playerKill.values()) {
            i += c;
        }
        return i;
    }

    public int getPlayerDeathTotal() {
        int i = 0;
        for (int c : playerDeath.values()) {
            i += c;
        }
        return i;
    }

    public int getMobKill(EntityType type) {
        if (mobKill.containsKey(type)) {
            return mobKill.get(type);
        }
        return 0;
    }

    public int getMobDeath(EntityType type) {
        if (mobDeath.containsKey(type)) {
            return mobDeath.get(type);
        }
        return 0;
    }

    public int getPlayerKill(String name) {
        if (playerKill.containsKey(name)) {
            return playerKill.get(name);
        }
        return 0;
    }

    public int getPlayerDeath(String name) {
        if (playerDeath.containsKey(name)) {
            return playerDeath.get(name);
        }
        return 0;
    }

    public List<String> getMobDeathInfo() {
        List<String> list = new ArrayList<>();
        for (EntityType type : mobDeath.keySet()) {
            list.add(ChatColor.YELLOW + type.name() + ChatColor.GRAY + " / " + ChatColor.AQUA + mobDeath.get(type));
        }
        return list;
    }

    public List<String> getMobKillInfo() {
        List<String> list = new ArrayList<>();
        for (EntityType type : mobKill.keySet()) {
            list.add(ChatColor.YELLOW + type.name() + ChatColor.GRAY + " / " + ChatColor.AQUA + mobKill.get(type));
        }
        return list;
    }

    public List<String> getPlayerDeathInfo() {
        List<String> list = new ArrayList<>();
        for (String name : playerDeath.keySet()) {
            list.add(ChatColor.YELLOW + name + ChatColor.GRAY + " / " + ChatColor.AQUA + playerDeath.get(name));
        }
        return list;
    }

    public List<String> getPlayerKillInfo() {
        List<String> list = new ArrayList<>();
        for (String name : playerKill.keySet()) {
            list.add(ChatColor.YELLOW + name + ChatColor.GRAY + " / " + ChatColor.AQUA + playerKill.get(name));
        }
        return list;
    }

    public void addMobKill(EntityType type) {
        if (mobKill.containsKey(type)) {
            mobKill.put(type, mobKill.get(type) + 1);
        } else
            mobKill.put(type, 1);
    }

    public void addMobDeath(EntityType type) {
        if (mobDeath.containsKey(type)) {
            mobDeath.put(type, mobDeath.get(type) + 1);
        } else
            mobDeath.put(type, 1);
    }

    public void addPlayerKill(String name) {
        if (playerKill.containsKey(name)) {
            playerKill.put(name, playerKill.get(name) + 1);
        } else
            playerKill.put(name, 1);
    }

    public void addPlayerDeath(String name) {
        if (playerDeath.containsKey(name)) {
            playerDeath.put(name, playerDeath.get(name) + 1);
        } else
            playerDeath.put(name, 1);
    }

    public boolean setMobKill(EntityType type, int amount) {
        if (mobKill.containsKey(type)) {
            mobKill.put(type, amount);
            return true;
        }
        return false;
    }

    public boolean setMobDeath(EntityType type, int amount) {
        if (mobKill.containsKey(type)) {
            mobKill.put(type, amount);
            return true;
        }
        return false;
    }

    public boolean setPlayerKill(String name, int amount) {
        if (playerKill.containsKey(name)) {
            playerKill.put(name, amount);
            return true;
        }
        return false;
    }

    public boolean setPlayerDeath(String name, int amount) {
        if (playerDeath.containsKey(name)) {
            playerDeath.put(name, amount);
            return true;
        }
        return false;
    }

    public void rePayment(double money) {
        Player player = Bukkit.getPlayer(this.uuid);
        this.debt -= money;
        if (this.debt <= 0) {
            this.debt = 0;
            setDeptCheck(true);
            player.sendMessage(ChatColor.GOLD + "[ ShopPVP ] " + ChatColor.GREEN + "-" + money + " / 残り" + MosesMoney.plugin.resultDisplayMoney(this.debt));
            player.sendMessage(ChatColor.GOLD + "[ ShopPVP ] " + ChatColor.GREEN + "借金が返済完了されました！銀行のメニューの借入より担保の受け取りが可能です！");
        } else {
            player.sendMessage(ChatColor.GOLD + "[ ShopPVP ] " + ChatColor.GREEN + "-" + money + " / 残り" + MosesMoney.plugin.resultDisplayMoney(this.debt));
        }
    }

    public void debt(UUID uuid, double money) {
        if (getUuid().equals(uuid)) {
            if (debtCheck) {
                this.debt = money;
                depositMoney(money);
                setDeptCheck(false);
                setInfo();
                Bukkit.getPlayer(getUuid()).sendMessage(ChatColor.GOLD + "[ ShopPVP ] " + ChatColor.GREEN + MosesMoney.plugin.resultDisplayMoney(money) + " 借入しました！");
            } else {
                Bukkit.getPlayer(uuid).sendMessage(ChatColor.GOLD + "[ ShopPVP ] " + ChatColor.RED + "借金を返済し終えていないため借入できません！");
            }
        }
    }

    public boolean getDebtCheck() {
        return this.debtCheck;
    }

    public void setDeptCheck(boolean debtCheck) {
        this.debtCheck = debtCheck;
    }

    public ItemStack getCollateral() {
        if (collateral == null) {
            if (collateralData != null) {
                try {
                    collateral = ItemSerializable.itemStackFromBase64(collateralData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return collateral;
    }

    public void setCollateral(ItemStack collateral) {
        this.collateral = collateral;
        setCollateralData(collateral);
    }

    public String getCollateralData() {
        return collateralData;
    }

    public void setCollateralData(ItemStack item) {
        this.collateralData = ItemSerializable.itemStackToBase64(item);
    }

    public ItemStack getInfo() {
        Player player = Bukkit.getPlayer(getUuid());
        for (ItemStack items : player.getInventory().getContents()) {
            if (items == null) continue;
            if (items.getType() == Material.BUCKET) {
                if (items.getItemMeta() != null) {
                    return items;
                }
            }
        }
        return null;
    }

    public void setInfo() {
        if (getInfo() == null) return;
        Player player = Bukkit.getPlayer(getUuid());
        ItemStack item = getInfo();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + player.getName() + "'s Information");
        String money = MosesMoney.plugin.resultDisplayMoney(MosesMoney.plugin.getBalance(player.getUniqueId()));
        meta.setLore(new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "-----------------------------",
                "",
                ChatColor.YELLOW + "Money : " + ChatColor.AQUA + money,
                ChatColor.YELLOW + "PlayerKill : " + ChatColor.AQUA + getPlayerKillTotal(),
                ChatColor.YELLOW + "PlayerDeath : " + ChatColor.AQUA + getPlayerDeathTotal(),
                ChatColor.YELLOW + "MobKill : " + ChatColor.AQUA + getMobKillTotal(),
                ChatColor.YELLOW + "MobDeath : " + ChatColor.AQUA + getMobDeathTotal(),
                "",
                ChatColor.GRAY + "-----------------------------"
        )));
        item.setItemMeta(meta);
    }

}

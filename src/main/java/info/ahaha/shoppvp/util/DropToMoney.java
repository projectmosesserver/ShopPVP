package info.ahaha.shoppvp.util;

import info.ahaha.mosesmoney.MosesMoney;
import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.data.PlayerData;
import info.ahaha.shoppvp.data.ShopData;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class DropToMoney {

    public static void dropToMoney(EntityPickupItemEvent e, PlayerData data){
        Player player = (Player) e.getEntity();
        for (Material material : ShopPVP.pickupMoney.keySet()) {
            if (e.getItem().getItemStack().getType() == material) {
                int dropMoney = (int) (e.getItem().getItemStack().getAmount() * ShopPVP.pickupMoney.get(material));
                if (data.getDebtCheck()) {
                    data.depositMoney(dropMoney);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "+ " + dropMoney + "厳" + ChatColor.GRAY + " / " + ChatColor.AQUA + MosesMoney.plugin.resultDisplayMoney(data.getMoney())));
                    //data.setInfo();
                } else {
                    data.rePayment(dropMoney);
                }
            }
        }
    }
}

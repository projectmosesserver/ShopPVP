package info.ahaha.shoppvp.commands;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.ShopPVP;
import info.ahaha.shoppvp.SkillType;
import info.ahaha.shoppvp.data.EffectData;
import info.ahaha.shoppvp.data.EntityData;
import info.ahaha.shoppvp.data.ItemData;
import info.ahaha.shoppvp.data.SkillData;
import info.ahaha.shoppvp.skill.Revival;
import info.ahaha.shoppvp.skill.ThrowTNT;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class Cmd implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))return false;
        Player player = (Player) sender;
        if (!player.isOp()){
            player.sendMessage(ShopPVP.plugin.message.sendError("コマンドを実行する権限がありません！"));
            return false;
        }
        if (args[0].equalsIgnoreCase("get")){
            for (ItemData data : ItemData.data){
                if (args[1].equalsIgnoreCase(data.getID())){
                    player.getInventory().addItem(data.getItem());
                    player.sendMessage(ShopPVP.plugin.message.sendMessage(data.getID()+"を受け取りました"));
                }
            }
        }
        if (args[0].equalsIgnoreCase("spawn")){
            Zombie zombie = player.getWorld().spawn(player.getLocation(),Zombie.class);
            zombie.setCustomName("Lv.18 : "+zombie.getName());
            zombie.setCustomNameVisible(true);
            SkillData skillData = null;
            EffectData effectData = null;
            for (SkillData data : SkillData.data){
                if (data.getId().equalsIgnoreCase("ZOMBIE1")){
                    skillData = data;
                }
            }
            for (EffectData data : EffectData.data){

                if (data.getID().equalsIgnoreCase("ZOMBIE1")){
                    if (data.getKeepEffects() != null){
                        for (PotionEffect effect : data.getKeepEffects()){
                            zombie.addPotionEffect(effect);
                        }
                    }
                    effectData = data;
                }
            }
            if (skillData != null && effectData != null) {
                EntityData data1 = new EntityData(zombie.getType(), 18, effectData, skillData);
                EntityData.data.add(data1);
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args[0].equalsIgnoreCase("get")){
            if (args.length == 2){
                List<String>list = new ArrayList<>();
                for (ItemData data : ItemData.data){
                    list.add(data.getID());
                }
                return list;
            }
        }
        return null;
    }
}

package info.ahaha.shoppvp.data;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.SkillType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EffectData {
    private final String id;
    private ActiveType type;
    private List<PotionEffect> effects;
    private Color color;
    public static List<EffectData> data = new ArrayList<>();

    public EffectData(String id, ActiveType type,List<PotionEffect> effects,  Color color) {
        this.id = id;
        this.type = type;
        this.color = color;
    }

    public EffectData(String id, ActiveType type,List<PotionEffect> effects) {
        this.id = id;
        this.type = type;
        this.effects = effects;
    }

    public List<PotionEffect> getEffects() {
        return effects;
    }

    public String getID() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public ActiveType getType() {
        return type;
    }

    public void randomEffects(LivingEntity target) {
        Random random = new Random();
        int r = random.nextInt(getEffects().size() - 1);
        target.addPotionEffect(getEffects().get(r));
    }

    public void randomExplodeEffects(LivingEntity exploder) {
        Random random = new Random();
        int r = random.nextInt(getEffects().size() - 1);
        ItemStack potion = new ItemStack(Material.LINGERING_POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.addCustomEffect(getEffects().get(r), true);
        potion.setItemMeta(meta);
        ThrownPotion thrownPotion = (ThrownPotion) exploder.getWorld().spawnEntity(exploder.getLocation(), EntityType.SPLASH_POTION);
        thrownPotion.setItem(potion);

    }

    public void addEffect(LivingEntity target) {
        for (PotionEffect effect : getEffects())
        target.addPotionEffect(effect);
    }

    public void addMultiEffects(LivingEntity target){
        for (PotionEffect effect : getEffects()){
            target.addPotionEffect(effect);
        }
    }

}

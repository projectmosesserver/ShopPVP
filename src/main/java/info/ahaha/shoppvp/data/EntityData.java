package info.ahaha.shoppvp.data;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.Skill;
import info.ahaha.shoppvp.SkillType;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class EntityData {

    private final EntityType type;
    private final int level;
    private final EffectData effectData;
    private SkillData skillData;
    public static List<EntityData> data = new ArrayList<>();

    public EntityData(EntityType type, int level,  EffectData effectData) {
        this.type = type;
        this.level = level;
        this.effectData = effectData;
    }

    public EntityData(EntityType type, int level, EffectData effectData, SkillData skillData) {
        this.type = type;
        this.level = level;
        this.effectData = effectData;
        this.skillData = skillData;
    }

    public EntityData(EntityType type, int level, SkillData skillData) {
        this.type = type;
        this.level = level;
        this.skillData = skillData;
        effectData = null;
    }

    public EntityType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public EffectData getEffectData() {
        return effectData;
    }

    public SkillData getSkillData() {
        return skillData;
    }
}

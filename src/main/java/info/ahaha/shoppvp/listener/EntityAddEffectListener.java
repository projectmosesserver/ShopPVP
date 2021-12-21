package info.ahaha.shoppvp.listener;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.Skill;
import info.ahaha.shoppvp.data.EntityData;
import info.ahaha.shoppvp.data.SkillData;
import info.ahaha.shoppvp.skill.CircleMagma;
import info.ahaha.shoppvp.skill.Revival;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static org.bukkit.Bukkit.getLogger;

public class EntityAddEffectListener implements Listener {

    @EventHandler
    public void addEffect(EntityDamageByEntityEvent e) {

        for (EntityData data : EntityData.data) {
            int level = 0;
            if (e.getDamager().getType() == data.getType()) {
                if (e.getDamager().getCustomName() != null) {
                    if (e.getDamager().getCustomName().contains("Lv.")) {
                        level = Integer.parseInt(e.getDamager().getCustomName().split("Lv.")[1].split(" :")[0]);
                        if (data.getLevel() == level) {
                            if (data.getEffectData().getType() == ActiveType.ATTACK) {
                                if (data.getEffectData().isRandom()) {
                                    data.getEffectData().randomEffects((LivingEntity) e.getEntity());
                                } else
                                    data.getEffectData().addEffect((LivingEntity) e.getEntity());
                            }
                            for (Skill skill : data.getSkillData().getSkills()) {
                                if (skill.getType() == ActiveType.ATTACK) {
                                    skill.skillActive((LivingEntity) e.getEntity());
                                }
                            }
                            return;
                        }
                    }
                }
            }
            if (e.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) e.getDamager();
                if (arrow.getShooter() instanceof Skeleton ) {
                    Skeleton skeleton = (Skeleton) arrow.getShooter();
                    if (skeleton.getCustomName() != null) {
                        if (skeleton.getCustomName().contains("Lv.")) {
                            level = Integer.parseInt(skeleton.getCustomName().split("Lv.")[1].split(" :")[0]);
                            if (data.getLevel() == level) {
                                if (data.getEffectData().getType() == ActiveType.ATTACK) {
                                    if (data.getEffectData().isRandom()) {
                                        data.getEffectData().randomEffects((LivingEntity) e.getEntity());
                                    } else
                                        data.getEffectData().addEffect((LivingEntity) e.getEntity());
                                }
                                for (Skill skill : data.getSkillData().getSkills()) {
                                    if (skill.getType() == ActiveType.ATTACK) {
                                        skill.skillActive((LivingEntity) e.getEntity());
                                    }
                                }
                                return;
                            }
                        }
                    }
                } else if (arrow.getShooter() instanceof Player) {
                    int damage = (int) e.getDamage();
                    LivingEntity entity = (LivingEntity) e.getEntity();
                    int max = (int) entity.getMaxHealth();
                    int health = (int) entity.getHealth();
                    int healths = health - damage;
                    if (healths < 0)
                        healths = 0;
                    Player player = (Player) arrow.getShooter();
                    EntityType type = e.getEntityType();
                    if (type == EntityType.COW || type == EntityType.PIG || type == EntityType.RABBIT || type == EntityType.GOAT ||
                            type == EntityType.CHICKEN || type == EntityType.BEE || type == EntityType.SQUID || type == EntityType.DONKEY ||
                            type == EntityType.TURTLE || type == EntityType.CAT || type == EntityType.HORSE || type == EntityType.POLAR_BEAR
                            || type == EntityType.AXOLOTL || type == EntityType.SALMON || type == EntityType.COD
                            || type == EntityType.TROPICAL_FISH || type == EntityType.PUFFERFISH || type == EntityType.MUSHROOM_COW
                            || type == EntityType.VILLAGER || type == EntityType.OCELOT || type == EntityType.GLOW_SQUID ||
                            type == EntityType.PARROT || type == EntityType.FOX || type == EntityType.WOLF
                            || type == EntityType.MULE || type == EntityType.SKELETON_HORSE || type == EntityType.WANDERING_TRADER ||
                            type == EntityType.TRADER_LLAMA || type == EntityType.LLAMA || type == EntityType.SHEEP ||
                            type == EntityType.DOLPHIN) {

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + type.name() + ChatColor.GRAY + " | " + ChatColor.GREEN + "" + ChatColor.BOLD + healths + " / " + max));
                    } else if (entity.getType() == EntityType.PLAYER) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + e.getEntity().getName() + ChatColor.GRAY + " | " + ChatColor.GREEN + "" + ChatColor.BOLD + healths + " / " + max));
                    } else {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + entity.getCustomName() + ChatColor.GRAY + " | " + ChatColor.GREEN + "" + ChatColor.BOLD + healths + " / " + max));
                    }
                }
            }
            if (e.getDamager() instanceof Player) {
                Player player = (Player) e.getDamager();
                int damage = (int) e.getDamage();
                LivingEntity entity = (LivingEntity) e.getEntity();
                int max = (int) entity.getMaxHealth();
                int health = (int) entity.getHealth();
                int healths = health - damage;
                if (healths < 0)
                    healths = 0;
                EntityType type = e.getEntityType();
                if (type == EntityType.COW || type == EntityType.PIG || type == EntityType.RABBIT || type == EntityType.GOAT ||
                        type == EntityType.CHICKEN || type == EntityType.BEE || type == EntityType.SQUID || type == EntityType.DONKEY ||
                        type == EntityType.TURTLE || type == EntityType.CAT || type == EntityType.HORSE || type == EntityType.POLAR_BEAR
                        || type == EntityType.AXOLOTL || type == EntityType.SALMON || type == EntityType.COD
                        || type == EntityType.TROPICAL_FISH || type == EntityType.PUFFERFISH || type == EntityType.MUSHROOM_COW
                        || type == EntityType.VILLAGER || type == EntityType.OCELOT || type == EntityType.GLOW_SQUID ||
                        type == EntityType.PARROT || type == EntityType.FOX || type == EntityType.WOLF
                        || type == EntityType.MULE || type == EntityType.SKELETON_HORSE || type == EntityType.WANDERING_TRADER ||
                        type == EntityType.TRADER_LLAMA || type == EntityType.LLAMA || type == EntityType.SHEEP ||
                        type == EntityType.DOLPHIN) {

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                            ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + type.name() + ChatColor.GRAY + " | " + ChatColor.GREEN + "" + ChatColor.BOLD + healths + " / " + max));


                } else if (type == EntityType.PLAYER) {

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                            ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + entity.getName() + ChatColor.GRAY + " | " + ChatColor.GREEN + "" + ChatColor.BOLD + healths + " / " + max));
                } else {

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                            ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + entity.getCustomName() + ChatColor.GRAY + " | " + ChatColor.GREEN + "" + ChatColor.BOLD + healths + " / " + max));

                }
            }
            if (e.getEntity().getType() == data.getType()) {
                if (e.getEntity().getCustomName() != null) {
                    if (e.getEntity().getCustomName().contains("Lv.")) {
                        level = Integer.parseInt(e.getEntity().getCustomName().split("Lv.")[1].split(" :")[0]);
                    }
                }
                if (data.getLevel() == level) {
                    if (data.getEffectData().getType() == ActiveType.HIT) {
                        if (data.getEffectData().isRandom()) {
                            data.getEffectData().randomEffects((LivingEntity) e.getDamager());
                        } else
                            data.getEffectData().addEffect((LivingEntity) e.getDamager());

                    }
                    for (Skill skill : data.getSkillData().getSkills()) {
                        if (skill instanceof CircleMagma) {
                            skill.skillActive((LivingEntity) e.getEntity());
                        }
                        if (skill instanceof Revival) {
                            Revival revival = (Revival) skill;
                            LivingEntity entity = (LivingEntity) e.getEntity();
                            if (revival.nums.containsKey(entity.getUniqueId())) {
                                if (revival.nums.get(entity.getUniqueId()) <= 0) {
                                    Player player = (Player) e.getDamager();
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                            ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + entity.getCustomName() + ChatColor.GRAY + " | " + ChatColor.GREEN + "" + ChatColor.BOLD + 0 + " / " + (int)entity.getMaxHealth()));
                                    entity.damage(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                                    revival.nums.remove(entity.getUniqueId());
                                    return;
                                }
                            }
                            if (!revival.nums.containsKey(entity.getUniqueId())) {
                                revival.putNums(entity);
                            }
                            if (revival.isRevival(e.getEntity())) {
                                if (entity.getHealth() - e.getDamage() <= 0) {
                                    e.setDamage(0);
                                    revival.skillActive(entity);
                                }

                            }
                        }
                    }
                }
            }
        }

    }
}

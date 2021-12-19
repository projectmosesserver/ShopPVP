package info.ahaha.shoppvp.data;

import info.ahaha.shoppvp.Skill;
import org.bukkit.Color;

import java.util.ArrayList;
import java.util.List;

public class SkillData {

    private final String id;
    private final List<Skill>skills;
    private Color color;

    public static List<SkillData> data = new ArrayList<>();

    public SkillData(String id, List<Skill>skills) {
        this.id = id;
        this.skills = skills;
    }

    public SkillData(String id,List<Skill>skills,Color color) {
        this.id = id;
        this.skills = skills;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

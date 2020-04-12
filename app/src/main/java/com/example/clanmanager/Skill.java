package com.example.clanmanager;

public class Skill {
    String skillName;
    int skillRate;

    public Skill(){

    }

    public Skill(String skillName, int skillRate) {
        this.skillName = skillName;
        this.skillRate = skillRate;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getSkillRate() {
        return skillRate;
    }

    public void setSkillRate(int skillRate) {
        this.skillRate = skillRate;
    }
}

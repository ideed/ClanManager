package com.example.clanmanager;

import java.util.Date;

public class Member {
    public String memberName;
    private int overallSkill ;
    private Date memberDate;
    private String memberCOO;
    private double attendencePercentage;

    public Member(){

    }

    public Member(String memberName, Date memberDate, String memberCOO, double attendencePercentage, int overallSkill) {
        this.memberName = memberName;
        this.overallSkill = overallSkill;
        this.memberDate = memberDate;
        this.memberCOO = memberCOO;
        this.attendencePercentage = attendencePercentage;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getOverallSkill() {
        return overallSkill;
    }

    public void setOverallSkill(int overallSkill) {
        this.overallSkill = overallSkill;
    }

    public Date getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(Date memberDate) {
        this.memberDate = memberDate;
    }

    public String getMemberCOO() {
        return memberCOO;
    }

    public void setMemberCOO(String memberCOO) {
        this.memberCOO = memberCOO;
    }

    public double getAttendencePercentage() {
        return attendencePercentage;
    }

    public void setAttendencePercentage(double attendencePercentage) {
        this.attendencePercentage = attendencePercentage;
    }
}

package com.example.clanmanager;

public class Clan {
     String clanName;
     String owner;

    public Clan(){

    }

    public Clan(String clanName, String owner) {
        this.clanName = clanName;
        this.owner = owner;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

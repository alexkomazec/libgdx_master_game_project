package com.potatowars.hud.status;

import com.potatowars.sprites.LevelUpSystem;
import com.potatowars.sprites.commonParameters.Damage;
import com.potatowars.sprites.commonParameters.EnergyPoints;

public class StatsPacket{

    private EnergyPoints energyPoints;
    private Damage damage;
    private LevelUpSystem.LEVEL level;
    private int currentExpCapacity;
    private int exp;
    private int gold;

    public StatsPacket()
    {
        energyPoints = new EnergyPoints();
        energyPoints.setArmorPoints(0);
        energyPoints.setHealthPoints(0);
        energyPoints.setManaPoints(0);


        damage = new Damage();
        damage.setDamage(0);

        level               = LevelUpSystem.LEVEL.LEVEL1;
        currentExpCapacity  = 0;
        exp                 = 0;
        gold                = 0;
    }

    public EnergyPoints getEnergyPoints() {
        return energyPoints;
    }

    public void setEnergyPoints(EnergyPoints energyPoints) {
        this.energyPoints = energyPoints;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public LevelUpSystem.LEVEL getLevel() {
        return level;
    }

    public void setLevel(LevelUpSystem.LEVEL level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getCurrentExpCapacity() {
        return currentExpCapacity;
    }

    public void setCurrentExpCapacity(int currentExpCapacity) {
        this.currentExpCapacity = currentExpCapacity;
    }
}

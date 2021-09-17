package com.potatowars.sprites.commonParameters;

public class EnergyPoints {

    int healthPoints;
    int healthPointsCapacity;

    int manaPoints;
    int manaPointsCapacity;

    int armorPoints;

    public EnergyPoints(){}

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    public int getArmorPoints() {
        return armorPoints;
    }

    public void setArmorPoints(int armorPoints) {
        this.armorPoints = armorPoints;
    }

    public int getHealthPointsCapacity() {
        return healthPointsCapacity;
    }

    public void setHealthPointsCapacity(int healthPointsCapacity) {
        this.healthPointsCapacity = healthPointsCapacity;
    }

    public int getManaPointsCapacity() {
        return manaPointsCapacity;
    }

    public void setManaPointsCapacity(int manaPointsCapacity) {
        this.manaPointsCapacity = manaPointsCapacity;
    }

    public void increaseHealthCapacity(int healthBonus) {
        this.healthPointsCapacity += healthBonus;
    }

    public void increaseManaCapacity(int manaBonus) {
        this.manaPointsCapacity += manaBonus;
    }

    public void increaseArmor(int armorBonus) {
        this.armorPoints += armorBonus;
    }

}

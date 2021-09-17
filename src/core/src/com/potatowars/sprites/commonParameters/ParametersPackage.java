package com.potatowars.sprites.commonParameters;

import com.potatowars.sprites.LevelUpSystem;

public class ParametersPackage {

    private ShapeArea area;
    private ShapePosition position;
    private CommonStates states;
    private EnergyPoints energyPoints;
    private Damage damage;
    private Speed speed;

    LevelUpSystem.LEVEL level;
    private int exp;
    private int gold;

    public ParametersPackage(){
        //Physics paramters
        area = new ShapeArea(1,1);
        position = new ShapePosition(0,0);

        //Moving parameters
        states = new CommonStates();
        energyPoints = new EnergyPoints();

        //attack parameters
        damage = new Damage();
        speed  = new Speed();

        level  = LevelUpSystem.LEVEL.LEVEL1;
        exp    = 0;
    }


    //EnergyPoints getters and setters
    public EnergyPoints getEnergyPoints() {
        return energyPoints;
    }

    public void setEnergyPoints(EnergyPoints energyPoints) {
        this.energyPoints = energyPoints;
    }

    //CommonStates getters and setters
    public void setStates(CommonStates states) {
        this.states = states;
    }

    public CommonStates getStates() {
        return states;
    }

    //ShapeArea getters and setters
    public ShapeArea getArea() {
        return area;
    }

    public void setArea(ShapeArea area) {
        this.area = area;
    }

    //ShapePosition getters and setters
    public ShapePosition getPosition() {
        return position;
    }

    public void setPosition(ShapePosition position) {
        this.position = position;
    }

    //Damage getters and setters
    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    //Speed getters and setters
    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
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
}

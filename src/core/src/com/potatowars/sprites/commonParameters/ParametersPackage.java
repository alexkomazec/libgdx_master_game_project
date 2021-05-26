package com.potatowars.sprites.commonParameters;

public class ParametersPackage {

    ShapeArea area;
    ShapePosition position;
    CommonStates states;
    EnergyPoints energyPoints;
    Damage damage;
    Speed speed;

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
}

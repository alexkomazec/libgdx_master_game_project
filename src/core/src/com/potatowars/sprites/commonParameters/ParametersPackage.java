package com.potatowars.sprites.commonParameters;

public class ParametersPackage {

    ShapeArea area;
    ShapePosition position;
    CommonStates states;

    public ParametersPackage(float height, float weight, float x, float y){
        area = new ShapeArea(height,weight);
        position = new ShapePosition(x,y);

        states = new CommonStates();
    }

    public ParametersPackage(){
        area = new ShapeArea(1,1);
        position = new ShapePosition(0,0);

        states = new CommonStates();
    }


    //Getters
    public float getX() {
        return position.getX();
    }

    public float getY() {
        return position.getY();
    }

    public float getHeigt() {
        return area.getHeight();
    }

    public float getWeight() {
        return area.getWeight();
    }

    public CommonStates getStates() {
        return states;
    }

    //Setters
    public void setX(float x) {
        position.setX(x);
    }

    public void setY(float y) {
        position.setY(y);
    }

    public void setHeigt(float height) {
        area.setHeight(height);
    }

    public void setWeight(float width) {
        area.setWeight(width);
    }

    public void setStates(CommonStates states) {
        this.states = states;
    }


}

package com.potatowars.sprites.commonParameters;

public class ShapeArea {

    //2D shape area
    private float height, weight;

    public ShapeArea(float height, float weight){
        this.height = height;
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

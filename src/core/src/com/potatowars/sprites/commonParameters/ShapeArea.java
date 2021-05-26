package com.potatowars.sprites.commonParameters;

public class ShapeArea {

    //2D shape area
    private float height, width;

    public ShapeArea(float height, float width){
        this.height = height;
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}

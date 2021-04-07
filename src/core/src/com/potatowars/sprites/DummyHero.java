package com.potatowars.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class DummyHero extends Sprite {

    //public World world;
    public Body b2body;

    public DummyHero(){}

    public void setB2Body(Body b2body){
        this.b2body = b2body;
    }
}

package com.potatowars.box2d;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2dWorld {

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    private BodyDef bdef;
    private PolygonShape shape;
    private FixtureDef fdef;
    private Body body;

    // static variable single_instance of type Singleton
    // explanation: There is only one world in the game. So it should be prevented to create
    //              more than one object of the particular class (Only one world should exist)
    private static Box2dWorld single_instance = null;


    private Box2dWorld() {
        world = new World(new Vector2(0,-10), true);
        b2dr = new Box2DDebugRenderer();

        bdef = new BodyDef();
        shape = new PolygonShape();
        fdef = new FixtureDef();

    }

    // static method to create instance of Singleton class
    public static Box2dWorld getInstance()
    {
        if (single_instance == null)
            single_instance = new Box2dWorld();

        return single_instance;
    }

    public void Box2DDebugRendererRender (World world, Matrix4 projMatrix){
        b2dr.render(world,projMatrix);
    }

    public World getWorld() {
        return world;
    }

    public Box2DDebugRenderer getB2dr() {
        return b2dr;
    }
    
    public BodyDef getBdef() {
        return bdef;
    }

    public PolygonShape getShape() {
        return shape;
    }

    public FixtureDef getFdef() {
        return fdef;
    }

}

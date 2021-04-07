package com.potatowars.box2d;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.potatowars.config.GameConfig;

public class Box2dBodyBuilder {

    private Box2dBodyBuilder(){}

    public static Body createBox(World world, float x, float y, float width, float height, int bodyType,PolygonShape shape ){
        Body pbody;
        BodyDef def = new BodyDef(); //Body definition, propreties that the body will have

        if(GameConfig.iSTATIC == bodyType) {
            def.type = BodyDef.BodyType.StaticBody;
        }else if(GameConfig.iDYNAMIC == bodyType){
            def.type = BodyDef.BodyType.DynamicBody;
        }else if (GameConfig.iKINEMATIC == bodyType){
            def.type = BodyDef.BodyType.KinematicBody;
        }else{
            System.out.println("Irregular bodyTpe");
        }

        def.position.set(divideByPpm(x),divideByPpm(y));
        def.fixedRotation = true;
        pbody = world.createBody(def); // Put the body with body definition into the world

        //PolygonShape shape = new PolygonShape();
        shape.setAsBox(divideByPpm(width),divideByPpm(height));
        pbody.createFixture(shape, 1f); // Giving a shape to the box2d body
        shape.dispose();

        return pbody;
    }

    public static void createBox(World world, int bodyType, Shape shape ){
        Body pbody;
        BodyDef def = new BodyDef(); //Body definition, propreties that the body will have

        if(GameConfig.iSTATIC == bodyType) {
            def.type = BodyDef.BodyType.StaticBody;
        }else if(GameConfig.iDYNAMIC == bodyType){
            def.type = BodyDef.BodyType.DynamicBody;
        }else if (GameConfig.iKINEMATIC == bodyType){
            def.type = BodyDef.BodyType.KinematicBody;
        }else{
            System.out.println("Irregular bodyTpe");
        }

        pbody = world.createBody(def); // Put the body with body definition into the world

        pbody.createFixture(shape, 1f); // Giving a shape to the box2d body
        shape.dispose();
    }

    public static float divideByPpm(float number){
        return number/ GameConfig.PPM;
    }

    public static float multiplyByPpm(float number){
        return number*GameConfig.PPM;
    }

}

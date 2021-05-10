package com.potatowars.box2d;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.potatowars.config.GameConfig;
import com.potatowars.sprites.characters.BasicCharacter;
import com.potatowars.sprites.characters.EnemyCharacter;
import com.potatowars.sprites.characters.MainCharacter;
import com.potatowars.sprites.items.ExitGoal;
import com.potatowars.sprites.items.InteractiveTileObject;

public class Box2dBodyBuilder {

    private Box2dBodyBuilder(){}

    //Create body and its abilities for Non-living things. Also put the body into the world,
    //set the Living thing reference to point to this body in order to have a control of the body
    // (If the body is dynamic, moving stuff and collision detection will be applied, otherwise
    // only collision detection will be applied (for static bodies such as doors, consumable
    // items, etc))
    public static Body createBodyAndPutIntoTheWorld(World world, float x, float y, float width, float height, int bodyType, PolygonShape shape,
                                                    //Non-living things
                                                    InteractiveTileObject interactiveTileObject){

        Body pbody;
        BodyDef def = new BodyDef(); //Body definition, propreties that the body will have

        def.position.set(divideByPpm(x),divideByPpm(y));
        def.fixedRotation = true;

        setBodyType(bodyType,def);

        pbody = world.createBody(def); // Put the body with body definition into the world

        shape.setAsBox(divideByPpm(width),divideByPpm(height));

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;

        if(interactiveTileObject instanceof ExitGoal) {
            fdef.filter.categoryBits = GameConfig.EXIT_DOOR_BIT;//Owner bit

            interactiveTileObject.setFixture(pbody.createFixture(fdef));// Giving a shape to the box2d body
            //interactiveTileObject.setFixture(pbody.createFixture(shape,0f));
        }
        else{
            //TBD - Will be populated when more objects are created
            pbody.createFixture(shape, 1f);// Giving a shape to the box2d body
        }

        shape.dispose();

        return pbody;
    }


    //Create body and its abilities for Living things. Also put the body into the world,
    //set the Living thing reference to point to this body in order to have a control of the body
    // (If the body is dynamic, moving stuff and collision detection will be applied, otherwise
    // only collision detection will be applied (for static bodies such as doors, consumable
    // items, etc))
    public static Body createBodyAndPutIntoTheWorld(World world, float x, float y, float width, float height, int bodyType, PolygonShape shape,
                                                    //Living thing
                                                    BasicCharacter hero){

        Body pbody;
        BodyDef def = new BodyDef(); //Body definition, propreties that the body will have

        def.position.set(divideByPpm(x),divideByPpm(y));
        //def.position.set(x,y);
        def.fixedRotation = true;

        setBodyType(bodyType,def);

        pbody = world.createBody(def); // Put the body with body definition into the world

        shape.setAsBox(divideByPpm(width),divideByPpm(height));

        setParamForLivingThings(bodyType,x,y,height,width,hero,def,pbody,shape);
        shape.dispose();

        return pbody;
    }

    //Generic function used just to create a body and put it in the world. This is used so far
    //for objects that we do not need so far
    public static void createBodyAndPutIntoTheWorld(World world, int bodyType, Shape shape ){
        Body pbody;
        BodyDef def = new BodyDef(); //Body definition, propreties that the body will have

        setBodyType(bodyType,def);

        pbody = world.createBody(def); // Put the body with body definition into the world

        pbody.createFixture(shape, 1f); // Giving a shape to the box2d body
        shape.dispose();
    }

    //Set the body type according to the bodyType that is passed
    private static void setBodyType(int bodyType, BodyDef def){
        if(GameConfig.iSTATIC == bodyType) {
            def.type = BodyDef.BodyType.StaticBody;
        }else if(GameConfig.iDYNAMIC == bodyType){
            def.type = BodyDef.BodyType.DynamicBody;
        }else if (GameConfig.iKINEMATIC == bodyType){
            def.type = BodyDef.BodyType.KinematicBody;
        }else{
            System.out.println("Irregular bodyTpe");
        }
    }

    //Set some parameters for some living and non-living things according to body type
    private static void setParamForLivingThings(int bodyType,
                                                float x,
                                                float y,
                                                float height,
                                                float width,
                                                BasicCharacter hero,
                                                BodyDef def,
                                                Body pbody,
                                                Shape shape){

        if(GameConfig.iSTATIC == bodyType) {
            //Do nothing
        }else if(GameConfig.iDYNAMIC == bodyType){
            hero.setX(x);
            hero.setY(y);
            hero.setHeigt(height);
            hero.setWeight(width);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;

            if(hero instanceof MainCharacter) {
                fdef.filter.categoryBits = GameConfig.HERO_BIT;//Owner bit
                fdef.filter.maskBits = GameConfig.GROUND_BIT | GameConfig.EXIT_DOOR_BIT;//Objects that will colied with

                hero.setFixture(pbody.createFixture(fdef));// Giving a shape to the box2d body
                //hero.setFixture(pbody.createFixture(shape, 1f));// Giving a shape to the box2d body
            }
            else if(hero instanceof EnemyCharacter){
                hero.setFixture(pbody.createFixture(shape, 1f));// Giving a shape to the box2d body
            }else{
                //do nothing
            }

        }else if (GameConfig.iKINEMATIC == bodyType){
            def.type = BodyDef.BodyType.KinematicBody;
        }else{
            System.out.println("Irregular bodyTpe");
        }
    }

    public static float divideByPpm(float number){
        return number/ GameConfig.PPM;
    }

}

package com.potatowars.sprites.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.potatowars.sprites.characters.interfaces.CharacterMovement;
import com.potatowars.sprites.commonParameters.CommonStates;
import com.potatowars.sprites.commonParameters.ParametersPackage;

import static com.potatowars.sprites.Animation.TextureAtlasCommonContainer.ALL_HERO_FRAMES;
import static com.potatowars.sprites.Animation.TextureAtlasCommonContainer.heroMoves;

public class BasicCharacter extends Sprite implements CharacterMovement {

    //Texture and Image stuff

    //Texture Regions and Atlas
    protected TextureRegion characterStand;
    protected float stateTimer;
    protected boolean runningRight;

    //Animaiton
    protected Animation characterRun;

    //Box2d World stuff
    public Body b2body;

    protected Fixture fixture;

    //BaseCharacter abilities
    protected ParametersPackage basicCharacter;

    //States
    protected CommonStates.State currentState;
    protected CommonStates.State previosState;

    protected BasicCharacter(){
        super(heroMoves.findRegion(ALL_HERO_FRAMES));
    }

    public Fixture getFixture(){ return fixture;}

    public void setFixture(Fixture fixture){
        this.fixture = fixture;
    }

    //Position getters
    public float getX() {
        return basicCharacter.getX();
    }

    public float getY() {
        return basicCharacter.getY();
    }

    //Area getters
    public float getHeight() {
        return basicCharacter.getHeigt();
    }

    public float getWeight() {
        return basicCharacter.getWeight();
    }

    //Position Setters
    public void setX(int x) {
        basicCharacter.setX(x);
    }

    public void setY(int y) {
        basicCharacter.setY(y);
    }

    //Area Setters
    public void setHeigt(float height) {
        basicCharacter.setHeigt(height);
    }

    public void setWeight(float width) {
        basicCharacter.setWeight(width);
    }

    //Box2d Body stuff
    public void setB2Body(Body b2body){
        this.b2body = b2body;
    }

    public float getB2Body_positionX(){
        return b2body.getPosition().x;
    }

    public float getB2Body_positionY(){
        return b2body.getPosition().y;
    }

    CommonStates.State getCurrentState(){
        return basicCharacter.getStates().getCurrentState();
    }

    //Interface methods
    @Override
    public void update(float dt) {

    }

    @Override
    public TextureRegion getFrame(float dt) {
        return null;
    }

    @Override
    public CommonStates.State getState() {
        return null;
    }
}

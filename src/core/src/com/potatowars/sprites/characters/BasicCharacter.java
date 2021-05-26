package com.potatowars.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.potatowars.common.Pair;
import com.potatowars.sprites.characters.interfaces.CharacterMovement;
import com.potatowars.sprites.commonParameters.CommonStates;
import com.potatowars.sprites.commonParameters.ParametersPackage;
import com.potatowars.util.ViewportUtils;

public class BasicCharacter extends Sprite implements CharacterMovement {

    private static final Logger log = new Logger(ViewportUtils.class.getName(), Logger.DEBUG);

    //There will be set all movements for the current character
    protected Array< Pair<Animation<TextureRegion>,CommonStates.State> > movements;
    //Additional parameters that is used for animation and movement direction
    protected float stateTimer;
    protected boolean runningRight;
    protected CommonStates.State currentState;
    protected CommonStates.State previosState;

    //Box2d World stuff, each character has a body, and fixture in Box2d world
    public Body b2body;
    protected Fixture fixture;

    //BaseCharacter abilities, each charater has measures, speed, damage, etc.
    protected ParametersPackage parametersPackage;

    protected BasicCharacter(){
        super();
        movements = new Array<>();
    }

    protected TextureRegion findSpecifiedPair(CommonStates.State movementState){

        int iterator;
        boolean found = false;

        for( iterator = 0; iterator < movements.size; iterator++){
            if(movementState == movements.get(iterator).getT2()){
                found = true;
                break;
            } else{
                //Go to the next cycle
            }
        }

        if(found){
            return movements.get(iterator).getT1().getKeyFrame(stateTimer,true);
        }else{
            log.debug("Development Error:" + movementState.toString() + " has not found");
            Gdx.app.exit();
            return null;
        }
    }

    //Fixture getters and setters
    public Fixture getFixture(){ return fixture;}

    public void setFixture(Fixture fixture){
        this.fixture = fixture;
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

    //ParametersPackage getters and setters
    public ParametersPackage getParametersPackage() {
        return parametersPackage;
    }

    public void setParametersPackage(ParametersPackage parametersPackage) {
        this.parametersPackage = parametersPackage;
    }

    public Array<Pair<Animation<TextureRegion>, CommonStates.State>> getMovements() {
        return movements;
    }

    public void setMovements(Array<Pair<Animation<TextureRegion>, CommonStates.State>> movements) {
        this.movements = movements;
    }

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

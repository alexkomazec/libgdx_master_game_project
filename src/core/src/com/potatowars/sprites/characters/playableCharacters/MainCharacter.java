package com.potatowars.sprites.characters.playableCharacters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.potatowars.PotatoWars;
import com.potatowars.box2d.Box2dBodyBuilder;
import com.potatowars.config.GameConfig;
import com.potatowars.sprites.characters.BasicCharacter;
import com.potatowars.sprites.commonParameters.CommonStates;
import com.potatowars.sprites.commonParameters.ParametersPackage;

import static com.potatowars.sprites.commonParameters.CommonStates.State.ATTACKING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.BEINGHURT;
import static com.potatowars.sprites.commonParameters.CommonStates.State.DYING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.FALLING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.JUMPING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.RUNNING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.STANDING;

public class MainCharacter extends BasicCharacter {

    protected Array<String> movementTypes;

    public MainCharacter(PotatoWars game){
        super();

        parametersPackage = new ParametersPackage();

        //Set all the character characteristics
        setCharacterStats(parametersPackage);

        //Set all the character movement types
        movementTypes = new Array<String>();
        setAllMovementTypes();

        //Set the sprite bounds
        setBounds(0,0,32/ GameConfig.PPM,32/ GameConfig.PPM);
    }

    protected void setAllMovementTypes(){

        movementTypes.add("stand");
        movementTypes.add("run");
        movementTypes.add("jump");
        movementTypes.add("hurt");
        movementTypes.add("dead");
        movementTypes.add("fall");
    }


    protected void setCharacterStats(ParametersPackage parametersPackage){

        if(null != parametersPackage) {
            parametersPackage.getDamage().setDamage(GameConfig.HERO_DPS);
            parametersPackage.getEnergyPoints().setHealthPoints(GameConfig.HERO_HP);
            parametersPackage.getEnergyPoints().setManaPoints(GameConfig.HERO_MANA);
            parametersPackage.getEnergyPoints().setArmorPoints(GameConfig.HERO_ARMOR);
            parametersPackage.getSpeed().setAtackSpeed(GameConfig.HERO_ATTACK_SPEED);
            parametersPackage.getSpeed().setAtackSpeed(GameConfig.HERO_MOVEMENT_SPEED);
        }
        else{
            System.out.println("parametersPackage is null");
        }
    }

    //Update the position of the character
    public void update(float dt)
    {
        setPosition(
                getB2Body_positionX() - Box2dBodyBuilder.divideByPpm(getWidth()),
                getB2Body_positionY() - Box2dBodyBuilder.divideByPpm(getHeight())
        );
        setRegion(getFrame(dt));
    }

    @Override
    public void setB2Body(Body b2body) {
        super.setB2Body(b2body);
        fixture.setUserData("hero");
    }

    //Get the current frame
    public TextureRegion getFrame(float dt)
    {
        //Set current State
        currentState = getState();

        setCurrentState(currentState);

        TextureRegion region;

        switch (getCurrentState())
        {
            case JUMPING:
                region = (TextureRegion) findSpecifiedPair(JUMPING);
                break;
            case RUNNING:
                region = (TextureRegion) findSpecifiedPair(RUNNING);
                break;
            case FALLING:
                region = (TextureRegion) findSpecifiedPair(FALLING);
                break;
            case STANDING:
                region = (TextureRegion) findSpecifiedPair(STANDING);
                break;
            case DYING:
                region = (TextureRegion) findSpecifiedPair(DYING);
                break;
            case BEINGHURT:
                region = (TextureRegion) findSpecifiedPair(BEINGHURT);
                break;
            case ATTACKING:
                region = (TextureRegion) findSpecifiedPair(ATTACKING);
                break;
            default:
                region = (TextureRegion) findSpecifiedPair(STANDING);
                break;
        }

        if((b2body.getLinearVelocity().x <0 || !runningRight) &&  !region.isFlipX())
        {
            //Running Left
            region.flip(true,false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x >0|| runningRight) && region.isFlipX())
        {
            //Running Right
            region.flip(true,false);
            runningRight = true;
        }

        stateTimer = currentState == previosState ? stateTimer + dt:0;
        previosState = currentState;
        return region;
    }


    //Calculate the current state
    public CommonStates.State getState()
    {
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y <0 && previosState == JUMPING))
        {
            return JUMPING;
        }
        else if(b2body.getLinearVelocity().y < 0)
        {
            return FALLING;
        }
        else if(b2body.getLinearVelocity().x != 0)
        {
            return RUNNING;
        }
        else {
            return STANDING;
        }
    }

    //Binding Wrapper methods
    private void setCurrentState(CommonStates.State state){
        parametersPackage.getStates().setCurrentState(state);
    }

    private CommonStates.State getCurrentState(){
        return parametersPackage.getStates().getCurrentState();
    }

    public Array<String> getMovementTypes() {
        return movementTypes;
    }

}

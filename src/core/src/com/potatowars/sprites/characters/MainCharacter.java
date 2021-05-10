package com.potatowars.sprites.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.potatowars.PotatoWars;
import com.potatowars.box2d.Box2dBodyBuilder;
import com.potatowars.config.GameConfig;
import com.potatowars.sprites.commonParameters.CommonStates;
import com.potatowars.sprites.commonParameters.ParametersPackage;

import static com.potatowars.sprites.Animation.TextureAtlasCommonContainer.getRunningAnimation;
import static com.potatowars.sprites.Animation.TextureAtlasCommonContainer.getTextureRegion;

public class MainCharacter extends BasicCharacter {

    protected TextureRegion characterJump;

    public MainCharacter(PotatoWars game){
        super();

        basicCharacter = new ParametersPackage();

        characterRun = getRunningAnimation();

        characterStand = getTextureRegion(CommonStates.State.STANDING);
        //heroJump    =   new TextureRegion(getTexture(),64,352,32,32);
        characterJump =   getTextureRegion(CommonStates.State.JUMPING);

        setBounds(0,0,32/ GameConfig.PPM,32/ GameConfig.PPM);
        setRegion(characterJump);
    }

    //Update the position of the character
    public void update(float dt)
    {
        setPosition(
                getB2Body_positionX() - Box2dBodyBuilder.divideByPpm(getWeight()),
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
                region = characterJump;
                break;
            case RUNNING:
                region = (TextureRegion) characterRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = characterStand;
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


    //Get the current state
    public CommonStates.State getState()
    {
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y <0 && previosState == CommonStates.State.JUMPING))
        {
            return CommonStates.State.JUMPING;
        }
        else if(b2body.getLinearVelocity().y < 0)
        {
            return CommonStates.State.FALLING;
        }
        else if(b2body.getLinearVelocity().x != 0)
        {
            return CommonStates.State.RUNNING;
        }
        else {
            return CommonStates.State.STANDING;
        }
    }

    //Binding Wrapper methods
    void setCurrentState(CommonStates.State state){
        basicCharacter.getStates().setCurrentState(state);
    }

}

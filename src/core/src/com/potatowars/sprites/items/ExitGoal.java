package com.potatowars.sprites.items;

import com.badlogic.gdx.physics.box2d.Body;
import com.potatowars.config.GameConfig;

public class ExitGoal extends InteractiveTileObject{

    public ExitGoal(){

    }

    @Override
    public void setB2Body(Body b2body) {
        super.setB2Body(b2body);
        setCategoryFilter(GameConfig.EXIT_DOOR_BIT);
        fixture.setUserData("exit_door");
    }
}

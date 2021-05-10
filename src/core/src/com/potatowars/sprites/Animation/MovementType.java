package com.potatowars.sprites.Animation;

import com.potatowars.sprites.commonParameters.CommonStates;

public class MovementType {

    private CommonStates.State movementName;

    int x,y,weight,height;

    public MovementType(){
        this.movementName = CommonStates.State.STANDING;
        this.x = 0;
        this.y = 0;
        this.weight = 0;
        this.height = 0;
    }

    public MovementType(CommonStates.State movementName,int x,int y,int weight, int height){
        this.movementName = movementName;
        this.x = 0;
        this.y = 0;
        this.weight = 0;
        this.height = 0;
    }

    public CommonStates.State getMovementName() {
        return movementName;
    }
}

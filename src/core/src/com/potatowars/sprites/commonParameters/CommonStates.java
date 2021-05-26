package com.potatowars.sprites.commonParameters;

public class CommonStates {

    //State stuff
    public enum State{STANDING,FALLING,JUMPING,RUNNING,DYING,BEINGHURT,ATTACKING};

    private State currentState;
    private State previosState;


    public CommonStates(){
        currentState = State.STANDING;
        previosState = State.STANDING;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void setPreviosState(State previosState) {
        this.previosState = previosState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public State getPreviosState() {
        return previosState;
    }

}

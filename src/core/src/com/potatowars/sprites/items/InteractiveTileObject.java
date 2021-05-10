package com.potatowars.sprites.items;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

public class InteractiveTileObject {

    protected Body b2body;
    protected Fixture fixture;

    public InteractiveTileObject(){

    }

    //Box2d Body stuff
    public void setB2Body(Body b2body){
        this.b2body = b2body;
    }

    public void setCategoryFilter(short filterBit)
    {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public void setFixture(Fixture fixture){
        this.fixture = fixture;
    }

}

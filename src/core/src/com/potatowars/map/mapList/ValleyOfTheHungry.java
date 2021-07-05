package com.potatowars.map.mapList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;

public class ValleyOfTheHungry extends Map{

    /*Dangerous area - Stalagmites*/
    protected final static String STATIC_STALAGMITES = "static_Stalagmites";

    public ValleyOfTheHungry(TiledMap tiledMap, MainCharacter player ){
        super("ValleyOfTheHungry",tiledMap,player);
    }
}

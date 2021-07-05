package com.potatowars.map.mapList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;

public class SnowWave extends Map{

    public SnowWave(TiledMap tiledMap, MainCharacter player) {
        super("SnowWave",tiledMap,player);
    }
}

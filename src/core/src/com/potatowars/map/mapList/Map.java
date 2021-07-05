package com.potatowars.map.mapList;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;

import sun.applet.Main;

public class Map {

    TiledMap tiledMap = null;

    protected String mapName = " ";

    MainCharacter player;

    /*Exit door*/
    protected final static String STATIC_DOOR = "static_door";
    protected final static String DOOR = "door";

    /*Player*/
    protected final static String DYNAMIC_PLAYER = "dynamic_player";

    /*Player*/
    protected final static String DYNAMIC_ENEMY1 = "dynamic_Enemy1";

    /*Platforms*/
    protected final static String STATIC_PLATFORM = "static_Platform";

    protected MapLayer door_tiledLayer = null;
    protected MapLayer door_objectLayer = null;

    protected MapLayer player_objectLayer = null;
    protected MapLayer enemy1_objectLayer = null;

    protected MapLayer platform_objectLayer= null;


    public Map(String mapName, TiledMap tiledMap, MainCharacter player){
        this.mapName = mapName;
        this.tiledMap = tiledMap;
        this.player   = player;

        /*Get layers that are needed*/
        door_tiledLayer = this.tiledMap.getLayers().get(STATIC_DOOR);
        door_objectLayer = this.tiledMap.getLayers().get(DOOR);

        player_objectLayer = this.tiledMap.getLayers().get(DYNAMIC_PLAYER);
        enemy1_objectLayer = this.tiledMap.getLayers().get(DYNAMIC_ENEMY1);

        platform_objectLayer = this.tiledMap.getLayers().get(STATIC_PLATFORM);
    }
}

package com.potatowars.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.potatowars.PotatoWars;
import com.potatowars.config.GameConfig;
import com.potatowars.map.mapList.Map;
import com.potatowars.map.tilemap_handler.TilemapHandler;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.items.InteractiveTileObject;

import static com.potatowars.map.MapFactory.MapName.LEVEL0;
import static com.potatowars.map.MapFactory.MapName.LEVEL1;
import static com.potatowars.map.MapFactory.MapName.LEVEL2;
import static com.potatowars.map.MapFactory.MapName.LEVEL3;
import static com.potatowars.map.MapFactory.MapName.LEVEL4;
import static com.potatowars.map.MapFactory.MapName.LEVEL5;

public class MapManager implements MapLoadingObserver{

    private static final String CLASS_NAME = MapManager.class.getSimpleName();
    private static MapFactory.MapName currnetMap = LEVEL0;

    private static MapManager _instance = null;

    public static MapManager getInstance() {
        if (_instance == null) {
            _instance = new MapManager();
        }

        return _instance;
    }

    private MapManager(){}

    //onNotify is supposed to be used to notify MapManager that new map should be loaded
    @Override
    public void onNotify(PotatoWars game, MainCharacter mainCharacter, Array<InteractiveTileObject> interactiveTiledObjects) {

        MapFactory.MapName mapName = getNextMap();
        Map map = MapFactory.getMap(mapName,mainCharacter,interactiveTiledObjects);

        if( map == null ){
            Gdx.app.debug(CLASS_NAME, "Map does not exist!  ");
        }else{
            //do nothing
        }
    }

    public static Map getCurrentMap(){
        return MapFactory.getCurrentMap(currnetMap);
    }

    private MapFactory.MapName getNextMap(){
            switch(currnetMap) {

                case LEVEL0:
                case LEVEL5:
                    currnetMap = LEVEL1;
                    break;
                case LEVEL1:
                    currnetMap = LEVEL2;
                    break;
                case LEVEL2:
                    currnetMap = LEVEL3;
                    break;
                case LEVEL3:
                    currnetMap = LEVEL4;
                    break;
                case LEVEL4:
                    currnetMap = LEVEL5;
                    break;
                default:
                    currnetMap = LEVEL0;
                    break;
            }
            return currnetMap;
    }

}
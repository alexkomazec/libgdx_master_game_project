package com.potatowars.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.potatowars.PotatoWars;
import com.potatowars.config.GameConfig;
import com.potatowars.map.mapList.EternalSummer;
import com.potatowars.map.mapList.Kepler51b;
import com.potatowars.map.mapList.Map;
import com.potatowars.map.mapList.SnowWave;
import com.potatowars.map.mapList.TheGreatSea;
import com.potatowars.map.mapList.ValleyOfTheHungry;
import com.potatowars.map.tilemap_handler.TilemapHandler;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.items.InteractiveTileObject;

import java.util.Hashtable;

import static com.potatowars.map.MapFactory.MapName.LEVEL1;
import static com.potatowars.map.MapFactory.MapName.LEVEL2;
import static com.potatowars.map.MapFactory.MapName.LEVEL3;
import static com.potatowars.map.MapFactory.MapName.LEVEL4;
import static com.potatowars.map.MapFactory.MapName.LEVEL5;

public class MapFactory {

    private static final String CLASS_NAME = MapFactory.class.getSimpleName();

    //Map list
    private static Hashtable<MapName, Map> mapList = new Hashtable<MapName, Map>();

    public enum MapName{
        LEVEL0,
        LEVEL1,
        LEVEL2,
        LEVEL3,
        LEVEL4,
        LEVEL5,
    }

    public static Map getCurrentMap(MapName mapName){
        return getMap( mapName,null, null);
    }

    public static Map getMap(MapName mapType,MainCharacter mainCharacter, Array<InteractiveTileObject> interactiveTiledObjects){
        Map map = null;

        if(null == mainCharacter || null == interactiveTiledObjects){
            Gdx.app.debug(CLASS_NAME, "null == mainCharacter || null == interactiveTiledObjects");
        }

        switch(mapType){
            case LEVEL1:
                map = mapList.get(LEVEL1);
                if( map == null ){
                    Gdx.app.debug(CLASS_NAME, "map == null LEVEL1");
                    map = processMap(LEVEL1,mainCharacter,interactiveTiledObjects);

                }
                break;
            case LEVEL2:
                map = mapList.get(LEVEL2);
                if( map == null ){
                    Gdx.app.debug(CLASS_NAME, "map == null LEVEL2");
                    map = processMap(LEVEL2,mainCharacter,interactiveTiledObjects);

                }

                break;
            case LEVEL3:
                map = mapList.get(LEVEL3);
                if( map == null ){
                    Gdx.app.debug(CLASS_NAME, "map == null LEVEL3");
                    map = processMap(LEVEL3,mainCharacter,interactiveTiledObjects);

                }
                break;
            case LEVEL4:
                map = mapList.get(LEVEL4);
                if( map == null ){
                    Gdx.app.debug(CLASS_NAME, "map == null LEVEL4");
                    map = processMap(LEVEL4,mainCharacter,interactiveTiledObjects);

                }
                break;
            case LEVEL5:
                map = mapList.get(LEVEL5);
                if( map == null ){
                    Gdx.app.debug(CLASS_NAME, "map == null LEVEL5");
                    map = processMap(LEVEL5,mainCharacter,interactiveTiledObjects);

                }
                break;
            default:
                Gdx.app.debug(CLASS_NAME, "No specified map" + mapType);
                break;
        }
        return map;
    }

    private static Map processMap(MapName mapType,
                                  MainCharacter mainCharacter,
                                  Array<InteractiveTileObject> interactiveTiledObjects
                                  )
    {
        Map map;
        String mapName;
        TiledMap tiledMap;

        switch(mapType) {

            case LEVEL1:
                mapName = GameConfig.LEVEL1;

                tiledMap = TilemapHandler.getTiledMap(mapName);

                if(tiledMap == null){
                    Gdx.app.debug(CLASS_NAME, "tiledMap is null");
                }

                map = new ValleyOfTheHungry(tiledMap,mainCharacter);
                mapList.put(LEVEL1, map);

                break;
            case LEVEL2:
                mapName = GameConfig.LEVEL2;

                tiledMap = TilemapHandler.getTiledMap(mapName);

                if(tiledMap == null){
                    Gdx.app.debug(CLASS_NAME, "tiledMap is null");
                }

                map = new EternalSummer(tiledMap,mainCharacter);
                mapList.put(LEVEL2, map);
                break;
            case LEVEL3:
                mapName = GameConfig.LEVEL3;

                tiledMap = TilemapHandler.getTiledMap(mapName);

                if(tiledMap == null){
                    Gdx.app.debug(CLASS_NAME, "tiledMap is null");
                }

                map = new SnowWave(tiledMap,mainCharacter);
                mapList.put(LEVEL3, map);
                break;
            case LEVEL4:
                mapName = GameConfig.LEVEL4;

                tiledMap = TilemapHandler.getTiledMap(mapName);

                if(tiledMap == null){
                    Gdx.app.debug(CLASS_NAME, "tiledMap is null");
                }

                map = new TheGreatSea(tiledMap,mainCharacter);
                mapList.put(LEVEL4, map);
                break;
            case LEVEL5:
                mapName = GameConfig.LEVEL5;

                tiledMap = TilemapHandler.getTiledMap(mapName);

                if(tiledMap == null){
                    Gdx.app.debug(CLASS_NAME, "tiledMap is null");
                }

                map = new Kepler51b(tiledMap,mainCharacter);
                mapList.put(LEVEL5, map);
                break;
            default:
                mapName = GameConfig.LEVEL1;

                tiledMap = TilemapHandler.getTiledMap(mapName);

                if(tiledMap == null){
                    Gdx.app.debug(CLASS_NAME, "tiledMap is null");
                }

                map = new ValleyOfTheHungry(tiledMap,mainCharacter);
                mapList.put(LEVEL1, map);
                Gdx.app.debug(CLASS_NAME,"incorrect map");
        }

        //Parse .tmx map, fill the world with bodies, and also set living and non-living object
        //to point to particular bodies in order to have a control of all the bodies.
        TilemapHandler.createMap(PotatoWars.getInstance().getBox2dWorld().getWorld(), GameConfig.LEVEL1,
                //Living and Non-Living object
                tiledMap,
                mainCharacter,
                interactiveTiledObjects);

        return map;
    }

}
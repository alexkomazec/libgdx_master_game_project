package com.potatowars.map.tilemap_handler;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapGroupLayer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.potatowars.box2d.Box2dBodyBuilder;
import com.potatowars.config.GameConfig;
import com.potatowars.map.mapList.Map;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.items.ExitGoal;
import com.potatowars.sprites.items.InteractiveTileObject;

public class TilemapHandler implements Disposable {

    //Load the map into the code
    private static TmxMapLoader mapLoader;

    //This is what renders our map to the screen
    private static OrthogonalTiledMapRenderer rendererTiledMap;

    private TilemapHandler() { }

    public static TiledMap getTiledMap(String fileName){

        mapLoader = new TmxMapLoader();

        //Loading level file from assets
        TiledMap map = mapLoader.load(fileName);

        return map;
    }


    public static void createMap(World world, String fileName,
                                 //Living and Non-Living objects
                                 TiledMap tiledMap,
                                 MainCharacter mainCharacter,
                                 Array<InteractiveTileObject> interactiveTiledObjects)
    {
        parseTiledObjectLayer(world,fileName,
                //Living and Non-Living objects
                tiledMap,
                mainCharacter,
                interactiveTiledObjects);
    }

    private static void createBodyForAllTheObjectsInTheLayer(MapObjects objects, World world, int bodyType, MapLayer mapLayer,
                                                             //Living and Non-Living objects
                                                             MainCharacter mainCharacter,
                                                             Array<InteractiveTileObject> interactiveTiledObjects){

        for(MapObject object : objects) {

            //Go through all the objects in the particular layer
            if(object instanceof PolylineMapObject) {
                //PolyLineMapObject is an irregular shape which beggining and and are not at the same
                //position
                Shape shape = createPolyline((PolylineMapObject) object);

                Box2dBodyBuilder.createBodyAndPutIntoTheWorld(world,bodyType,shape);
            } else {
                //Then object is a rectangular object.
                //Note: All others object shapes (PolygonMapObject,TextureMapObject,EclipseMapObject
                // CircleMapObject) are not supported in this project.
                Rectangle rect = ((RectangleMapObject)object).getRectangle();

                PolygonShape shape = new PolygonShape();

                //Force move scenario, explanaiton: Memory for dummyPlayer has been allocated (Empty constructor, because at this time, x,y,width,height
                // parameters are missing) in Loading Screen. So the first time when there are all needed information for initilazing dummyPlayer is here.
                // It is immposible to pass dummyPlayer to  parseTiledObjectLayer(This method) because Java always passes by value, so allocating new memmory
                // by dummyPlayer referece (dummyPlayer reference from parseTiledObjectLayer is just a copy of the "real dummyPlayer" reference) will allocate new
                // piece of memmory that dummyPlayer reference (coppied one) will be pointing to, so passed, original dummyPlayer reference will be untouched
                //TODO: REF1: Think about a better solution. This solution works well but portability is poor.

                if(GameConfig.iDYNAMIC == bodyType) {/*Dynamic bodies*/
                    if (null != object.getName()) {
                        //If dynamic object has a name
                        if (object.getName().contains(GameConfig.PLAYER_NAME)) {
                            mainCharacter.setB2Body(Box2dBodyBuilder.createBodyAndPutIntoTheWorld(
                                    world,
                                    rect.getX() + rect.getWidth() / 2,
                                    rect.getY() + rect.getHeight() / 2,
                                    rect.getWidth(),
                                    rect.getHeight(),
                                    bodyType,
                                    shape,
                                    mainCharacter));
                        } else {
                            //TODO: Put all other dynamic bodies likes enemies
                        }
                    }else{
                        // If dynamic object has not a name, however put it in the world
                        Box2dBodyBuilder.createBodyAndPutIntoTheWorld(
                                world,
                                rect.getX() + rect.getWidth() / 2,
                                rect.getY() + rect.getHeight() / 2,
                                rect.getWidth(),
                                rect.getHeight(),
                                bodyType,
                                shape,
                                mainCharacter);
                    }
                }/*Static bodies*/
                else if(GameConfig.iSTATIC == bodyType){
                    if (null != object.getName()) {
                        //If the static object has a name
                        if(object.getName().contains(GameConfig.EXIT_DOOR_NAME)) {
                            ExitGoal exitDoor = new ExitGoal();
                            exitDoor.setB2Body(Box2dBodyBuilder.createBodyAndPutIntoTheWorld(
                                    world,
                                    rect.getX() + rect.getWidth() / 2,
                                    rect.getY() + rect.getHeight() / 2,
                                    rect.getWidth() / 2,
                                    rect.getHeight() / 2,
                                    bodyType,
                                    shape,
                                    exitDoor
                            ));
                            interactiveTiledObjects.add(exitDoor);
                        }else{
                            //TODO: More static objects with names should be put there
                        }
                    }else{
                        //if the static object has not a name
                        Box2dBodyBuilder.createBodyAndPutIntoTheWorld(
                                world,
                                rect.getX() + rect.getWidth() / 2,
                                rect.getY() + rect.getHeight() / 2,
                                rect.getWidth() / 2,
                                rect.getHeight() / 2,
                                bodyType,
                                shape,
                                new InteractiveTileObject()
                        );
                    }
                }
                else {
                    //If the object has not a name
                    Box2dBodyBuilder.createBodyAndPutIntoTheWorld(
                            world,
                            rect.getX() + rect.getWidth() / 2,
                            rect.getY() + rect.getHeight() / 2,
                            rect.getWidth() / 2,
                            rect.getHeight() / 2,
                            bodyType,
                            shape,
                            mainCharacter
                    );
                }
            }
        }
    }

    private static int decideBodyType(MapObjects objects, MapLayer mapLayer){

        int bodyType = GameConfig.iSTATIC; //Static is default value

        for(MapObject object : objects) {

            if (mapLayer.getName().contains(GameConfig.strSTATIC)) {
                bodyType = GameConfig.iSTATIC;
            } else if (mapLayer.getName().contains(GameConfig.strDYNAMIC)) {
                bodyType = GameConfig.iDYNAMIC;
            } else if (mapLayer.getName().contains(GameConfig.strKINEMATIC)) {
                bodyType = GameConfig.iKINEMATIC;
            } else {
                bodyType = GameConfig.iNOT_BODY;
            }
        }
        return bodyType;
    }

    private static void RecursiveSeraching(MapLayer mapLayer,World world,
                                           //Living and Non-Living objects
                                           MainCharacter mainCharacter,
                                           Array<InteractiveTileObject> interactiveTiledObjects)
    {
        //MapGroupLayer represents a folder of layers, so if mapLayer is instances of it,
        //go into the folder and do recursive searching.
        if(mapLayer instanceof  MapGroupLayer) {

            MapGroupLayer mapGroupLayer = (MapGroupLayer)mapLayer;

            for(int i = 0; i < mapGroupLayer.getLayers().size();i++){
                RecursiveSeraching(mapGroupLayer.getLayers().get(i),world,
                        //Living and Non-Living objects
                        mainCharacter,
                        interactiveTiledObjects);
            }
        }else{
            //It means that the current Maplayer is a layer, not a folder of layers
            MapObjects objects = mapLayer.getObjects();
            int bodyType = decideBodyType(objects, mapLayer);

            if( GameConfig.iNOT_BODY != bodyType){
                //Create a body for each of the objcets in the body
                createBodyForAllTheObjectsInTheLayer(objects,world,bodyType,mapLayer,
                        //Living and Non-Living objects
                        mainCharacter,
                        interactiveTiledObjects);
            }
        }

    }

    public static void parseTiledObjectLayer(World world, String fileName,
                                             //Living and Non-Living objects
                                             TiledMap tiledMap,
                                             MainCharacter mainCharacter,
                                             Array<InteractiveTileObject> interactiveTiledObjects) {

        mapLoader = new TmxMapLoader();

        //Setting TiledMap renderer
        rendererTiledMap = new OrthogonalTiledMapRenderer(tiledMap,1/GameConfig.PPM);
        //rendererTiledMap = new OrthogonalTiledMapRenderer(map);

        //Getting layers from the level file
        MapLayers mapLayers = tiledMap.getLayers();

        //Go through all the folders/layers/objects in the map
        for(MapLayer mapLayer : mapLayers) {
            RecursiveSeraching(mapLayer,world,
                    //Living and Non-Living objects
                    mainCharacter,
                    interactiveTiledObjects);
        }
    }

    //TODO: IMP1
    private static ChainShape createPolyline(PolylineMapObject polyline) {
        float[] vertices = polyline.getPolyline().getTransformedVertices(); // Get all vertecies from the polyline
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for(int i = 0; i < worldVertices.length; i++) {
            worldVertices[i] = new Vector2(vertices[i * 2] / GameConfig.PPM, vertices[i * 2 + 1] / GameConfig.PPM);
            //worldVertices[i] = new Vector2(vertices[i * 2], vertices[i * 2 + 1]);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }

    //Wrappers
    public static void OrthogonalTiledMapRendererSetView (OrthographicCamera camera) {
        rendererTiledMap.setView(camera);
    }

    public static void OrthogonalTiledMapRendererRender () {
        rendererTiledMap.render();
    }


    @Override
    public void dispose() {
        rendererTiledMap.dispose();
    }
}

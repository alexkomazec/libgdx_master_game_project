package com.potatowars.tilemap_handling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.utils.Disposable;
import com.potatowars.box2d.Box2dBodyBuilder;
import com.potatowars.config.GameConfig;
import com.potatowars.sprites.DummyHero;

public class TilemapHandler implements Disposable {

    /*Getting the tiled map to the game*/

    //Load the map into the code
    private static TmxMapLoader mapLoader;
    //Tiled Map reference
    private static TiledMap map;

    //This is what renders our map to the screen
    private static OrthogonalTiledMapRenderer rendererTiledMap;

    private TilemapHandler() { }


    public static void createMap(World world, String fileName,DummyHero dummyHero )
    {
        parseTiledObjectLayer(world,fileName,dummyHero);
    }

    public static void parseTiledObjectLayer(World world,String fileName,DummyHero dummyHero) {

        mapLoader = new TmxMapLoader();

        //Loading level file from assets
        TiledMap map = mapLoader.load(fileName);

        //Setting TiledMap renderer
        rendererTiledMap = new OrthogonalTiledMapRenderer(map,1/GameConfig.PPM);

        //Getting layers from the level file
        MapLayers mapLayers = map.getLayers();

        for(MapLayer mapLayer : mapLayers){

            MapObjects objects = mapLayer.getObjects();
            int bodyType = GameConfig.iSTATIC; //Static is default value


            for(MapObject object : objects) {

                if(mapLayer.getName().contains(GameConfig.strSTATIC)) {
                    bodyType = GameConfig.iSTATIC;
                }else if(mapLayer.getName().contains(GameConfig.strDYNAMIC)){
                    bodyType = GameConfig.iDYNAMIC;
                }else if(mapLayer.getName().contains(GameConfig.strKINEMATIC)){
                    bodyType = GameConfig.iKINEMATIC;
                }else{
                    System.out.println(GameConfig.DEVELOPMENT_ERROR + " no valid body type in the name of .tmx file");
                    Gdx.app.exit();
                }

                if(object instanceof PolylineMapObject) {
                    Shape shape = createPolyline((PolylineMapObject) object);
                    Box2dBodyBuilder.createBox(world,bodyType,shape);
                } else {
                    Rectangle rect = ((RectangleMapObject)object).getRectangle();

                    PolygonShape shape = new PolygonShape();

                    //Force move scenario, explanaiton: Memory for dummyPlayer has been allocated (Empty constructor, because at this time, x,y,width,height
                    // parameters are missing) in Loading Screen. So the first time when there are all needed information for initilazing dummyPlayer is here.
                    // It is immposible to pass dummyPlayer to  parseTiledObjectLayer(This method) because Java always passes by value, so allocating new memmory
                    // by dummyPlayer referece (dummyPlayer reference from parseTiledObjectLayer is just a copy of the "real dummyPlayer" reference) will allocate new
                    // piece of memmory that dummyPlayer reference (coppied one) will be pointing to, so passed, original dummyPlayer reference will be untouched
                    //TODO: REF1: Think about a better solution. This solution works well but portability is poor.
                    if(mapLayer.getName().contains(GameConfig.PLAYER)){
                        dummyHero.setB2Body(Box2dBodyBuilder.createBox(world,rect.getX() + rect.getWidth()/2,rect.getY() + rect.getHeight()/2, rect.getWidth(),rect.getHeight(),bodyType,shape));
                    }else {
                        Box2dBodyBuilder.createBox(
                                world,
                                rect.getX() + rect.getWidth() / 2,
                                rect.getY() + rect.getHeight() / 2,
                                rect.getWidth() / 2,
                                rect.getHeight() / 2,
                                bodyType,
                                shape
                        );
                    }
                }
            }
        }
    }

    //TODO: IMP1
    private static ChainShape createPolyline(PolylineMapObject polyline) {
        float[] vertices = polyline.getPolyline().getTransformedVertices(); // Get all vertecies from the polyline
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for(int i = 0; i < worldVertices.length; i++) {
            worldVertices[i] = new Vector2(vertices[i * 2] / GameConfig.PPM, vertices[i * 2 + 1] / GameConfig.PPM);
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

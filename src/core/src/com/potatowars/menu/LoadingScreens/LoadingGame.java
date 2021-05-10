package com.potatowars.menu.LoadingScreens;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;
import com.potatowars.assets.AssetPaths;
import com.potatowars.box2d.Box2dWorld;
import com.potatowars.box2d.WorldContactListener;
import com.potatowars.menu.PlayScreens.PlayScreen;
import com.potatowars.sprites.characters.MainCharacter;
import com.potatowars.sprites.Animation.TextureAtlasCommonContainer;
import com.potatowars.sprites.items.InteractiveTileObject;
import com.potatowars.tilemap.tilemap_handler.TilemapHandler;

public class LoadingGame extends LoadingScreenBase {

    //Creating living and non-living objets
    MainCharacter mainCharacter;


    Viewport viewport;

    private Box2dWorld box2dWorld;
    private Array<InteractiveTileObject> interactiveTiledObjects;


    // == constructors ==
    public LoadingGame(PotatoWars game, Viewport viewport) {
        super(game);
        this.viewport = viewport;

        //Initilize eveyrthing

        //Initilazing world
        box2dWorld = Box2dWorld.getInstance();
        box2dWorld.getWorld().setContactListener(new WorldContactListener());

        //Load all frames to the asset manager
        assetManager.load(AssetDescriptors.HERO_FRAMES);
        assetManager.finishLoading();

        //Set Texture atlasses
        TextureAtlasCommonContainer.TextureAtlasInit(game);

        //Allocating memory for living and non-living objects in the game
        mainCharacter = new MainCharacter(game);
        interactiveTiledObjects = new Array<>();
    }

    // == public methods ==
    @Override
    public void show() {
        super.show();

        //Parse .tmx map, fill the world with bodies, and also set living and non-living object
        //to point to particular bodies in order to have a control of all the bodies.
        TilemapHandler.createMap(box2dWorld.getWorld(),AssetPaths.LEVEL1,
                //Living and Non-Living objects
                mainCharacter,
                interactiveTiledObjects);
    }

    @Override
    public void render(float delta) {
       super.render(delta);

        if(changeScreen) {
            game.setScreen(new PlayScreen(game, mainCharacter,box2dWorld));
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}

package com.potatowars.menu.LoadingScreens;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;
import com.potatowars.assets.AssetPaths;
import com.potatowars.box2d.Box2dWorld;
import com.potatowars.box2d.WorldContactListener;
import com.potatowars.config.GameConfig;
import com.potatowars.menu.PlayScreens.PlayScreen;
import com.potatowars.sprites.Animation.TextureAtlasCommonContainer;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.characters.playableCharacters.classes.Hunter;
import com.potatowars.sprites.characters.playableCharacters.classes.Mage;
import com.potatowars.sprites.characters.playableCharacters.classes.Warrior;
import com.potatowars.sprites.items.InteractiveTileObject;
import com.potatowars.tilemap.tilemap_handler.TilemapHandler;

import static com.potatowars.assets.AssetPaths.HUNTER_FRAME_ATLAS;
import static com.potatowars.assets.AssetPaths.MAGE_FRAME_ATLAS;
import static com.potatowars.assets.AssetPaths.WARRIOR_FRAME_ATLAS;

public class LoadingGame extends LoadingScreenBase {

    //Creating living and non-living objets
    MainCharacter mainCharacter;

    Viewport viewport;

    private Box2dWorld box2dWorld;
    private Array<InteractiveTileObject> interactiveTiledObjects;


    // == constructors ==
    public LoadingGame(PotatoWars game, Viewport viewport, GameConfig.HeroType heroSelected) {
        super(game);
        this.viewport = viewport;

        //Initilize eveyrthing

        //Initilazing world
        box2dWorld = Box2dWorld.getInstance();
        box2dWorld.getWorld().setContactListener(new WorldContactListener());

        //Load all frames to the asset manager
        assetManager.loadResource(AssetDescriptors.HERO_FRAMES);

        TextureAtlas heroTextureAtlas;

        //Allocating memory for living and non-living objects in the game
        switch(heroSelected){
            case WARRIOR_SELECTED:

                 mainCharacter = new Warrior(game);

                    //Load the texture atlas into the asset Manager
                    assetManager.loadResource(AssetDescriptors.WARRIOR_FRAMES);

                 heroTextureAtlas = assetManager.getResource(AssetDescriptors.WARRIOR_FRAMES);

                //Put out the Texture atlas from the pool
                TextureAtlasCommonContainer.addAllHeroTextureRegions(mainCharacter,heroTextureAtlas,WARRIOR_FRAME_ATLAS);

                break;
            case MAGE_SELECTED:
                mainCharacter = new Mage(game);

                //Load the texture atlas into the asset Manager
                assetManager.loadResource(AssetDescriptors.MAGE_FRAMES);

                heroTextureAtlas = assetManager.getResource(AssetDescriptors.MAGE_FRAMES);
                //Put out the Texture atlas from the pool
                TextureAtlasCommonContainer.addAllHeroTextureRegions(mainCharacter,heroTextureAtlas,MAGE_FRAME_ATLAS);

                break;
            case HUNTER_SELECTED:

                mainCharacter = new Hunter(game);
                assetManager.loadResource(AssetDescriptors.HUNTER_FRAMES);

                heroTextureAtlas = assetManager.getResource(AssetDescriptors.HUNTER_FRAMES);

                //Put out the Texture atlas from the pool
                TextureAtlasCommonContainer.addAllHeroTextureRegions(mainCharacter,heroTextureAtlas,HUNTER_FRAME_ATLAS);

                break;
            default:
                assetManager.loadResource(AssetDescriptors.WARRIOR_FRAMES);
                mainCharacter = new Warrior(game);
        }

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

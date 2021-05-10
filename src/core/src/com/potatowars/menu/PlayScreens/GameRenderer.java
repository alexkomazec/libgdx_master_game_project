package com.potatowars.menu.PlayScreens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;
import com.potatowars.assets.RegionNames;
import com.potatowars.box2d.Box2dBodyBuilder;
import com.potatowars.box2d.Box2dWorld;
import com.potatowars.config.GameConfig;
import com.potatowars.menu.Background;
import com.potatowars.sprites.characters.MainCharacter;
import com.potatowars.tilemap.tilemap_handler.TilemapHandler;
import com.potatowars.util.GdxUtils;
import com.potatowars.util.ViewportUtils;
import com.potatowars.util.debug.DebugCameraController;

public class GameRenderer implements Disposable {

    private static final Logger log = new Logger(GameRenderer.class.getName(), Logger.DEBUG);

    // == attributes ==
    private ShapeRenderer renderer;

    //World view
    private OrthographicCamera camera;
    private Viewport viewport;

    //Hud view
    private OrthographicCamera hudCamera;
    private Viewport hudViewport;

    //Texture related stuff
    private SpriteBatch batch;
    private Texture tex;

    private DebugCameraController debugCameraController;
    private final GameController controller;
    private TextureRegion backgroundRegion;

    private Box2dWorld box2dWorld;

    MainCharacter mainCharacter;

    PotatoWars game;

    // == constructors ==
    public GameRenderer(PotatoWars game, GameController controller, AssetManager assetManager, Box2dWorld box2dWorld, MainCharacter mainCharacter) {
        this.controller = controller;
        this.mainCharacter = mainCharacter;
        this.game   =   game;
        tex = new Texture("sprites/hero/cat.png");
        init(assetManager,box2dWorld);
    }

    // == init ==
    private void init(AssetManager assetManager,Box2dWorld box2dWorld) {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        this.box2dWorld = box2dWorld;
        camera = new OrthographicCamera();
        viewport = new FitViewport(Box2dBodyBuilder.divideByPpm(GameConfig.GAME_WIDTH), Box2dBodyBuilder.divideByPpm(GameConfig.GAME_HEIGHT) ,camera);
        //viewport = new FitViewport(GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT,camera);
        //camera.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()*2,0);

        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, hudCamera);
        batch = game.getBatch();


        // create debug camera controller
        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(viewport.getWorldWidth()/2, viewport.getWorldHeight()*2);
        renderer = new ShapeRenderer();

        //Getting texture atlas from asset manager
        TextureAtlas backGround = assetManager.get(AssetDescriptors.BACK_GROUND);

        backgroundRegion = backGround.findRegion(RegionNames.BACKGROUND);

        //(GameConfig.WORLD_HEIGHT/v )*(1/GameConfig.WORLD_BOX_SIZE)

    }


    // == public methods ==
    public void render(float delta) {

        box2dWorld.getWorld().step(1/60f,6,2);
        camera.position.set(mainCharacter.b2body.getPosition().x, mainCharacter.b2body.getPosition().y,0);

        // not wrapping inside alive cuz we want to be able to control camera even when there is game over
        //debugCameraController.handleDebugInput(delta);
        //debugCameraController.applyTo(camera);

        // clear screen
        GdxUtils.clearScreen();
        camera.update();

        TilemapHandler.OrthogonalTiledMapRendererSetView(camera);
        TilemapHandler.OrthogonalTiledMapRendererRender();

        box2dWorld.Box2DDebugRendererRender(box2dWorld.getWorld(),camera.combined);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
            //batch.draw(tex, dummyHero.getB2Body_positionX() - (tex.getWidth()/2), dummyHero.getB2Body_positionY() - (tex.getHeight()/2));
            /*batch.draw(
                    tex,
                    dummyHero.getB2Body_positionX() - Box2dBodyBuilder.divideByPpm(dummyHero.getWeight()),
                    dummyHero.getB2Body_positionY() - Box2dBodyBuilder.divideByPpm(dummyHero.getHeight()),
                    Box2dBodyBuilder.divideByPpm(dummyHero.getWeight()*2),
                    Box2dBodyBuilder.divideByPpm(dummyHero.getHeight()*2)
            );*/
            mainCharacter.draw(batch);
        batch.end();

        //renderGamePlay();

        // render debug graphics
        //renderDebug();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // == private methods ==
    private void renderGamePlay() {
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // draw background
        Background background = controller.getBackground();
        batch.draw(backgroundRegion,
                background.getX(), background.getY(),
                background.getWidth(), background.getHeight()
        );

        batch.end();
    }

    private void renderDebug() {
        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        renderer.end();

        ViewportUtils.drawGrid(viewport, renderer);
    }

}
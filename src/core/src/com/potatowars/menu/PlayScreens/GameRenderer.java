package com.potatowars.menu.PlayScreens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.tilemap.tilemap_handler.TilemapHandler;
import com.potatowars.util.GdxUtils;
import com.potatowars.util.ViewportUtils;
import com.potatowars.util.debug.DebugCameraController;

import static com.potatowars.config.GameConfig.ENABLE_IT;
import static com.potatowars.config.GameConfig.box2dBodyRenderrer_flag;
import static com.potatowars.config.GameConfig.cameraDebugging_flag;
import static com.potatowars.config.GameConfig.mapRenderrer_flag;

public class GameRenderer implements Disposable {

    private static final Logger log = new Logger(GameRenderer.class.getName(), Logger.DEBUG);

    // == attributes ==
    private ShapeRenderer renderer;

    //World view
    private OrthographicCamera camera;
    private Viewport viewport;

    //Hud view
    private Viewport hudViewport;

    //Texture related stuff
    private SpriteBatch batch;

    private DebugCameraController debugCameraController;
    private final GameController controller;
    private TextureRegion backgroundRegion;

    private Box2dWorld box2dWorld;

    MainCharacter mainCharacter;

    PotatoWars game;

    Hud hud;

    // == constructors ==
    public GameRenderer(PotatoWars game, GameController controller, AssetManager assetManager, Box2dWorld box2dWorld, MainCharacter mainCharacter,Hud hud) {
        this.controller = controller;
        this.mainCharacter = mainCharacter;
        this.game   =   game;
        this.hud    =   hud;
        init(assetManager,box2dWorld);
    }

    // == init ==
    private void init(AssetManager assetManager,Box2dWorld box2dWorld) {
        Gdx.app.setLogLevel(Application.LOG_NONE);

        this.box2dWorld = box2dWorld;
        camera = new OrthographicCamera();
        viewport = new FitViewport(Box2dBodyBuilder.divideByPpm(GameConfig.GAME_WIDTH), Box2dBodyBuilder.divideByPpm(GameConfig.GAME_HEIGHT) ,camera);

        hudViewport = new FitViewport(GameConfig.HUD_WIDTH/GameConfig.PPM, GameConfig.HUD_HEIGHT/GameConfig.PPM, camera);
        batch = game.getBatch();


        // create debug camera controller
        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(viewport.getWorldWidth()/2, viewport.getWorldHeight()*2);
        renderer = new ShapeRenderer();

        //Getting texture atlas from asset manager
        TextureAtlas backGround = assetManager.get(AssetDescriptors.BACK_GROUND);

        backgroundRegion = backGround.findRegion(RegionNames.BACKGROUND);

    }


    // == public methods ==
    public void render(float delta) {

        box2dWorld.getWorld().step(1/60f,6,2);
        camera.position.set(mainCharacter.b2body.getPosition().x, mainCharacter.b2body.getPosition().y,0);

        if(ENABLE_IT == cameraDebugging_flag ){
            debugCameraController.handleDebugInput(delta);
            debugCameraController.applyTo(camera);
        }

        // clear screen
        GdxUtils.clearScreen();
        camera.update();

        if(ENABLE_IT == mapRenderrer_flag) {
            TilemapHandler.OrthogonalTiledMapRendererSetView(camera);
            TilemapHandler.OrthogonalTiledMapRendererRender();
        }

        if(ENABLE_IT == box2dBodyRenderrer_flag) {
            box2dWorld.Box2DDebugRendererRender(box2dWorld.getWorld(), camera.combined);
        }

        //Rendering everything that is not a map
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
            mainCharacter.draw(batch);
        batch.end();

        //Rendering HUD as a top layer
        game.getBatch().setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

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
    // This method is currently unused but planned to used in the future as a kind
    //of gameplay effects
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

    // Debbuging methods that is currently unused
    // It can be used for shape rendering
    private void renderDebug() {
        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        renderer.end();

        ViewportUtils.drawGrid(viewport, renderer);
    }

}
package com.potatowars.menu.LoadingScreens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetManagmentHandler;
import com.potatowars.config.GameConfig;
import com.potatowars.menu.ViewPortConfiguration;
import com.potatowars.util.GdxUtils;

public class LoadingScreenBase extends ScreenAdapter {

    // == constants ==
    protected static final Logger log = new Logger(LoadingIntroScreen.class.getName(), Logger.DEBUG);

    protected static final float PROGRESS_BAR_WIDTH = GameConfig.PHYSICAL_WIDTH / 2f; // world units
    protected static final float PROGRESS_BAR_HEIGHT = 60; // world units

    // == attributes ==
    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected ShapeRenderer renderer;

    protected float progress;
    protected float waitTime = 0.75f;
    protected boolean changeScreen;

    protected final PotatoWars game;
    protected final AssetManagmentHandler assetManager;

    // == constructors ==
    public LoadingScreenBase(PotatoWars game) {
        this.game = game;
        assetManager = game.getAssetManagmentHandler();
    }

    // == public methods ==
    @Override
    public void show() {
        log.debug("show");

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(
                false,
                ViewPortConfiguration.getPhysicalWidth(),
                ViewPortConfiguration.getPhysicalHeight()
        );

        this.viewport = new ScreenViewport(camera);
        this.renderer = new ShapeRenderer();

        ViewPortConfiguration.calculateViewport(20, 20);
        //dummyHero = new DummyHero(game.getBox2dWorld().getWorld(),0,0);
        //game.getTilemapHandler().createMap("maps/valleyOfTheHungry_Level1/valleyOfTheHungry.tmx",dummyHero);
    }

    @Override
    public void render(float delta) {
        update(delta);

        GdxUtils.clearScreen();

        /*Apply viewport dimensions to camera*/
        this.viewport.apply();

        this.renderer.setProjectionMatrix(this.camera.combined);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);

        draw();

        this.renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        log.debug("hide");
        dispose();
    }

    @Override
    public void dispose() {
        log.debug("dispose");
        this.renderer.dispose();
        this.renderer = null;
    }

    // == private methods ==
    private void update(float delta) {
        // progress is between 0 and 1
        this.progress = this.assetManager.getProgress();

        // update returns true when all assets are loaded
        if(this.assetManager.updateAssetLoading()) {
            this.waitTime -= delta;

            if(this.waitTime <= 0) {
                this.changeScreen = true;
            }
        }
    }

    private void draw() {
        float progressBarX = (GameConfig.PHYSICAL_WIDTH - PROGRESS_BAR_WIDTH) / 2f;
        float progressBarY = (GameConfig.PHYSICAL_HEIGHT - PROGRESS_BAR_HEIGHT) / 2f;

        this.renderer.rect(progressBarX, progressBarY,
                this.progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT
        );
    }

}

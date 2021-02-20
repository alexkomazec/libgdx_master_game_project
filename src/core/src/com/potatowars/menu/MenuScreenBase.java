package com.potatowars.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;
import com.potatowars.common.GameManager;
import com.potatowars.config.GameConfig;
import com.potatowars.util.GdxUtils;

public abstract class MenuScreenBase extends ScreenAdapter {

    // == members == //

    //Instance of a game
    protected final PotatoWars game;

    //An asset manager
    protected final AssetManager assetManager;

    //Viewport
    private Viewport viewport;

    //Stage
    private Stage stage;

    protected Music music;
    protected Sound sound;

    // == constructor == //
    public MenuScreenBase(PotatoWars game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
        this.music = assetManager.get(AssetDescriptors.BACKGROUND_MUSIC);
        this.sound = assetManager.get(AssetDescriptors.CLICK_SOUND);
    }

    // == public methods == //
    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(createUi());

        playPauseBckMusic();
    }

    private void playPauseBckMusic()
    {
        if(GameManager.INSTANCE.isGameMusic()){
            music.play();
        }
        else{
            music.pause();
        }
    }

    protected void CheckAndPlayMenuSound(){
        if(GameManager.INSTANCE.isGameSound()){
            sound.play();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
       dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    // == protected methods == //
    protected abstract Actor createUi();
}

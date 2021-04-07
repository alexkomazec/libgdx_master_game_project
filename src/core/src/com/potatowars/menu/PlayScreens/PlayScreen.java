package com.potatowars.menu.PlayScreens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.potatowars.PotatoWars;
import com.potatowars.menu.MenuScreenBase;
import com.potatowars.sprites.DummyHero;
import com.potatowars.tilemap_handling.TilemapHandler;

public class PlayScreen extends MenuScreenBase {
    //PlayScreen is the top view class in which everything game play related will be happening

    //Controller module is rensponsible for calculation positions/checking collisions, etc...
    private GameController controller;

    //Game Renderer module is repsonsible for showing textures on positions that are calculated in GameController
    private GameRenderer renderer;

    //This is an instance of the main character
    private DummyHero dummyHero;

    public PlayScreen(PotatoWars game, DummyHero dummyHero) {
        super(game);
        this.dummyHero = dummyHero;
    }

    //PlayScreen is an active screen, so createUi is supposed to be used in non-active screens
    @Override
    protected Actor createUi() {
        return null;
    }

    @Override
    public void show() {
        controller = new GameController(dummyHero);
        renderer = new GameRenderer(controller,assetManager,game.getBox2dWorld(),dummyHero);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }


}

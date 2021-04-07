package com.potatowars.menu.LoadingScreens;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetPaths;
import com.potatowars.menu.PlayScreens.PlayScreen;
import com.potatowars.sprites.DummyHero;
import com.potatowars.tilemap_handling.TilemapHandler;

public class LoadingGame extends LoadingScreenBase {

    DummyHero dummyHero;
    Viewport viewport;

    // == constructors ==
    public LoadingGame(PotatoWars game, Viewport viewport) {
        super(game);
        this.viewport = viewport;
        dummyHero = new DummyHero();
    }

    // == public methods ==
    @Override
    public void show() {
        super.show();

        TilemapHandler.createMap(game.getBox2dWorld().getWorld(),AssetPaths.LEVEL1,dummyHero);
    }

    @Override
    public void render(float delta) {
       super.render(delta);

        if(changeScreen) {
            game.setScreen(new PlayScreen(game,dummyHero));
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

package com.potatowars.menu.LoadingScreens;

import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;
import com.potatowars.menu.MenuScreen;

public class LoadingIntroScreen extends LoadingScreenBase {

    // == constructors ==
    public LoadingIntroScreen(PotatoWars game) {
        super(game);
    }

    // == public methods ==
    @Override
    public void show() {
        super.show();

        assetManager.load(AssetDescriptors.BACKGROUND_MUSIC);
        assetManager.load(AssetDescriptors.CLICK_SOUND);
        assetManager.load(AssetDescriptors.FONT);
        assetManager.load(AssetDescriptors.BACK_GROUND);
        assetManager.load(AssetDescriptors.UI_SKIN);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(changeScreen) {
            game.setScreen(new MenuScreen(game));
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

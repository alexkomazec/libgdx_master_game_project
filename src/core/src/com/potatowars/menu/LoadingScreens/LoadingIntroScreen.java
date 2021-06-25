package com.potatowars.menu.LoadingScreens;

import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;
import com.potatowars.menu.EndGameScreens.EndLevelScreen;
import com.potatowars.menu.MenuScreen;

import java.awt.Menu;

public class LoadingIntroScreen extends LoadingScreenBase {

    // == constructors ==
    public LoadingIntroScreen(PotatoWars game) {
        super(game);
    }

    // == public methods ==
    @Override
    public void show() {
        super.show();
        game.getAssetManagmentHandler().loadResource(
                AssetDescriptors.BACKGROUND_MUSIC,
                AssetDescriptors.CLICK_SOUND,
                AssetDescriptors.FONT,
                AssetDescriptors.BACK_GROUND,
                AssetDescriptors.UI_SKIN
        );
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

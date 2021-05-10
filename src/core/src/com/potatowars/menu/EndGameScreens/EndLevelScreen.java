package com.potatowars.menu.EndGameScreens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;
import com.potatowars.assets.RegionNames;
import com.potatowars.menu.MenuScreen;
import com.potatowars.menu.MenuScreenBase;

public class EndLevelScreen extends MenuScreenBase {

    public EndLevelScreen(PotatoWars game) {
        super(game);
    }

    @Override
    protected Actor createUi() {
        Table table = new Table();

        //Getting texture atlas from asset manager
        TextureAtlas backGround = assetManager.get(AssetDescriptors.BACK_GROUND);

        //Getting skin for all the menus
        Skin uiskin = assetManager.get(AssetDescriptors.UI_SKIN);

        TextureRegion backgroundRegion = backGround.findRegion(RegionNames.BACKGROUND);
        table.setBackground(new TextureRegionDrawable(backgroundRegion));

        // highScore label
        Label highscoreLabel = new Label("High score", uiskin);
        // higscore read only button
        TextButton highscoreButton = new TextButton("000",uiskin,"readOnly");

        //Back to main menu
        TextButton mainMenuButton = new TextButton("Main Menu",uiskin);
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                CheckAndPlayMenuSound();
                game.setScreen(new MenuScreen(game));
            }
        });

        // setup table
        Table buttonTable = new Table(uiskin);
        buttonTable.defaults().pad(20);

        buttonTable.add(highscoreLabel).row();
        buttonTable.add(highscoreButton).row();
        buttonTable.add(mainMenuButton);

        buttonTable.center();

        table.add(buttonTable);
        table.center();
        table.setFillParent(true);
        table.pack();

        return table;
    }
}

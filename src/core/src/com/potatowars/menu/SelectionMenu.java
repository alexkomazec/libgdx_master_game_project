package com.potatowars.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;
import com.potatowars.assets.RegionNames;
import com.potatowars.config.GameConfig;
import com.potatowars.menu.LoadingScreens.LoadingGame;

public class SelectionMenu extends MenuScreenBase {

    GameConfig.HeroType heroSelected;

    public SelectionMenu(PotatoWars game,Viewport viewport) {
        super(game);
        heroSelected = GameConfig.HeroType.WARRIOR_SELECTED;
    }

    @Override
    protected Actor createUi() {
        Table table = new Table();

        //Getting texture atlas from asset manager
        TextureAtlas backGround = assetManager.getResource(AssetDescriptors.BACK_GROUND);

        //Getting skin for all the menus
        Skin uiskin = assetManager.getResource(AssetDescriptors.UI_SKIN);

        TextureRegion backgroundRegion = backGround.findRegion(RegionNames.BACKGROUND);
        table.setBackground(new TextureRegionDrawable(backgroundRegion));

        // Warrior selection button
        final TextButton selectWarrior = new TextButton("Warrior",uiskin);
        selectWarrior.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                CheckAndPlayMenuSound();
                heroSelected = GameConfig.HeroType.WARRIOR_SELECTED;
                game.setScreen(new LoadingGame(game,getViewport(),heroSelected));
            }
        });

        // Mage selection button
        TextButton selectMage = new TextButton("Mage",uiskin);
        selectMage.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                CheckAndPlayMenuSound();
                heroSelected = GameConfig.HeroType.MAGE_SELECTED;
                game.setScreen(new LoadingGame(game,getViewport(),heroSelected));
            }
        });

        // Warrior selection button
        TextButton selectHunter = new TextButton("Hunter",uiskin);
        selectHunter.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                CheckAndPlayMenuSound();
                heroSelected = GameConfig.HeroType.HUNTER_SELECTED;
                game.setScreen(new LoadingGame(game,getViewport(),heroSelected));
            }
        });

        //Back to main menu
        TextButton mainMenuButton = new TextButton("Back",uiskin);
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

        buttonTable.add(selectWarrior).row();
        buttonTable.add(selectHunter).row();
        buttonTable.add(selectMage).row();
        buttonTable.add(mainMenuButton);

        buttonTable.center();

        table.add(buttonTable);
        table.center();
        table.setFillParent(true);
        table.pack();

        return table;
    }

}

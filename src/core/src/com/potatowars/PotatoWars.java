package com.potatowars;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.potatowars.box2d.Box2dWorld;
import com.potatowars.config.GameConfig;
import com.potatowars.menu.LoadingScreens.LoadingIntroScreen;
import com.potatowars.menu.ViewPortConfiguration;

public class PotatoWars extends Game {

	//Resources stuff
	private AssetManager assetManager;
	private SpriteBatch batch;

	@Override
	public void create() {
		assetManager = new AssetManager();
		assetManager.getLogger().setLevel(Logger.DEBUG);

		batch = new SpriteBatch();

		ViewPortConfiguration.setupPhysicalSize();

		//tilemapHandler = new TilemapHandler(box2dWorld);

		setScreen(new LoadingIntroScreen(this));

	}

	@Override
	public void dispose() {
		assetManager.dispose();
		batch.dispose();
	}

	/*Getters*/

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}

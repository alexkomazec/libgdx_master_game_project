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
import com.potatowars.assets.AssetManagmentHandler;
import com.potatowars.box2d.Box2dWorld;
import com.potatowars.config.GameConfig;
import com.potatowars.menu.LoadingScreens.LoadingIntroScreen;
import com.potatowars.menu.ViewPortConfiguration;

public class PotatoWars extends Game {

	//Resources stuff
	private SpriteBatch batch;
	AssetManagmentHandler assetManagmentHandler;

	@Override
	public void create() {

		batch = new SpriteBatch();
		ViewPortConfiguration.setupPhysicalSize();
		assetManagmentHandler = new AssetManagmentHandler();

		setScreen(new LoadingIntroScreen(this));
	}

	public AssetManagmentHandler getAssetManagmentHandler() {
		return assetManagmentHandler;
	}

	@Override
	public void dispose() {
		assetManagmentHandler.getAssetManager().dispose();
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}

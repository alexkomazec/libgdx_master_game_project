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
import com.potatowars.box2d.WorldContactListener;
import com.potatowars.config.GameConfig;
import com.potatowars.map.MapManager;
import com.potatowars.menu.LoadingScreens.LoadingIntroScreen;
import com.potatowars.menu.ViewPortConfiguration;

public class PotatoWars extends Game {

	private static PotatoWars _instance = null;

	public static PotatoWars getInstance() {
		if (_instance == null) {
			_instance = new PotatoWars();
		}

		return _instance;
	}

	private PotatoWars(){}


	//Resources stuff
	private SpriteBatch batch;
	AssetManagmentHandler assetManagmentHandler;
	private Box2dWorld box2dWorld;

	@Override
	public void create() {

		//Initilazing world
		box2dWorld = Box2dWorld.getInstance();
		box2dWorld.getWorld().setContactListener(new WorldContactListener());

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

	public Box2dWorld getBox2dWorld() {
		return box2dWorld;
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}

package com.potatowars;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.potatowars.box2d.Box2dWorld;
import com.potatowars.menu.LoadingScreens.LoadingIntroScreen;

public class PotatoWars extends Game {
	private AssetManager assetManager;
	private SpriteBatch batch;

	//private TilemapHandler tilemapHandler;
	private Box2dWorld box2dWorld;


	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		assetManager = new AssetManager();
		assetManager.getLogger().setLevel(Logger.DEBUG);

		batch = new SpriteBatch();

		box2dWorld = Box2dWorld.getInstance();
		//tilemapHandler = new TilemapHandler(box2dWorld);

		setScreen(new LoadingIntroScreen(this));

	}


	@Override
	public void dispose() {
		assetManager.dispose();
		batch.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Box2dWorld getBox2dWorld() {
		return box2dWorld;
	}


}

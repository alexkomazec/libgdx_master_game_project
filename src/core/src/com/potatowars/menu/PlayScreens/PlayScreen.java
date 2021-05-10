package com.potatowars.menu.PlayScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.potatowars.PotatoWars;
import com.potatowars.box2d.Box2dWorld;
import com.potatowars.menu.EndGameScreens.EndLevelScreen;
import com.potatowars.menu.MenuScreenBase;
import com.potatowars.sprites.characters.MainCharacter;
import com.potatowars.sprites.items.InteractiveTileObject;

public class PlayScreen extends MenuScreenBase {
    //PlayScreen is the top view class in which everything game play related will be happening

    //Controller module is rensponsible for calculation positions/checking collisions, etc...
    private GameController controller;

    //Game Renderer module is repsonsible for showing textures on positions that are calculated in GameController
    private GameRenderer renderer;

    //Living and Non-living object references
    private MainCharacter mainCharacter;

    private PotatoWars game;

    public static boolean exit_door;

    Box2dWorld box2dWorld;

    public PlayScreen(PotatoWars game, MainCharacter mainCharacter, Box2dWorld box2dWorld) {
        super(game);
        this.box2dWorld = box2dWorld;
        this.game = game;
        this.mainCharacter = mainCharacter;
    }

    //PlayScreen is an active screen, so createUi is supposed to be used in non-active screens
    @Override
    protected Actor createUi() {
        return null;
    }

    @Override
    public void show() {
        controller = new GameController(mainCharacter,game);
        renderer = new GameRenderer(game,controller,assetManager,box2dWorld, mainCharacter);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);

        if(exit_door){
            exit_door = false;
            box2dWorld.dispose();
            game.setScreen(new EndLevelScreen(game));
        }
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

package com.potatowars.menu.PlayScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.potatowars.PotatoWars;
import com.potatowars.config.GameConfig;
import com.potatowars.menu.Background;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.commonParameters.EnvironmentalDifficulty;

public class GameController {

    private Background background;
    private MainCharacter mainCharacter;
    private PotatoWars game;
    //private Hud hud;
    private static boolean debug_walking;
    private EnvironmentalDifficulty environmentalDifficulty;
    // == constructors ==
    public GameController(MainCharacter mainCharacter, PotatoWars game, /*Hud hud,*/ EnvironmentalDifficulty environmentalDifficulty) {
        this.game = game;
        //this.hud = hud;
        this.environmentalDifficulty = environmentalDifficulty;
        init(mainCharacter);
    }

    // == init ==
    private void init(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;

        // create background
        background = new Background();
        background.setPosition(0, 0);
        background.setSize(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
    }

    // == public methods ==
    public void update(float delta) {

        //UpdateHandleInput
        handleInput(delta, mainCharacter);

        //Update player
        mainCharacter.update(delta);

        environmentalDifficulty.update(delta);
        //Update Hud
        //hud.update(environmentalDifficulty.getEnvironmentalDifficultyValue());
    }

    public void handleInput(float dt, MainCharacter mainCharacter){
        //control our player using immediate impulses
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){
            debug_walking = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)){
            debug_walking = false;
        }

        if(!debug_walking) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                mainCharacter.b2body.applyLinearImpulse(new Vector2(0f, 1000f), mainCharacter.b2body.getWorldCenter(), true);

            //if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            //    dummyHero.b2body.applyLinearImpulse(new Vector2(0f, -4f), dummyHero.b2body.getWorldCenter(), true);

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mainCharacter.b2body.getLinearVelocity().x <= 2) {
                mainCharacter.b2body.applyLinearImpulse(new Vector2(1000f, 0), mainCharacter.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && mainCharacter.b2body.getLinearVelocity().x >= -2) {
                mainCharacter.b2body.applyLinearImpulse(new Vector2(-1000f, 0), mainCharacter.b2body.getWorldCenter(), true);
            }
        } else {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                mainCharacter.b2body.applyLinearImpulse(new Vector2(0f, 10000f), mainCharacter.b2body.getWorldCenter(), true);

            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
                mainCharacter.b2body.applyLinearImpulse(new Vector2(0f, -10000f), mainCharacter.b2body.getWorldCenter(), true);

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mainCharacter.b2body.getLinearVelocity().x <= 2) {
                mainCharacter.b2body.applyLinearImpulse(new Vector2(10000f, 0), mainCharacter.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && mainCharacter.b2body.getLinearVelocity().x >= -2) {
                mainCharacter.b2body.applyLinearImpulse(new Vector2(-1000f, 0), mainCharacter.b2body.getWorldCenter(), true);
            }
        }
    }

    public Background getBackground() {
        return background;
    }


}


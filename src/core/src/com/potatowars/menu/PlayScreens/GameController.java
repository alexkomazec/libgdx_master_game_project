package com.potatowars.menu.PlayScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.potatowars.config.GameConfig;
import com.potatowars.menu.Background;
import com.potatowars.sprites.DummyHero;

public class GameController {

    private Background background;
    private DummyHero dummyHero;
    // == constructors ==
    public GameController(DummyHero dummyHero) {
        init(dummyHero);
    }

    // == init ==
    private void init(DummyHero dummyHero) {
        this.dummyHero = dummyHero;

        // create background
        background = new Background();
        background.setPosition(0, 0);
        background.setSize(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
    }

    // == public methods ==
    public void update(float delta) {
        handleInput(delta,dummyHero);
    }

    public void handleInput(float dt, DummyHero dummyHero){
        //control our player using immediate impulses
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            dummyHero.b2body.applyLinearImpulse(new Vector2(0f, 500f), dummyHero.b2body.getWorldCenter(), true);

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            dummyHero.b2body.applyLinearImpulse(new Vector2(0f, -500f), dummyHero.b2body.getWorldCenter(), true);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && dummyHero.b2body.getLinearVelocity().x <= 2) {
            dummyHero.b2body.applyLinearImpulse(new Vector2(500f, 0), dummyHero.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && dummyHero.b2body.getLinearVelocity().x >= -2){
            dummyHero.b2body.applyLinearImpulse(new Vector2(-500f, 0), dummyHero.b2body.getWorldCenter(), true);
        }

    }

    public Background getBackground() {
        return background;
    }


}


package com.potatowars.box2d;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.potatowars.config.GameConfig;
import com.potatowars.menu.PlayScreens.PlayScreen;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {
            case GameConfig.HERO_BIT | GameConfig.EXIT_DOOR_BIT:
                if (fixA.getFilterData().categoryBits == GameConfig.HERO_BIT) {
                    //TODO: Change the screen to Highscore screen
                    PlayScreen.exit_door = true;
                    System.out.println("Collided");
                } else {
                    //TODO: Change the screen to Highscore screen
                    PlayScreen.exit_door = true;
                    System.out.println("Collided");
                }
                break;
            default:
                //TODO: Do it!
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

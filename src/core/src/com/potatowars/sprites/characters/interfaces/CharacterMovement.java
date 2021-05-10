package com.potatowars.sprites.characters.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.potatowars.sprites.commonParameters.CommonStates;

public interface CharacterMovement {

    void update(float dt);

    TextureRegion getFrame(float dt);

    CommonStates.State getState();

}

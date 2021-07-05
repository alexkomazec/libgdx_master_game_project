package com.potatowars.map;

import com.badlogic.gdx.utils.Array;
import com.potatowars.PotatoWars;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.items.InteractiveTileObject;

public interface MapLoadingObserver {

    void onNotify(PotatoWars game, MainCharacter mainCharacter, Array<InteractiveTileObject> interactiveTiledObjects);

}

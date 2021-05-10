package com.potatowars.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;

public class TextureAtlasCommonContainer {

    Array<TextureRegion> frames = new Array<TextureRegion>();

    //All important atlasses:
    //Hero:
    //Atlas
    public static TextureAtlas heroMoves;
    public static final String ALL_HERO_FRAMES = "hero_moves";

    //Atlas information
    public static final int HERO_MOVES_PNG_WIDTH = 416;
    public static final int HERO_MOVES_PNG_HEIGHT = 672;

    //Number of frames in a row and column
    public static final int NUMBER_OF_COLUMS = 13;
    public static final int NUMBER_OF_ROWS = 21;

    //Frame width and height
    public static final int FRAME_WIDTH = HERO_MOVES_PNG_WIDTH/NUMBER_OF_COLUMS;
    public static final int FRAME_HEIGHT = HERO_MOVES_PNG_HEIGHT/NUMBER_OF_ROWS;
    
    private TextureAtlasCommonContainer() {}

    public static void getAllTextureAtlasses(PotatoWars game){
        heroMoves = game.getAssetManager().get(AssetDescriptors.HERO_FRAMES);
    }

}

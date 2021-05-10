package com.potatowars.sprites.Animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;
import com.potatowars.sprites.commonParameters.CommonStates;

public class TextureAtlasCommonContainer {

    //Beginning of hero stuff ======================================================================

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

    public static Array<MovementType> movesHero = new Array<MovementType>();

    //Number of frames in animations
    private static final int NUMBER_OF_FRAMES_RUNNING = 7;

    //End of hero stuff ============================================================================


    private TextureAtlasCommonContainer() {}

    public static void TextureAtlasInit(PotatoWars game){

        //Get each Texture Regions and extract movements from it
        heroMoves = game.getAssetManager().get(AssetDescriptors.HERO_FRAMES);

        //Add starting frame position from the hero_moves.png
        movesHero.add(new MovementType(CommonStates.State.STANDING,0,352,FRAME_WIDTH,FRAME_HEIGHT));
        movesHero.add(new MovementType(CommonStates.State.JUMPING,64,352,FRAME_WIDTH,FRAME_HEIGHT));
        movesHero.add(new MovementType(CommonStates.State.RUNNING,32,352,FRAME_WIDTH,FRAME_HEIGHT));
        movesHero.add(new MovementType(CommonStates.State.FALLING,0,352,FRAME_WIDTH,FRAME_HEIGHT));
    }

    public static TextureRegion getTextureRegion(CommonStates.State movementName) {

        //Loop through the full Array
        for (int iterator = 0; iterator < movesHero.size; iterator++) {

            //Check if the current moves match
            if (movementName == (movesHero.get(iterator).getMovementName())) {

                if(CommonStates.State.STANDING == movementName)
                    return new TextureRegion(heroMoves.findRegion(ALL_HERO_FRAMES), 0, 352, FRAME_WIDTH, FRAME_HEIGHT);
                else if(CommonStates.State.JUMPING == movementName){
                    return new TextureRegion(heroMoves.findRegion(ALL_HERO_FRAMES), 64, 352, FRAME_WIDTH, FRAME_HEIGHT);
                }else if(CommonStates.State.FALLING == movementName){
                    //TODO
                }

            } else {
                //do nothing
            }
        }

        return null;
    }

    public static Animation getRunningAnimation() {

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i < NUMBER_OF_FRAMES_RUNNING; i++) {
            frames.add(new TextureRegion(heroMoves.findRegion(ALL_HERO_FRAMES),i*FRAME_WIDTH,352,FRAME_WIDTH,FRAME_HEIGHT));
        }
        return new Animation(0.1f,frames);
    }



}

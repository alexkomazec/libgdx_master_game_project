package com.potatowars.sprites.Animation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.potatowars.PotatoWars;
import com.potatowars.assets.AssetDescriptors;
import com.potatowars.assets.AssetPaths;
import com.potatowars.common.Pair;
import com.potatowars.config.GameConfig;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.characters.playableCharacters.classes.Hunter;
import com.potatowars.sprites.characters.playableCharacters.classes.Mage;
import com.potatowars.sprites.characters.playableCharacters.classes.Warrior;
import com.potatowars.sprites.commonParameters.CommonStates;
import com.potatowars.util.debug.DebugCameraController;

import static com.potatowars.assets.AssetPaths.HERO_FRAME_ATLAS;
import static com.potatowars.assets.AssetPaths.HUNTER_FRAME_ATLAS;
import static com.potatowars.assets.AssetPaths.MAGE_FRAME_ATLAS;
import static com.potatowars.assets.AssetPaths.WARRIOR_FRAME_ATLAS;
import static com.potatowars.sprites.commonParameters.CommonStates.State.ATTACKING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.BEINGHURT;
import static com.potatowars.sprites.commonParameters.CommonStates.State.DYING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.FALLING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.JUMPING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.RUNNING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.STANDING;

public class TextureAtlasCommonContainer {

    private static final Logger log = new Logger(DebugCameraController.class.getName(), Logger.DEBUG);

    //Beginning of hero stuff ======================================================================

    //Atlas
    private static Array<TextureAtlasPack> heroMoves;
    public static final String ALL_HERO_FRAMES = "warrior_moves";

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

    public static void addAllHeroTextureRegions(MainCharacter mainCharacter, TextureAtlas textureAtlas, String textreAtlasPath){

        boolean isWarrior = mainCharacter instanceof  Warrior;
        boolean isHunter = mainCharacter instanceof Hunter;
        boolean isMage = mainCharacter instanceof Mage;

        if(isWarrior || isHunter || isMage){
            //It means that some of the main characters are created
            for(int iterator = 0; iterator<mainCharacter.getMovementTypes().size;iterator++){

                String movementType = mainCharacter.getMovementTypes().get(iterator);

                switch (movementType){
                    case "stand":

                        //Calculating and Storing pair (Animation,Movement State)
                        calcAndStorePair(movementType,textureAtlas,trimTheTextureAtlasPath(textreAtlasPath),STANDING,mainCharacter);
                        break;
                    case "run":

                        //Calculating and Storing pair (Animation,Movement State)
                        calcAndStorePair(movementType,textureAtlas,trimTheTextureAtlasPath(textreAtlasPath),RUNNING,mainCharacter);
                        break;
                    case "jump":

                        //Calculating and Storing pair (Animation,Movement State)
                        calcAndStorePair(movementType,textureAtlas,trimTheTextureAtlasPath(textreAtlasPath),JUMPING,mainCharacter);
                        break;
                    case "hurt":

                        //Calculating and Storing pair (Animation,Movement State)
                        calcAndStorePair(movementType,textureAtlas,trimTheTextureAtlasPath(textreAtlasPath),BEINGHURT,mainCharacter);
                        break;
                    case "dead":

                        //Calculating and Storing pair (Animation,Movement State)
                        calcAndStorePair(movementType,textureAtlas,trimTheTextureAtlasPath(textreAtlasPath),DYING,mainCharacter);
                        break;
                    case "attack":

                        //Calculating and Storing pair (Animation,Movement State)
                        calcAndStorePair(movementType,textureAtlas,trimTheTextureAtlasPath(textreAtlasPath),ATTACKING,mainCharacter);
                        break;
                    case "shoot":

                        //Calculating and Storing pair (Animation,Movement State)
                        calcAndStorePair(movementType,textureAtlas,trimTheTextureAtlasPath(textreAtlasPath),ATTACKING,mainCharacter);
                    case "cast":

                        //Calculating and Storing pair (Animation,Movement State)
                        calcAndStorePair(movementType,textureAtlas,trimTheTextureAtlasPath(textreAtlasPath),ATTACKING,mainCharacter);
                        break;
                    case "fall":

                        //Calculating and Storing pair (Animation,Movement State)
                        calcAndStorePair(movementType,textureAtlas,trimTheTextureAtlasPath(textreAtlasPath),FALLING,mainCharacter);
                        break;

                    default:
                        log.debug("Wrong state,");
                        //Calculating and Storing pair (Animation,Movement State)
                        calcAndStorePair(movementType,textureAtlas,trimTheTextureAtlasPath(textreAtlasPath),STANDING,mainCharacter);
                }

            }
        }
    }

    private static void calcAndStorePair(String movementType,TextureAtlas textureAtlas,String textureAtlasName,CommonStates.State state,MainCharacter mainCharacter){

        Array<TextureRegion> frames = new Array<TextureRegion>();

        //Getting all the frames from the Texture Region
        frames = getFrame(movementType,textureAtlas,textureAtlasName);

        mainCharacter.getMovements().add(new Pair<Animation<TextureRegion>,CommonStates.State>(new Animation<TextureRegion>(0.1f, frames),state));
    }

    private static Array<TextureRegion> getFrame(String movementType, TextureAtlas textureAtlas, String textureAtlasName){

        Array<TextureRegion> frames = new Array<TextureRegion>();

        int [] metaFrames = {
                0, //- Number of frames
                0 //- Number of used frames
        };

        String textureRegionPostfix = "";

        int textureWidth = 0;
        int textureHeight = 0;

        switch(movementType){
            case "stand":
                metaFrames[0] = GameConfig.NUM_OF_FRAMES_RUN;
                metaFrames[1] = 1;
                textureRegionPostfix = "_walk";
                break;
            case "run":
                metaFrames[0] = GameConfig.NUM_OF_FRAMES_RUN;
                metaFrames[1] = GameConfig.NUM_OF_FRAMES_RUN;
                textureRegionPostfix = "_walk";
                break;
            case "jump":
                metaFrames[0] = GameConfig.NUM_OF_FRAMES_JUMP;
                metaFrames[1] = GameConfig.NUM_OF_FRAMES_JUMP;
                textureRegionPostfix = "_fall";
                break;
            case "hurt":
                metaFrames[0] =GameConfig.NUM_OF_FRAMES_HURT;
                metaFrames[1] = GameConfig.NUM_OF_FRAMES_HURT;
                textureRegionPostfix = "_hurt";
                break;
            case "dead":
                metaFrames[0] = GameConfig.NUM_OF_FRAMES_DEAD;
                metaFrames[1] = GameConfig.NUM_OF_FRAMES_DEAD;
                textureRegionPostfix = "_dead";
                break;
            case "attack":
                metaFrames[0] = GameConfig.NUM_OF_FRAMES_ATTACK;
                metaFrames[1] = GameConfig.NUM_OF_FRAMES_ATTACK;
                textureRegionPostfix = "_attack";
                break;
            case "shoot":
                metaFrames[0] = GameConfig.NUM_OF_FRAMES_SHOOT;
                metaFrames[1] = GameConfig.NUM_OF_FRAMES_SHOOT;
                textureRegionPostfix = "_shoot";
                break;
            case "cast":
                metaFrames[0] = GameConfig.NUM_OF_FRAMES_CAST;
                metaFrames[1] = GameConfig.NUM_OF_FRAMES_CAST;
                textureRegionPostfix = "_cast";
                break;
            case "fall":
                metaFrames[0] = GameConfig.NUM_OF_FRAMES_FALL;
                metaFrames[1] = GameConfig.NUM_OF_FRAMES_FALL;
                textureRegionPostfix = "_fall";
                break;
            default:
                log.debug("TextureAtlasCommonContainer: Wrong movementType");
                metaFrames[0] = GameConfig.NUM_OF_FRAMES_STAND;
                metaFrames[1] = GameConfig.NUM_OF_FRAMES_STAND;
                textureRegionPostfix = "_walk";
        }

        textureWidth    = textureAtlas.findRegion(textureAtlasName + textureRegionPostfix).getRegionWidth()/metaFrames[0];
        textureHeight   = textureAtlas.findRegion(textureAtlasName + textureRegionPostfix).getRegionHeight();


        for(int iterator = 0; iterator < metaFrames[1]; iterator++){
            frames.add(new TextureRegion(
                    textureAtlas.findRegion(textureAtlasName + textureRegionPostfix),
                    iterator*textureWidth,
                    0,
                    textureWidth,
                    textureHeight));
        }

        return frames;
    }

    private static String trimTheTextureAtlasPath(String textreAtlasPath){

        String textureAtlasName;

        switch(textreAtlasPath){
            case WARRIOR_FRAME_ATLAS:
                textureAtlasName = "warrior_moves";
                break;

            case HUNTER_FRAME_ATLAS:
                textureAtlasName = "hunter_moves";
                break;
            case MAGE_FRAME_ATLAS:
                textureAtlasName= "mage_moves";
                break;
            default:
                log.debug("Wrong atlas");
                textureAtlasName = null;
                Gdx.app.exit();
        }

        return textureAtlasName;
    }

}

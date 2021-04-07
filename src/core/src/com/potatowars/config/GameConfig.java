package com.potatowars.config;

public class GameConfig {

    // == Game Size ==
    public static final float WORLD_BOX_SIZE = 16f;

    // == Box2d Scalling ==
    //INFO: Creating a body with width 16 and height 16, in box2D world it is represented as 16 meters x 16 meters body
    //      which is too heavy, so scalling is important part.
    public static final float PPM = 8; // pixels per meter

    public static final float WIDTH = 800f; //pixels
    public static final float HEIGHT  = 416f; //pixels

    public static final float HUD_WIDTH = 800f; // world units
    public static final float HUD_HEIGHT = 416f; // world units

    //== Gameplay Size ==
    public static final  float GAME_WIDTH = 800f; //pixels
    public static final  float GAME_HEIGHT  = HEIGHT/2; //pixels

    public static final float GAME_WIDTH_WUNIT  = GAME_WIDTH/WORLD_BOX_SIZE; // world units
    public static final float GAME_HEIGHT_WUNIT = GAME_HEIGHT/WORLD_BOX_SIZE; // world units

    // == World Size ==
    public static final float WORLD_WIDTH = WIDTH/WORLD_BOX_SIZE; // world units
    public static final float WORLD_HEIGHT = HEIGHT/WORLD_BOX_SIZE; // world units

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f; // world units
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f; // world units


    // == Predefined string ==
    public static final String GAME_SOUND = "GAME SOUND";
    public static final String GAME_MUSIC = "GAME MUSIC";

    // == Enemy Stats ==
    public static final float ENEMY_DPS = 100f;
    public static final float ENEMY_HP = 100f;
    public static final float ENEMY_SPEED = 100f;

    // == Environmental difficulty
    public static final int SURVIVAL_DIFFICULTY = 100;

    // == Difficulty presets ==

    // == Difficulty multipliers
    private static float EASY_MULTIPLIER = 0.7f;
    private static float NORMAL_MULTIPLIER = 1f;
    private static float HARD_MULTIPLIER = 1.5f;

    // == Easy game
    public static final float ENEMY_DPS_EASY = ENEMY_DPS*EASY_MULTIPLIER; // 70% of normal
    public static final float ENEMY_HP_EASY = ENEMY_HP*EASY_MULTIPLIER; // 70% of normal
    public static final float ENEMY_SPEED_EASY = ENEMY_SPEED*EASY_MULTIPLIER; // 70% of normal
    public static final float SURVIVAL_DIFFICULTY_EASY= SURVIVAL_DIFFICULTY*EASY_MULTIPLIER; // 70% of normal

    // == Normal game
    public static final float ENEMY_DPS_NORMAL = ENEMY_DPS*NORMAL_MULTIPLIER; // 100% of normal
    public static final float ENEMY_HP_NORMAL = ENEMY_HP*NORMAL_MULTIPLIER; // 100% of normal
    public static final float ENEMY_SPEED_NORMAL = ENEMY_SPEED*NORMAL_MULTIPLIER; // 100% of normal
    public static final float SURVIVAL_DIFFICULTY_NORMAL= SURVIVAL_DIFFICULTY*NORMAL_MULTIPLIER; // 100% of normal

    // == Hard game
    public static final float ENEMY_DPS_HARD = ENEMY_DPS*HARD_MULTIPLIER; // 150% of normal
    public static final float ENEMY_HP_HARD = ENEMY_HP*HARD_MULTIPLIER; // 150% of normal
    public static final float ENEMY_SPEED_HARD = ENEMY_SPEED*HARD_MULTIPLIER; // 150% of normal
    public static final float SURVIVAL_DIFFICULTY_HARD= SURVIVAL_DIFFICULTY*HARD_MULTIPLIER; // 150% of normal

    // == Names of Level Maps
    public static final String LEVEL1 = "maps/valleyOfTheHungry_Level1/valleyOfTheHungry.tmx";
    public static final String LEVEL2 = "none";
    public static final String LEVEL3 = "none";
    public static final String LEVEL4 = "none";
    public static final String LEVEL5 = "none";

    // == Box2D types of Bodies
    public static final int iSTATIC = 0;
    public static final int iDYNAMIC = 1;
    public static final int iKINEMATIC = 2;

    //Debugging phrases
    public static final String DEVELOPMENT_ERROR = "DEVELOPMENT ERROR:";

    //Parsing .tmx files
    // == Box2D types of Bodies in String
    public static final String strSTATIC = "static";
    public static final String strDYNAMIC = "dynamic";
    public static final String strKINEMATIC = "kinematic";

    public static final String PLAYER = "Player";

    private GameConfig(){}
}

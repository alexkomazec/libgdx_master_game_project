package com.potatowars.config;

public class GameConfig {

    // == Game Size ==
    public static final  float WIDTH = 480; //pixels
    public static final  float HEIGHT  = 800f; //pixels

    public static final float HUD_WIDTH = 480f; // world units
    public static final float HUD_HEIGHT = 800f; // world units

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

    private GameConfig(){

    }
}

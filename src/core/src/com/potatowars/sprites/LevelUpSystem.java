package com.potatowars.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.potatowars.hud.status.StatsPacket;
import com.potatowars.hud.status.StatusInterfaces.StatsObserver;
import com.potatowars.hud.status.StatusInterfaces.StatsSubject;

import static com.potatowars.config.GameConfig.LEVEL_10_EXP_CAPACITY;
import static com.potatowars.config.GameConfig.LEVEL_1_EXP_CAPACITY;
import static com.potatowars.config.GameConfig.LEVEL_2_EXP_CAPACITY;
import static com.potatowars.config.GameConfig.LEVEL_3_EXP_CAPACITY;
import static com.potatowars.config.GameConfig.LEVEL_4_EXP_CAPACITY;
import static com.potatowars.config.GameConfig.LEVEL_5_EXP_CAPACITY;
import static com.potatowars.config.GameConfig.LEVEL_6_EXP_CAPACITY;
import static com.potatowars.config.GameConfig.LEVEL_7_EXP_CAPACITY;
import static com.potatowars.config.GameConfig.LEVEL_8_EXP_CAPACITY;
import static com.potatowars.config.GameConfig.LEVEL_9_EXP_CAPACITY;

public class LevelUpSystem implements StatsSubject {

    private static final String CLASS_NAME = LevelUpSystem.class.getSimpleName();
    private static LevelUpSystem _instance = null;

    private Array<StatsObserver> observers;
    private LEVEL currentLevel;
    private int currentExpCapacity;
    private int allTime_exp;
    private StatsPacket statsPacket;
    private final LEVEL MAX_LEVEL = LEVEL.LEVEL10;

    public enum LEVEL{
        LEVEL1,
        LEVEL2,
        LEVEL3,
        LEVEL4,
        LEVEL5,
        LEVEL6,
        LEVEL7,
        LEVEL8,
        LEVEL9,
        LEVEL10
    }

    public static LevelUpSystem getInstance() {
        if (_instance == null) {
            _instance = new LevelUpSystem();
        }

        return _instance;
    }

    private LevelUpSystem(){
        currentLevel        = LEVEL.LEVEL1;
        allTime_exp         =  0;
        currentExpCapacity  = getCurrentExpCapacity();
        observers           = new Array<StatsObserver>();
    }

    public void addExperience(int exp){

        statsPacket = new StatsPacket();


        if((getLevel() != MAX_LEVEL))
        {
            increaseExp(exp);
        }
        else
        {
            //do nothing
        }

        if( allTime_exp<= currentExpCapacity )
        {
            //Do nothing
        }
        else
        {
            if(getLevel() != MAX_LEVEL) {
                //Level up!
                setExp(allTime_exp - currentExpCapacity);
                levelup();
                currentExpCapacity = getCurrentExpCapacity();

                statsPacket.getEnergyPoints().setHealthPoints(10);
                statsPacket.getEnergyPoints().setManaPoints(10);
                statsPacket.getEnergyPoints().setArmorPoints(10);
                statsPacket.getDamage().setDamage(10);
            }
        }

        if((getLevel() == MAX_LEVEL))
        {
            allTime_exp = currentExpCapacity;
        }

        statsPacket.setExp(this.allTime_exp);
        statsPacket.setLevel(this.currentLevel);
        statsPacket.setCurrentExpCapacity(this.currentExpCapacity);

        notify(this.statsPacket, StatsObserver.ComponentType.LEVEL_UP_SYSTEM_COMPONENT);

    }

     private void levelup(){

        switch (getLevel())
         {
             case LEVEL1:
                 currentLevel   =   LEVEL.LEVEL2;
                 break;
             case LEVEL2:
                 currentLevel   =   LEVEL.LEVEL3;
                 break;
             case LEVEL3:
                 currentLevel   =   LEVEL.LEVEL4;
                 break;
             case LEVEL4:
                 currentLevel   =   LEVEL.LEVEL5;
                 break;
             case LEVEL5:
                 currentLevel   =   LEVEL.LEVEL6;
                 break;
             case LEVEL6:
                 currentLevel   =   LEVEL.LEVEL7;
                 break;
             case LEVEL7:
                 currentLevel   =   LEVEL.LEVEL8;
                 break;
             case LEVEL8:
                 currentLevel   =   LEVEL.LEVEL9;
                 break;
             case LEVEL9:
             case LEVEL10:
                 currentLevel   =   LEVEL.LEVEL10;
                 break;
             default:
                 currentLevel   =   LEVEL.LEVEL1;
                 break;
         }
     }

     public int getCurrentExpCapacity(){

        switch (getLevel())
        {
            case LEVEL1:
                return LEVEL_1_EXP_CAPACITY;
            case LEVEL2:
                return LEVEL_2_EXP_CAPACITY;
            case LEVEL3:
                return LEVEL_3_EXP_CAPACITY;
            case LEVEL4:
                return LEVEL_4_EXP_CAPACITY;
            case LEVEL5:
                return LEVEL_5_EXP_CAPACITY;
            case LEVEL6:
                return LEVEL_6_EXP_CAPACITY;
            case LEVEL7:
                return LEVEL_7_EXP_CAPACITY;
            case LEVEL8:
                return LEVEL_8_EXP_CAPACITY;
            case LEVEL9:
                return LEVEL_9_EXP_CAPACITY;
            case LEVEL10:
                return LEVEL_10_EXP_CAPACITY;
            default:
                return LEVEL_1_EXP_CAPACITY;
        }
    }

    private void setExp(int exp){
        this.allTime_exp = exp;
    }

    public LEVEL getLevel() {
        return currentLevel;
    }

    private void setLevel(LEVEL level) {
        this.currentLevel = level;
    }

    public int getExp() {
        return allTime_exp;
    }

    private void increaseExp(int exp) {
        this.allTime_exp += exp;
    }

    public void setCurrentExpCapacity(int currentExpCapacity) {
        this.currentExpCapacity = currentExpCapacity;
    }

    @Override
    public void addObserver(StatsObserver statsObserver) {
        observers.add(statsObserver);
    }

    @Override
    public void removeObserver(StatsObserver statsObserver) {
        observers.removeValue(statsObserver, true);
    }

    @Override
    public void removeAllObservers() {
        for(StatsObserver observer: observers){
            observers.removeValue(observer, true);
        }
    }

    @Override
    public void notify(StatsPacket statusPacket, StatsObserver.ComponentType component) {
        for(StatsObserver observer: observers){
            Gdx.app.debug(CLASS_NAME,"Going to notify:" + observer);
            observer.onNotify(statusPacket, component);
        }
    }
}

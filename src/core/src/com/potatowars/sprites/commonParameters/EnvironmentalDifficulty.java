package com.potatowars.sprites.commonParameters;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.utils.Logger;
import com.potatowars.config.GameConfig;

public class EnvironmentalDifficulty {

    private int environmentalDifficultyValue;
    private String typeOfEnviromental;
    private float timeCount;
    private static final Logger log = new Logger(EnvironmentalDifficulty.class.getName(), Logger.DEBUG);

    public EnvironmentalDifficulty(String typeOfEnviromental){
        log.setLevel(Application.LOG_NONE);

        switch(typeOfEnviromental){
            case GameConfig.LEVEL1:
                this.typeOfEnviromental = "FOOD %";
                break;
            case GameConfig.LEVEL2:
                this.typeOfEnviromental = "WARMTH %";
                break;
            case GameConfig.LEVEL3:
                this.typeOfEnviromental = "COLDNESS %";
                break;
            case GameConfig.LEVEL4:
                this.typeOfEnviromental = "OXYGEN %";
                break;
            case GameConfig.LEVEL5:
                this.typeOfEnviromental = "GRAVITY %";
                break;
            default:
                System.out.println("Wrong type");
        }

        this.environmentalDifficultyValue = 100; // In percentage
    }

    public void update(float dt){

        log.debug("timeCount" + timeCount);
        timeCount+= dt;
        if(timeCount>10){
            environmentalDifficultyValue -=10;
            timeCount = 0;
        }else{
            //do nothing
        }
    }

    public String getTypeOfEnviromental() {
        return typeOfEnviromental;
    }
    public int getEnvironmentalDifficultyValue() {
        return environmentalDifficultyValue;
    }
}
